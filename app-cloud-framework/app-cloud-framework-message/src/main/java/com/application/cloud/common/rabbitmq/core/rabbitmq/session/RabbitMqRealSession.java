package com.application.cloud.common.rabbitmq.core.rabbitmq.session;

import com.application.cloud.common.rabbitmq.core.api.RabbitMqSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author ：孤狼
 * @description: 基础实现
 * @modified By：
 * @version: 1.0.0
 */
@Slf4j
public class RabbitMqRealSession implements RabbitMqSession {
	
	private ConnectionFactory connectionFactory;
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	@Override
	public RabbitAdmin getRabbitAdmin() throws Exception {
		return new RabbitAdmin(connectionFactory);
	}
	
	@Override
	public RabbitTemplate getRabbitTemplate() throws Exception {
		return new RabbitTemplate(connectionFactory);
	}
	
	@Override
	public String receiveMessage(String queueName) throws Exception {
		RabbitTemplate rabbitTemplate = getRabbitTemplate();
		Message message = rabbitTemplate.receive(queueName);
		if (message != null) {
			byte[] body = message.getBody();
			String result = new String(body, StandardCharsets.UTF_8);
			return result;
		} else {
			log.info("没有在队列名为：{}队列上获取到消息信息", queueName);
			return "";
		}
	}
	
	@Override
	public boolean queueExists(String queueName) throws Exception {
		RabbitAdmin rabbitAdmin = getRabbitAdmin();
		QueueInformation information = rabbitAdmin.getQueueInfo(queueName);
		if (null == information) {
			return false;
		} else {
			return true;
		}
	}
}
