package com.application.cloud.common.rabbitmq.complex.core.rabbitmq.factory;

import com.application.cloud.common.rabbitmq.complex.core.api.RabbitMqSession;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.pool.RabbitMqTempPool;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.session.RabbitMqRealSession;
import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ：孤狼
 * @description: 生产rabbitmq的实现类
 * @modified By：
 * @version: 1.0.0
 */
@Slf4j
public class RabbitMqRealTemplateFactory {
	/**
	 * rabbitmq连接池.
	 */
	private RabbitMqTempPool tempPool;
	
	public RabbitMqRealTemplateFactory() {
	}
	
	public RabbitMqRealTemplateFactory(RabbitMqTempPool tempPool) {
		this.tempPool = tempPool;
	}
	
	public RabbitMqSession getRabbitTemplateSession() throws RabbitMqException {
		RabbitMqSession session = null;
		try {
			session = (RabbitMqSession) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
					new Class[]{RabbitMqSession.class}, new RabbitMqTemplateSessionProxy(new RabbitMqRealSession()));
		} catch (Exception e) {
			log.error("错误信息是:{}", e);
		}
		return session;
	}
	
	
	private class RabbitMqTemplateSessionProxy implements InvocationHandler {
		
		private RabbitMqRealSession sendSession;
		
		private ConnectionFactory connection = null;
		
		public RabbitMqTemplateSessionProxy(RabbitMqRealSession sendSession) {
			this.sendSession = sendSession;
		}
		
		/**
		 * 同步获取rabbitmq链接
		 *
		 * @return
		 */
		private synchronized ConnectionFactory getConnectFactory() {
			log.debug("获取rabbitmq链接");
			try {
				if (connection == null) {
					connection = RabbitMqRealTemplateFactory.this.tempPool.getResource();
				}
			} catch (Exception e) {
				log.error("获取rabbitmq的connection链接错误,{}", e);
				throw new RabbitMqException(e);
			}
			if (null == connection) {
				log.error("[rabbitmq错误:{}]", "获得connection实例对象为空");
				throw new RabbitMqException("获得rabbitmq实例对象为空");
			}
			return connection;
		}
		
		/**
		 * Rabbitmq方法的代理实现
		 *
		 * @param proxy
		 * @param method
		 * @param args
		 * @return
		 * @throws Throwable
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			boolean success = true;
			try {
				if (getConnectFactory() == null) {
					log.error("获取rabbitmq的连接对象失败");
					throw new RabbitMqException("获取rabbitmq连接池失败");
				}
				connection = getConnectFactory();
				sendSession.setConnectionFactory(connection);
				return method.invoke(sendSession, args);
			} catch (RuntimeException e) {
				success = false;
				if (connection != null) {
					//com.caitc.message.api.rabbitmq.close();
					if (tempPool != null) {
						tempPool.returnResource(connection);
					}
					connection = null;
				}
				log.error("[rabbitmq执行失败！异常信息为：{}]", e);
				throw new RabbitMqException("rabbitmq执行失败！");
			}
		}
	}
}
