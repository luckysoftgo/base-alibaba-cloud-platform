package com.application.cloud.common.rabbitmq.core.api;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author ：孤狼
 * @description: 消息的顶级接口
 * @modified By：
 * @version: 1.0.0
 */
public interface RabbitMqSession {
	
	/**
	 * 获取给定消息的 RabbitAdmin 实例.
	 *
	 * @throws Exception
	 */
	public RabbitAdmin getRabbitAdmin() throws Exception;
	
	
	/**
	 * 获取给定消息的 RabbitTemplate 实例.
	 *
	 * @throws Exception
	 */
	public RabbitTemplate getRabbitTemplate() throws Exception;
	
	/**
	 * 获取队列的消息
	 *
	 * @param queueName
	 * @throws Exception
	 */
	public String receiveMessage(String queueName) throws Exception;
	
	/**
	 * 判断队列是否存在
	 *
	 * @param queueName
	 * @throws Exception
	 */
	public boolean queueExists(String queueName) throws Exception;
	
}
