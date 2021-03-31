package com.application.cloud.common.rabbitmq;

import com.application.cloud.common.rabbitmq.boot.autoconfigure.DynamicRabbitSessionSource;
import com.application.cloud.common.rabbitmq.boot.autoconfigure.DynamicRabbitTempSource;
import com.application.cloud.common.rabbitmq.core.rabbitmq.factory.RabbitMqRealSessionFactory;
import com.application.cloud.common.rabbitmq.core.rabbitmq.factory.RabbitMqRealTemplateFactory;
import org.junit.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：admin
 * @date ：2021-3-29
 * @description: 消息发送者
 * @modified By：
 * @version: 1.0.0
 */
public class MessageProducer extends BasicTest {
	
	@Autowired
	private DynamicRabbitSessionSource rabbitSessionSource;
	
	@Autowired
	private DynamicRabbitTempSource rabbitTempSource;
	
	@Test
	public void sendSimpleMsg() {
		try {
			String queueName = "rabbitmq.car";
			String message = "nice to meet you！！!";
			RabbitMqRealSessionFactory sessionFactory = rabbitSessionSource.determineTargetRabbitSessionSource("master");
			boolean result = sessionFactory.getRabbitMqSendSession().sendMessage(queueName, message);
			
			/*
			Connection connection = sessionFactory.getRabbitMqSendSession().getConnection("master");
			Channel channel = connection.createChannel();
			*/
			
			System.out.println("发送的结果是:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void sendMsgByPoolTemplate() {
		try {
			RabbitMqRealTemplateFactory sessionFactory = rabbitTempSource.determineTargetRabbitTemplateSource("master");
			RabbitTemplate rabbitTemplate = sessionFactory.getRabbitTemplateSession().getRabbitTemplate();
			String msg = "nice to meet you!";
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setContentType("text/plain");
			Message message = new Message(msg.getBytes(), messageProperties);
			String exchangeName = "default-exchange";
			String queueName = "rabbitmq.test";
			
			rabbitTemplate.send(exchangeName, queueName, message);
			rabbitTemplate.convertAndSend(exchangeName, queueName, "hello object message send!");
			
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "这是第一个消息");
			map.put("data", Arrays.asList("helloword", 123, true));
			rabbitTemplate.convertAndSend(queueName, map);
			System.out.println("消息发送完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void sendMsgByPoolAdmin() {
		try {
			String exchangeName = "default-exchange";
			String queueName = "rabbitmq.test";
			RabbitMqRealTemplateFactory sessionFactory = rabbitTempSource.determineTargetRabbitTemplateSource("master");
			RabbitAdmin rabbitAdmin = sessionFactory.getRabbitTemplateSession().getRabbitAdmin();
			RabbitTemplate rabbitTemplate = sessionFactory.getRabbitTemplateSession().getRabbitTemplate();
			
			RabbitMqRealTemplateFactory sessionFactory1 = rabbitTempSource.determineTargetRabbitTemplateSource("slave");
			RabbitAdmin rabbitAdmin1 = sessionFactory.getRabbitTemplateSession().getRabbitAdmin();
			RabbitTemplate rabbitTemplate1 = sessionFactory.getRabbitTemplateSession().getRabbitTemplate();
			
			String msg = "nice to meet you!";
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setContentType("text/plain");
			Message message = new Message(msg.getBytes(), messageProperties);
			Queue queue = new Queue(queueName, true, false, false);
			//创建队列.
			rabbitAdmin.declareQueue(queue);
			DirectExchange directExchange = new DirectExchange(exchangeName, true, false, (Map) null);
			//绑定关系
			rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(queueName));
			rabbitTemplate.send(exchangeName, queueName, message);
			rabbitTemplate.convertAndSend(exchangeName, queueName, "hello object message send!");
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "这是第一个消息");
			map.put("data", Arrays.asList("helloword", 123, true));
			rabbitAdmin.getRabbitTemplate().convertAndSend(queueName, map);
			System.out.println("消息发送完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
