package com.application.cloud.common.rabbitmq.boot.autoconfigure;


import com.application.cloud.common.rabbitmq.core.rabbitmq.factory.RabbitMqRealTemplateFactory;
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
 * @description: 动态Template操作配置
 * @modified By：
 * @version: 1.0.0
 */
public class DynamicRabbitTempSource implements InitializingBean, DisposableBean {
	
	@Nullable
	public Map<String, RabbitMqRealTemplateFactory> targetRabbitTemplateSource;
	@Nullable
	private Object defaultTargetRabbitTemplateSource;
	@Nullable
	private Map<String, RabbitMqRealTemplateFactory> resolvedRabbitTemplateSource;
	@Nullable
	private RabbitMqRealTemplateFactory resolvedDefaultRabbitTemplateSource;
	
	public DynamicRabbitTempSource() {
	}
	
	@Nullable
	public Map<String, RabbitMqRealTemplateFactory> getTargetRabbitTemplateSource() {
		return targetRabbitTemplateSource;
	}
	
	public void setTargetRabbitTemplateSource(@Nullable Map<String, RabbitMqRealTemplateFactory> targetRabbitTemplateSource) {
		this.targetRabbitTemplateSource = targetRabbitTemplateSource;
	}
	
	@Nullable
	public Object getDefaultTargetRabbitTemplateSource() {
		return defaultTargetRabbitTemplateSource;
	}
	
	public void setDefaultTargetRabbitTemplateSource(@Nullable Object defaultTargetRabbitTemplateSource) {
		this.defaultTargetRabbitTemplateSource = defaultTargetRabbitTemplateSource;
	}
	
	@Nullable
	public Map<String, RabbitMqRealTemplateFactory> getResolvedRabbitTemplateSource() {
		return resolvedRabbitTemplateSource;
	}
	
	public void setResolvedRabbitTemplateSource(@Nullable Map<String, RabbitMqRealTemplateFactory> resolvedRabbitTemplateSource) {
		this.resolvedRabbitTemplateSource = resolvedRabbitTemplateSource;
	}
	
	@Nullable
	public RabbitMqRealTemplateFactory getResolvedDefaultRabbitTemplateSource() {
		return resolvedDefaultRabbitTemplateSource;
	}
	
	public void setResolvedDefaultRabbitTemplateSource(@Nullable RabbitMqRealTemplateFactory resolvedDefaultRabbitTemplateSource) {
		this.resolvedDefaultRabbitTemplateSource = resolvedDefaultRabbitTemplateSource;
	}
	
	@Override
	public void afterPropertiesSet() {
		if (this.targetRabbitTemplateSource == null) {
			throw new IllegalArgumentException("Property 'targetRabbitTemplateSource' is required");
		} else {
			this.resolvedRabbitTemplateSource = new HashMap(this.targetRabbitTemplateSource.size());
			this.targetRabbitTemplateSource.forEach((key, value) -> {
				String lookupKey = this.resolveSpecifiedLookupKey(key);
				RabbitMqRealTemplateFactory rabbitTemplateSource = this.resolveSpecifiedRabbitTemplateSource(value);
				this.resolvedRabbitTemplateSource.put(lookupKey, rabbitTemplateSource);
			});
			if (this.defaultTargetRabbitTemplateSource != null) {
				this.resolvedDefaultRabbitTemplateSource = this.resolveSpecifiedRabbitTemplateSource(this.defaultTargetRabbitTemplateSource);
			}
		}
	}
	
	protected String resolveSpecifiedLookupKey(Object lookupKey) {
		return Objects.toString(lookupKey, "");
	}
	
	protected RabbitMqRealTemplateFactory resolveSpecifiedRabbitTemplateSource(Object rabbitTemplateSource) throws IllegalArgumentException {
		if (rabbitTemplateSource instanceof RabbitMqRealTemplateFactory) {
			return (RabbitMqRealTemplateFactory) rabbitTemplateSource;
		} else {
			throw new IllegalArgumentException("Illegal data source value - only [com.rabbitmq.client.rabbitTemplate] supported: " + rabbitTemplateSource);
		}
	}
	
	public RabbitMqRealTemplateFactory getMasterTemplate() throws Exception {
		return this.determineTargetRabbitTemplateSource(this.determineCurrentLookupKey());
	}
	
	public RabbitMqRealTemplateFactory determineTargetRabbitTemplateSource(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new RabbitMqException("Cannot determine target RabbitMqRealTemplateFactory for null key!");
		}
		Assert.notNull(this.resolvedRabbitTemplateSource, "Sessionection router not initialized");
		RabbitMqRealTemplateFactory rabbitTemplateSource = this.resolvedRabbitTemplateSource.get(key);
		if (rabbitTemplateSource == null) {
			rabbitTemplateSource = this.resolvedDefaultRabbitTemplateSource;
		}
		if (rabbitTemplateSource == null) {
			throw new RabbitMqException("Cannot determine target RabbitMqRealTemplateFactory for lookup key [" + key + "]");
		} else {
			return rabbitTemplateSource;
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
