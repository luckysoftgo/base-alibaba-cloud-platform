package com.application.cloud.common.rabbitmq.core.rabbitmq.factory;

import com.application.cloud.common.rabbitmq.boot.autoconfigure.DynamicRabbitConnSource;
import com.application.cloud.common.rabbitmq.boot.config.RabbitMqPoolConfig;
import com.rabbitmq.client.Connection;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ：孤狼
 * @description: 连接池工厂
 * @modified By：
 * @version: 1.0.0
 */
public class RabbitMqConnPoolFactory extends BasePooledObjectFactory<Connection> {
	/**
	 * 配置项名称
	 */
	private AtomicReference<RabbitMqPoolConfig> nodesReference = new AtomicReference<RabbitMqPoolConfig>();
	/**
	 * 动态获取的配置项
	 */
	private DynamicRabbitConnSource rabbitConnSource;
	
	public RabbitMqConnPoolFactory(RabbitMqPoolConfig rabbitMqPoolConfig, DynamicRabbitConnSource rabbitConnSource) {
		this.nodesReference.set(rabbitMqPoolConfig);
		this.rabbitConnSource = rabbitConnSource;
	}
	
	@Override
	public Connection create() throws Exception {
		RabbitMqPoolConfig mqConfig = nodesReference.get();
		return rabbitConnSource.determineTargetRabbitConnSource(mqConfig.getInstanceName());
	}
	
	@Override
	public PooledObject<Connection> wrap(Connection conn) {
		//包装实际对象
		return new DefaultPooledObject<>(conn);
	}
	
	public DynamicRabbitConnSource getRabbitConnSource() {
		return rabbitConnSource;
	}
	
	public void setRabbitConnSource(DynamicRabbitConnSource rabbitConnSource) {
		this.rabbitConnSource = rabbitConnSource;
	}
}
