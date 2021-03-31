package com.application.cloud.common.rabbitmq.core.rabbitmq.factory;

import com.application.cloud.common.rabbitmq.boot.config.RabbitMqProperty;
import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ：孤狼
 * @description: 连接池工厂
 * @modified By：
 * @version: 1.0.0
 */
public class RabbitMqTempPoolFactory extends BasePooledObjectFactory<ConnectionFactory> {
	/**
	 * 配置项名称
	 */
	private AtomicReference<RabbitMqProperty> nodesReference = new AtomicReference<RabbitMqProperty>();
	
	public RabbitMqTempPoolFactory(RabbitMqProperty rabbitMqConfig) {
		this.nodesReference.set(rabbitMqConfig);
	}
	
	@Override
	public ConnectionFactory create() throws Exception {
		RabbitMqProperty rabbitMqConfig = nodesReference.get();
		try {
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
			if (!StringUtils.isEmpty(rabbitMqConfig.getUsername())) {
				connectionFactory.setUsername(rabbitMqConfig.getUsername());
			}
			if (!StringUtils.isEmpty(rabbitMqConfig.getPassword())) {
				connectionFactory.setPassword(rabbitMqConfig.getPassword());
			}
			if (!StringUtils.isEmpty(rabbitMqConfig.getVirtualHost())) {
				connectionFactory.setVirtualHost(rabbitMqConfig.getVirtualHost());
			}
			if (!StringUtils.isEmpty(rabbitMqConfig.getAddresses())) {
				connectionFactory.setAddresses(rabbitMqConfig.getAddresses());
			}
			//设置连接工厂缓存模式：
			connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
			//缓存连接数
			connectionFactory.setConnectionCacheSize(5);
			//设置连接限制
			connectionFactory.setConnectionLimit(10);
			connectionFactory.setPublisherConfirmType(ConfirmType.CORRELATED);
			connectionFactory.setPublisherReturns(true);
			return connectionFactory;
		} catch (Exception e) {
			throw new RabbitMqException("dynamic-rabbitmq create basic connection named " + rabbitMqConfig.getInstance() + " error");
		}
	}
	
	@Override
	public PooledObject<ConnectionFactory> wrap(ConnectionFactory conn) {
		//包装实际对象
		return new DefaultPooledObject<>(conn);
	}
	
}
