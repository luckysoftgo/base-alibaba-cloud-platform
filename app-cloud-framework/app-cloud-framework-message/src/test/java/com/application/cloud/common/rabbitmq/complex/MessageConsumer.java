package com.application.cloud.common.rabbitmq.complex;

import com.application.cloud.common.rabbitmq.BasicTest;
import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitSessionSource;
import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitTempSource;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.factory.RabbitMqRealSessionFactory;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.factory.RabbitMqRealTemplateFactory;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

/**
 * @author ：admin
 * @description: 消息的消费者
 * @modified By：
 * @version: 1.0.0
 */

public class MessageConsumer extends BasicTest {
	
	@Autowired
	private DynamicRabbitSessionSource rabbitSessionSource;
	
	@Autowired
	private DynamicRabbitTempSource rabbitTempSource;
	
	@Test
	public void getSimpleMsg() {
		try {
			String queueName = "rabbitmq.hello";
			RabbitMqRealSessionFactory templateFactory = rabbitSessionSource.determineTargetRabbitSessionSource("slave1");
			String message = templateFactory.getRabbitMqReceiveSession().receiveMessage(queueName);
			System.out.println("消息是:" + message + ",接收完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void getMsgByPoolTemplate1() {
		try {
			String queueName = "rabbitmq.test";
			RabbitMqRealTemplateFactory templateFactory = rabbitTempSource.determineTargetRabbitTemplateSource("master");
			RabbitTemplate rabbitTemplate = templateFactory.getRabbitTemplateSession().getRabbitTemplate();
			Message message = rabbitTemplate.receive(queueName);
			byte[] body = message.getBody();
			String result = new String(body, StandardCharsets.UTF_8);
			System.out.println("消息是:" + result + ",接收完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void getMsgByPoolTemplate2() {
		try {
			String queueName = "rabbitmq.test";
			RabbitMqRealTemplateFactory templateFactory = rabbitTempSource.determineTargetRabbitTemplateSource("master");
			String result = templateFactory.getRabbitTemplateSession().receiveMessage(queueName);
			System.out.println("消息是:" + result + ",接收完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void getQueueExists() {
		try {
			String queueName = "rabbitmq";
			RabbitMqRealTemplateFactory templateFactory = rabbitTempSource.determineTargetRabbitTemplateSource("master");
			boolean result = templateFactory.getRabbitTemplateSession().queueExists(queueName);
			if (result) {
				System.out.println("队列存在!");
			} else {
				System.out.println("队列不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
