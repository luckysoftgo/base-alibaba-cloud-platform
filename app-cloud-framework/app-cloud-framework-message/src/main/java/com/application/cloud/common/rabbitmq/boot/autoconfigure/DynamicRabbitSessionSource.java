package com.application.cloud.common.rabbitmq.boot.autoconfigure;


import com.application.cloud.common.rabbitmq.core.rabbitmq.factory.RabbitMqRealSessionFactory;
import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
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
 * @description: 动态操作配置
 * @modified By：
 * @version: 1.0.0
 */
public class DynamicRabbitSessionSource implements InitializingBean, DisposableBean {
	
	@Nullable
	public Map<String, RabbitMqRealSessionFactory> targetRabbitSessionSource;
	@Nullable
	private Object defaultTargetRabbitSessionSource;
	@Nullable
	private Map<String, RabbitMqRealSessionFactory> resolvedRabbitSessionSource;
	@Nullable
	private RabbitMqRealSessionFactory resolvedDefaultRabbitSessionSource;
	
	public DynamicRabbitSessionSource() {
	}
	
	@Nullable
	public Map<String, RabbitMqRealSessionFactory> getTargetRabbitSessionSource() {
		return targetRabbitSessionSource;
	}
	
	public void setTargetRabbitSessionSource(@Nullable Map<String, RabbitMqRealSessionFactory> targetRabbitSessionSource) {
		this.targetRabbitSessionSource = targetRabbitSessionSource;
	}
	
	@Nullable
	public Object getDefaultTargetRabbitSessionSource() {
		return defaultTargetRabbitSessionSource;
	}
	
	public void setDefaultTargetRabbitSessionSource(@Nullable Object defaultTargetRabbitSessionSource) {
		this.defaultTargetRabbitSessionSource = defaultTargetRabbitSessionSource;
	}
	
	@Nullable
	public Map<String, RabbitMqRealSessionFactory> getResolvedRabbitSessionSource() {
		return resolvedRabbitSessionSource;
	}
	
	public void setResolvedRabbitSessionSource(@Nullable Map<String, RabbitMqRealSessionFactory> resolvedRabbitSessionSource) {
		this.resolvedRabbitSessionSource = resolvedRabbitSessionSource;
	}
	
	@Nullable
	public RabbitMqRealSessionFactory getResolvedDefaultRabbitSessionSource() {
		return resolvedDefaultRabbitSessionSource;
	}
	
	public void setResolvedDefaultRabbitSessionSource(@Nullable RabbitMqRealSessionFactory resolvedDefaultRabbitSessionSource) {
		this.resolvedDefaultRabbitSessionSource = resolvedDefaultRabbitSessionSource;
	}
	
	@Override
	public void afterPropertiesSet() {
		if (this.targetRabbitSessionSource == null) {
			throw new IllegalArgumentException("Property 'targetRabbitSessionSource' is required");
		} else {
			this.resolvedRabbitSessionSource = new HashMap(this.targetRabbitSessionSource.size());
			this.targetRabbitSessionSource.forEach((key, value) -> {
				String lookupKey = this.resolveSpecifiedLookupKey(key);
				RabbitMqRealSessionFactory rabbitSessionSource = this.resolveSpecifiedRabbitSessionSource(value);
				this.resolvedRabbitSessionSource.put(lookupKey, rabbitSessionSource);
			});
			if (this.defaultTargetRabbitSessionSource != null) {
				this.resolvedDefaultRabbitSessionSource = this.resolveSpecifiedRabbitSessionSource(this.defaultTargetRabbitSessionSource);
			}
		}
	}
	
	protected String resolveSpecifiedLookupKey(Object lookupKey) {
		return Objects.toString(lookupKey, "");
	}
	
	protected RabbitMqRealSessionFactory resolveSpecifiedRabbitSessionSource(Object rabbitSessionSource) throws IllegalArgumentException {
		if (rabbitSessionSource instanceof RabbitMqRealSessionFactory) {
			return (RabbitMqRealSessionFactory) rabbitSessionSource;
		} else {
			throw new IllegalArgumentException("Illegal data source value - only [com.rabbitmq.client.Sessionection] supported: " + rabbitSessionSource);
		}
	}
	
	public RabbitMqRealSessionFactory getMasterSession() throws Exception {
		return this.determineTargetRabbitSessionSource(this.determineCurrentLookupKey());
	}
	
	public RabbitMqRealSessionFactory determineTargetRabbitSessionSource(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new RabbitMqException("Cannot determine target RabbitMqRealSessionFactory for null key!");
		}
		Assert.notNull(this.resolvedRabbitSessionSource, "Sessionection router not initialized");
		RabbitMqRealSessionFactory rabbitSessionSource = this.resolvedRabbitSessionSource.get(key);
		if (rabbitSessionSource == null) {
			rabbitSessionSource = this.resolvedDefaultRabbitSessionSource;
		}
		if (rabbitSessionSource == null) {
			throw new RabbitMqException("Cannot determine target RabbitMqRealSessionFactory for lookup key [" + key + "]");
		} else {
			return rabbitSessionSource;
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
