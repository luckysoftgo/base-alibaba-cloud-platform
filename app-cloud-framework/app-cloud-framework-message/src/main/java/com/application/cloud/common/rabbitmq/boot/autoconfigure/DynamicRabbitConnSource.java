package com.application.cloud.common.rabbitmq.boot.autoconfigure;


import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ：孤狼
 * @description: 动态链接配置
 * @modified By：
 * @version: 1.0.0
 */
public class DynamicRabbitConnSource implements InitializingBean, DisposableBean {
	
	@Nullable
	public Map<String, Connection> targetRabbitConnSource;
	@Nullable
	private Object defaultTargetRabbitConnSource;
	@Nullable
	private Map<String, Connection> resolvedRabbitConnSource;
	@Nullable
	private Connection resolvedDefaultRabbitConnSource;
	
	public DynamicRabbitConnSource() {
	}
	
	@Nullable
	public Map<String, Connection> getTargetRabbitConnSource() {
		return targetRabbitConnSource;
	}
	
	public void setTargetRabbitConnSource(@Nullable Map<String, Connection> targetRabbitConnSource) {
		this.targetRabbitConnSource = targetRabbitConnSource;
	}
	
	@Nullable
	public Object getDefaultTargetRabbitConnSource() {
		return defaultTargetRabbitConnSource;
	}
	
	public void setDefaultTargetRabbitConnSource(@Nullable Object defaultTargetRabbitConnSource) {
		this.defaultTargetRabbitConnSource = defaultTargetRabbitConnSource;
	}
	
	@Nullable
	public Map<String, Connection> getResolvedRabbitConnSource() {
		return resolvedRabbitConnSource;
	}
	
	public void setResolvedRabbitConnSource(@Nullable Map<String, Connection> resolvedRabbitConnSource) {
		this.resolvedRabbitConnSource = resolvedRabbitConnSource;
	}
	
	@Nullable
	public Connection getResolvedDefaultRabbitConnSource() {
		return resolvedDefaultRabbitConnSource;
	}
	
	public void setResolvedDefaultRabbitConnSource(@Nullable Connection resolvedDefaultRabbitConnSource) {
		this.resolvedDefaultRabbitConnSource = resolvedDefaultRabbitConnSource;
	}
	
	@Override
	public void afterPropertiesSet() {
		if (this.targetRabbitConnSource == null) {
			throw new IllegalArgumentException("Property 'targetRabbitConnSource' is required");
		} else {
			this.resolvedRabbitConnSource = new HashMap(this.targetRabbitConnSource.size());
			this.targetRabbitConnSource.forEach((key, value) -> {
				String lookupKey = this.resolveSpecifiedLookupKey(key);
				Connection rabbitConnSource = this.resolveSpecifiedRabbitConnSource(value);
				this.resolvedRabbitConnSource.put(lookupKey, rabbitConnSource);
			});
			if (this.defaultTargetRabbitConnSource != null) {
				this.resolvedDefaultRabbitConnSource = this.resolveSpecifiedRabbitConnSource(this.defaultTargetRabbitConnSource);
			}
		}
	}
	
	protected String resolveSpecifiedLookupKey(Object lookupKey) {
		return Objects.toString(lookupKey, "");
	}
	
	protected Connection resolveSpecifiedRabbitConnSource(Object rabbitConnSource) throws IllegalArgumentException {
		if (rabbitConnSource instanceof Connection) {
			return (Connection) rabbitConnSource;
		} else {
			throw new IllegalArgumentException("Illegal data source value - only [com.rabbitmq.client.Connection] supported: " + rabbitConnSource);
		}
	}
	
	public Connection getMasterConnection() throws Exception {
		return this.determineTargetRabbitConnSource(this.determineCurrentLookupKey());
	}
	
	public Connection determineTargetRabbitConnSource(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new RabbitMqException("Cannot determine target Connection for null key!");
		}
		Assert.notNull(this.resolvedRabbitConnSource, "Connection router not initialized");
		Connection rabbitConnSource = this.resolvedRabbitConnSource.get(key);
		if (rabbitConnSource == null) {
			rabbitConnSource = this.resolvedDefaultRabbitConnSource;
		}
		if (rabbitConnSource == null) {
			throw new RabbitMqException("Cannot determine target Connection for lookup key [" + key + "]");
		} else {
			return rabbitConnSource;
		}
	}
	
	/**
	 * 当前默认的链接.
	 *
	 * @return
	 */
	protected String determineCurrentLookupKey() {
		return "master";
	}
	
	@Override
	public void destroy() throws Exception {
	
	}
}
