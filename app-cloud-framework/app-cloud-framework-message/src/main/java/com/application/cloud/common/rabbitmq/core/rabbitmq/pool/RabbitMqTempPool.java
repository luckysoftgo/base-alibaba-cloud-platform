package com.application.cloud.common.rabbitmq.core.rabbitmq.pool;

import com.application.cloud.common.rabbitmq.boot.config.RabbitMqPoolConfig;
import com.application.cloud.common.rabbitmq.boot.config.RabbitMqProperty;
import com.application.cloud.common.rabbitmq.core.rabbitmq.factory.RabbitMqTempPoolFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

/**
 * @author ：孤狼
 * @description: rabbittemplate链接池
 * @modified By：
 * @version: 1.0.0
 */
public class RabbitMqTempPool extends RabbitMqPool<ConnectionFactory> {
	
	/**
	 * 构造函数.
	 *
	 * @param rabbitMqPoolConfig
	 */
	public RabbitMqTempPool(RabbitMqPoolConfig rabbitMqPoolConfig, RabbitMqProperty rabbitMqConfig) {
		super(rabbitMqPoolConfig, new RabbitMqTempPoolFactory(rabbitMqConfig));
	}
}
