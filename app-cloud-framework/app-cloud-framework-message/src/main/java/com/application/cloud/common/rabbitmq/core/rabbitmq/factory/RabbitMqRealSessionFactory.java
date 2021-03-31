package com.application.cloud.common.rabbitmq.core.rabbitmq.factory;

import com.application.cloud.common.rabbitmq.boot.autoconfigure.DynamicRabbitConnSource;
import com.application.cloud.common.rabbitmq.core.api.RabbitMqReceiveSession;
import com.application.cloud.common.rabbitmq.core.api.RabbitMqSendSession;
import com.application.cloud.common.rabbitmq.core.factory.RabbitMqSessionFactory;
import com.application.cloud.common.rabbitmq.core.rabbitmq.pool.RabbitMqConnPool;
import com.application.cloud.common.rabbitmq.core.rabbitmq.session.RabbitMqRealReceiveSession;
import com.application.cloud.common.rabbitmq.core.rabbitmq.session.RabbitMqRealSendSession;
import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ：孤狼
 * @description: 消息实现类
 * @modified By：
 * @version: 1.0.0
 */
@Slf4j
public class RabbitMqRealSessionFactory implements RabbitMqSessionFactory {
	/**
	 * rabbitmq连接池.
	 */
	private RabbitMqConnPool connPool;
	/**
	 * rabbitmq连接池對象.
	 */
	private DynamicRabbitConnSource rabbitConnSource;
	
	public RabbitMqRealSessionFactory() {
	}
	
	public RabbitMqRealSessionFactory(RabbitMqConnPool connPool) {
		this.connPool = connPool;
	}
	
	public RabbitMqRealSessionFactory(RabbitMqConnPool connPool, DynamicRabbitConnSource rabbitConnSource) {
		this.connPool = connPool;
		this.rabbitConnSource = rabbitConnSource;
	}
	
	@Override
	public RabbitMqSendSession getRabbitMqSendSession() throws RabbitMqException {
		RabbitMqSendSession session = null;
		try {
			session = (RabbitMqSendSession) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
					new Class[]{RabbitMqSendSession.class}, new RabbitMqRealSendSessionProxy(new RabbitMqRealSendSession(rabbitConnSource)));
		} catch (Exception e) {
			log.error("错误信息是:{}", e);
		}
		return session;
	}
	
	@Override
	public RabbitMqReceiveSession getRabbitMqReceiveSession() throws RabbitMqException {
		RabbitMqReceiveSession session = null;
		try {
			session = (RabbitMqReceiveSession) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
					new Class[]{RabbitMqReceiveSession.class}, new RabbitMqRealReceiveSessionProxy(new RabbitMqRealReceiveSession(rabbitConnSource)));
		} catch (Exception e) {
			log.error("错误信息是:{}", e);
		}
		return session;
	}
	
	private class RabbitMqRealSendSessionProxy implements InvocationHandler {
		
		private RabbitMqRealSendSession sendSession;
		private Connection connection = null;
		
		public RabbitMqRealSendSessionProxy(RabbitMqRealSendSession sendSession) {
			this.sendSession = sendSession;
		}
		
		/**
		 * 同步获取rabbitmq链接
		 *
		 * @return
		 */
		private synchronized Connection getConnection() {
			log.debug("获取rabbitmq链接");
			try {
				if (connection == null) {
					connection = RabbitMqRealSessionFactory.this.connPool.getResource();
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
				if (getConnection() == null) {
					log.error("获取rabbitmq的连接对象失败");
					throw new RabbitMqException("获取rabbitmq连接池失败");
				}
				connection = getConnection();
				sendSession.setConnection(connection);
				return method.invoke(sendSession, args);
			} catch (RuntimeException e) {
				success = false;
				if (connection != null) {
					//com.caitc.message.api.rabbitmq.close();
					if (connPool != null) {
						connPool.returnResource(connection);
					}
					connection = null;
				}
				log.error("[rabbitmq执行失败！异常信息为：{}]", e);
				throw e;
			}
		}
	}
	
	private class RabbitMqRealReceiveSessionProxy implements InvocationHandler {
		
		private RabbitMqRealReceiveSession receiveSession;
		private Connection connection = null;
		
		public RabbitMqRealReceiveSessionProxy(RabbitMqRealReceiveSession receiveSession) {
			this.receiveSession = receiveSession;
		}
		
		/**
		 * 同步获取rabbitmq链接
		 *
		 * @return
		 */
		private synchronized Connection getConnection() {
			log.debug("获取rabbitmq链接");
			try {
				if (connection == null) {
					connection = RabbitMqRealSessionFactory.this.connPool.getResource();
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
				if (getConnection() == null) {
					log.error("获取rabbitmq的连接对象失败");
					throw new RabbitMqException("获取rabbitmq连接池失败");
				}
				connection = getConnection();
				receiveSession.setConnection(connection);
				return method.invoke(receiveSession, args);
			} catch (RuntimeException e) {
				success = false;
				if (connection != null) {
					//com.caitc.message.api.rabbitmq.close();
					if (connPool != null) {
						connPool.returnResource(connection);
					}
					connection = null;
				}
				log.error("[rabbitmq执行失败！异常信息为：{}]", e);
				throw e;
			}
		}
	}
}
