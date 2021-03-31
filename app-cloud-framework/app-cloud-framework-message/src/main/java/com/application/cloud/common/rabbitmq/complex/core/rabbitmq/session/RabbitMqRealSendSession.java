package com.application.cloud.common.rabbitmq.complex.core.rabbitmq.session;

import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitConnSource;
import com.application.cloud.common.rabbitmq.complex.core.api.RabbitMqSendSession;
import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author ：孤狼
 * @description: rabbitmq消息发送处理类
 * @modified By：
 * @version: 1.0.0
 */
@Slf4j
public class RabbitMqRealSendSession extends RabbitMqBasicSession implements RabbitMqSendSession {
	
	public RabbitMqRealSendSession(DynamicRabbitConnSource rabbitConnSource) {
		super(rabbitConnSource);
	}
	
	@Override
	public Connection getConnection(String instanceName) throws Exception {
		return rabbitConnSource.determineTargetRabbitConnSource(instanceName);
	}
	
	@Override
	public boolean sendMessage(String queueName, String message) throws Exception {
		return sendMessage(null, queueName, message);
	}
	
	@Override
	public boolean sendMessage(String exchangeName, String queueName, String message) throws Exception {
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
			//消息内容为byte array, so可以自己随意编码
			if (StringUtils.isEmpty(exchangeName)) {
				exchangeName = "";
			}
			channel.basicPublish(exchangeName, queueName, null, message.getBytes(StandardCharsets.UTF_8));
			//消息发送完成后，关闭通道和连接
			return true;
		} catch (RabbitMqException e) {
			log.error("发送消息产生了异常,异常信息是:{}", e.getMessage());
			return false;
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
	
	/**
	 * object 转字节数组.
	 *
	 * @param object
	 * @return
	 */
	private byte[] objectToBytes(final Object object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			return baos.toByteArray();
		} catch (Exception e) {
			log.error("对象写入字节出现异常,异常信息是:{}", e.getMessage());
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (baos != null) {
					baos.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}
}
