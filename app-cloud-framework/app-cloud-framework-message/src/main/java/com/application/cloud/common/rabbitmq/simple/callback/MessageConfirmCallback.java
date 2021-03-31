package com.application.cloud.common.rabbitmq.simple.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
public class MessageConfirmCallback implements RabbitTemplate.ConfirmCallback {
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			log.info("消息发送成功,消息的唯一标识是:{},消息返回体是:{}", correlationData.getId(), correlationData.getReturnedMessage());
		} else {
			log.info("消息发送失败,失败原因是:{}", cause);
		}
		log.info("消息唯一标识: {}", correlationData);
		log.info("确认状态: {},true:如果消息到达exchange;false:如果消息没有到exchange", ack);
		log.info("造成原因: {}", cause);
	}
}
