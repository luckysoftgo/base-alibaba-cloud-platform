package com.application.cloud.common.rabbitmq.core.api;

import com.rabbitmq.client.Connection;

/**
 * @author ：孤狼
 * @description: 消息发送接口
 * @modified By：
 * @version: 1.0.0
 */
public interface RabbitMqSendSession {
	
	/**
	 * 获取给定消息的链接实例.
	 *
	 * @param instanceName
	 * @throws Exception
	 */
	public Connection getConnection(String instanceName) throws Exception;
	
	/**
	 * 指定队列发送消息
	 *
	 * @param queueName
	 * @param message
	 * @throws Exception
	 */
	public boolean sendMessage(String queueName, String message) throws Exception;
	
	/**
	 * 给指定的交换实例上发信息
	 *
	 * @param queueName
	 * @param message
	 * @throws Exception
	 */
	public boolean sendMessage(String exchangeName, String queueName, String message) throws Exception;
	
}
