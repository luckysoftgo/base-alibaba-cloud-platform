package com.application.cloud.common.rabbitmq.core.rabbitmq.session;

import com.application.cloud.common.rabbitmq.boot.autoconfigure.DynamicRabbitConnSource;
import com.application.cloud.common.rabbitmq.core.api.RabbitMqReceiveSession;
import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author ：孤狼
 * @description: rabbitmq消息接收处理类
 * @modified By：
 * @version: 1.0.0
 */
@Slf4j
public class RabbitMqRealReceiveSession extends RabbitMqBasicSession implements RabbitMqReceiveSession {
	
	public RabbitMqRealReceiveSession(DynamicRabbitConnSource rabbitConnSource) {
		super(rabbitConnSource);
	}
	
	@Override
	public Connection getConnection(String instanceName) throws Exception {
		return rabbitConnSource.determineTargetRabbitConnSource(instanceName);
	}
	
	@Override
	public String receiveMessage(String queueName) throws Exception {
		Connection connection = null;
		Channel channel = null;
		try {
			//获得连接.
			connection = getConnection();
			//创建一个通道.
			channel = connection.createChannel();
			//申明通道发送消息的队列，把消息发送至消息队列 queueName
			//Declaring a queue is idempotent - 如果队列不存在则会创建一个队列,持久队列
			channel.queueDeclare(queueName, true, false, false, null);
			// 创建消费者，阻塞接收消息
			GetResponse response = channel.basicGet(queueName, true);
			if (null == response) {
				log.info("通过队列名为:{},并未获取到队列中的消息", queueName);
				return null;
			}
			byte[] body = response.getBody();
			String result = new String(body, StandardCharsets.UTF_8);
			//消息发送完成后，关闭通道和连接
			return result;
		} catch (RabbitMqException e) {
			log.error("发送消息产生了异常,异常信息是:{}", e.getMessage());
			return "";
		} finally {
			//消息发送完成后，关闭通道和连接
			if (null != channel) {
				channel.close();
			}
			if (null != connection) {
				connection.close();
			}
		}
	}
}
