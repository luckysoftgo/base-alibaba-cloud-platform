package com.application.cloud.common.rabbitmq.simple.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author ：admin
 * @description: 消息发送确认
 * ConfirmCallback  只确认消息是否正确到达 Exchange 中
 * ReturnCallback   消息没有正确到达队列时触发回调，如果正确到达队列不执行
 * 1. 如果消息没有到exchange,则confirm回调,ack=false
 * 2. 如果消息到达exchange,则confirm回调,ack=true
 * 3. exchange到queue成功,则不回调return
 * 4. exchange到queue失败,则回调return
 * @modified By：
 * @version: 1.0.0
 */
@Slf4j
public class MessageReturnCallback implements RabbitTemplate.ReturnCallback {
	
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		log.info("消息发送失败,发送的消息没有到达队列：{}", routingKey);
		log.info("消息主体: {}", message);
		log.info("回复编码: {}", replyCode);
		log.info("回复内容: {}", replyText);
		log.info("交换器: {}", exchange);
		log.info("路由键: {}", routingKey);
	}
}
