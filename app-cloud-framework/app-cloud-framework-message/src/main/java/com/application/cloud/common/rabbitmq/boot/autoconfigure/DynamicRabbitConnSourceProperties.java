
package com.application.cloud.common.rabbitmq.boot.autoconfigure;

import com.application.cloud.common.rabbitmq.boot.config.RabbitMqProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：孤狼
 * @description: 链接源配置的获取
 * @modified By：
 * @version: 1.0.0
 */
@Data
@ConfigurationProperties(prefix = DynamicRabbitConnSourceProperties.PREFIX)
public class DynamicRabbitConnSourceProperties {
	
	/**
	 * 配置信息.
	 */
	public static final String PREFIX = "spring.rabbitmq.dynamic";
	
	/**
	 * 必须设置默认的链接,默认master
	 */
	private String primary = "master";
	/**
	 * 每一个链接配置
	 */
	private Map<String, RabbitMqProperty> connections = new LinkedHashMap<>();
	
	public String getPrimary() {
		return primary;
	}
	
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	
	public Map<String, RabbitMqProperty> getConnections() {
		return connections;
	}
	
	public void setConnections(Map<String, RabbitMqProperty> connections) {
		this.connections = connections;
	}
}
