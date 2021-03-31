package com.application.cloud.common.rabbitmq.complex.core.rabbitmq.pool;

import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitConnSource;
import com.application.cloud.common.rabbitmq.complex.boot.config.RabbitMqPoolConfig;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.factory.RabbitMqConnPoolFactory;
import com.rabbitmq.client.Connection;

/**
 * @author ：孤狼
 * @description: 链接池
 * @modified By：
 * @version: 1.0.0
 */
public class RabbitMqConnPool extends RabbitMqPool<Connection> {
	
	/**
	 * 构造函数.
	 *
	 * @param rabbitMqPoolConfig
	 */
	public RabbitMqConnPool(RabbitMqPoolConfig rabbitMqPoolConfig, DynamicRabbitConnSource rabbitConnSource) {
		super(rabbitMqPoolConfig, new RabbitMqConnPoolFactory(rabbitMqPoolConfig, rabbitConnSource));
	}
}
