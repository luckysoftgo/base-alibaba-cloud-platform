package com.application.cloud.common.rabbitmq.complex.core.factory;

import com.application.cloud.common.rabbitmq.complex.core.api.RabbitMqReceiveSession;
import com.application.cloud.common.rabbitmq.complex.core.api.RabbitMqSendSession;
import com.application.cloud.common.rabbitmq.exception.RabbitMqException;

/**
 * @author ：孤狼
 * @description: 工厂实例
 * @modified By：
 * @version: 1.0.0
 */
public interface RabbitMqSessionFactory {
	
	/**
	 * 消息发送对象
	 *
	 * @return
	 * @throws RabbitMqException
	 */
	RabbitMqSendSession getRabbitMqSendSession() throws RabbitMqException;
	
	/**
	 * 消息接收对象
	 *
	 * @return
	 * @throws RabbitMqException
	 */
	RabbitMqReceiveSession getRabbitMqReceiveSession() throws RabbitMqException;
	
}
