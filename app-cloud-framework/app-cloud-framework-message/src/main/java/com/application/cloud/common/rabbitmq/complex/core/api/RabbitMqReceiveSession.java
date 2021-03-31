package com.application.cloud.common.rabbitmq.complex.core.api;

import com.rabbitmq.client.Connection;

/**
 * @author ：孤狼
 * @description: 消息发送接口
 * @modified By：
 * @version: 1.0.0
 */
public interface RabbitMqReceiveSession {
	
	/**
	 * 获取给定消息的链接实例.
	 *
	 * @param instanceName
	 * @throws Exception
	 */
	public Connection getConnection(String instanceName) throws Exception;
	
	/**
	 * 指定队列收消息
	 *
	 * @param queueName
	 * @throws Exception
	 */
	public String receiveMessage(String queueName) throws Exception;
	
}
