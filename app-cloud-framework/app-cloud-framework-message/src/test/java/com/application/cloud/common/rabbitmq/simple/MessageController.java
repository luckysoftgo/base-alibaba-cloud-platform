package com.application.cloud.common.rabbitmq.simple;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author ：admin
 * @date ：2021-3-31
 * @description: 测试机制
 * @modified By：
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Resource(name = "defaultRabbitTemplate")
	private RabbitTemplate defaultRabbitTemplate;
	
	@GetMapping("/send")
	public String sendMessage(String msg) {
		try {
			String queueName = "rabbitmq.car";
			//default
			//String msg = "nice to meet you!";
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setContentType("application/json");
			Message message = new Message(msg.getBytes(StandardCharsets.UTF_8), messageProperties);
			defaultRabbitTemplate.send(queueName, message);
			System.out.println("send success");
			return "success!";
		} catch (Exception e) {
			return "failed!";
		}
	}
}
