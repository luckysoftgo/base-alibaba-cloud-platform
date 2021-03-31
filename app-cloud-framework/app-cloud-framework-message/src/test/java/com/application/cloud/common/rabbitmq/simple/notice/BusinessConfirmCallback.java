package com.application.cloud.common.rabbitmq.simple.notice;

import com.application.cloud.common.rabbitmq.simple.callback.MessageConfirmCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * @author ：admin
 * @date ：2021-3-31
 * @description:
 * @modified By：
 * @version: 1.0.0
 */
@Slf4j
public class BusinessConfirmCallback extends MessageConfirmCallback {
	
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			System.out.println("消息发送成功" + correlationData);
		} else {
			System.out.println("消息发送失败:" + cause);
		}
		log.info("消息唯一标识: {}", correlationData);
		log.info("确认状态: {}", ack);
		log.info("造成原因: {}", cause);
	}
	
}
