package com.application.cloud.common.rabbitmq.simple.notice;

import com.application.cloud.common.rabbitmq.simple.callback.MessageReturnCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

/**
 * @author ：admin
 * @date ：2021-3-31
 * @description:
 * @modified By：
 * @version: 1.0.0
 */
@Slf4j
public class BusinessReturnCallback extends MessageReturnCallback {
	
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		log.info("消息主体: {}", message);
		log.info("回复编码: {}", replyCode);
		log.info("回复内容: {}", replyText);
		log.info("交换器: {}", exchange);
		log.info("路由键: {}", routingKey);
	}
}
