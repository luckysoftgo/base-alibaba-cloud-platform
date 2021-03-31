package com.application.cloud.common.rabbitmq.complex.core.rabbitmq.session;

import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitConnSource;
import com.rabbitmq.client.Connection;

/**
 * @author ：孤狼
 * @description: rabbitmq实现基础类
 * @modified By：
 * @version: 1.0.0
 */
public class RabbitMqBasicSession {
	
	private Connection connection;
	
	public DynamicRabbitConnSource rabbitConnSource;
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public DynamicRabbitConnSource getRabbitConnSource() {
		return rabbitConnSource;
	}
	
	public void setRabbitConnSource(DynamicRabbitConnSource rabbitConnSource) {
		this.rabbitConnSource = rabbitConnSource;
	}
	
	public RabbitMqBasicSession(DynamicRabbitConnSource rabbitConnSource) {
		this.rabbitConnSource = rabbitConnSource;
	}
}
