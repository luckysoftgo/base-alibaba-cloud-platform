package com.application.cloud.common.rabbitmq.complex.boot;

import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitConnSource;
import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitConnSourceProperties;
import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitSessionSource;
import com.application.cloud.common.rabbitmq.complex.boot.autoconfigure.DynamicRabbitTempSource;
import com.application.cloud.common.rabbitmq.complex.boot.config.RabbitMqPoolConfig;
import com.application.cloud.common.rabbitmq.complex.boot.config.RabbitMqProperty;
import com.application.cloud.common.rabbitmq.complex.boot.tool.BootBeanGetter;
import com.application.cloud.common.rabbitmq.complex.boot.tool.BootBeanSetter;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.factory.RabbitMqRealSessionFactory;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.factory.RabbitMqRealTemplateFactory;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.pool.RabbitMqConnPool;
import com.application.cloud.common.rabbitmq.complex.core.rabbitmq.pool.RabbitMqTempPool;
import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ：孤狼
 * @description: 自动配置和装置类
 * @modified By：
 * @version: 1.0.0
 */
@Configuration
@EnableConfigurationProperties({DynamicRabbitConnSourceProperties.class, RabbitMqPoolConfig.class})
@ConditionalOnClass(DynamicRabbitConnSource.class)
public class DynamicDataSourceAutoConfiguration {
	
	@Autowired
	private DynamicRabbitConnSourceProperties dynamicDataSourceProperties;
	
	/**
	 * 创建基础数据源
	 *
	 * @param rabbitMqConfig 数据源参数
	 * @return 数据源
	 */
	private Connection createConnSource(RabbitMqProperty rabbitMqConfig) {
		try {
			com.rabbitmq.client.ConnectionFactory connection = new com.rabbitmq.client.ConnectionFactory();
			if (!StringUtils.isEmpty(rabbitMqConfig.getUsername())) {
				connection.setUsername(rabbitMqConfig.getUsername());
			}
			if (!StringUtils.isEmpty(rabbitMqConfig.getPassword())) {
				connection.setPassword(rabbitMqConfig.getPassword());
			}
			if (!StringUtils.isEmpty(rabbitMqConfig.getVirtualHost())) {
				connection.setVirtualHost(rabbitMqConfig.getVirtualHost());
			}
			Integer authPort = rabbitMqConfig.getPort() == 0 ? 5672 : rabbitMqConfig.getPort();
			connection.setPort(authPort);
			if (StringUtils.isEmpty(rabbitMqConfig.getAddresses())) {
				new RabbitMqException("配置文件中,必须要有配置的有addresses配置信息");
			}
			String[] array = rabbitMqConfig.getAddresses().split("\\,");
			Address[] addresses = new Address[array.length];
			for (int i = 0; i < array.length; i++) {
				String address = array[i];
				if (address.contains(":")) {
					String[] hostPort = address.split("\\:");
					addresses[i] = new Address(hostPort[0], Integer.parseInt(hostPort[1]));
				} else {
					addresses[i] = new Address(address, 5672);
				}
			}
			return connection.newConnection(addresses);
		} catch (Exception e) {
			throw new RabbitMqException("dynamic-rabbitmq create basic connection named " + rabbitMqConfig.getInstance() + " error");
		}
	}
	
	/**
	 * 创建使用的bean对象
	 *
	 * @return
	 */
	@Order(-1)
	@Bean(name = "dynamicRabbitConnSource")
	@ConditionalOnMissingBean(DynamicRabbitConnSource.class)
	public DynamicRabbitConnSource createRabbitConnSource() {
		Map<String, Connection> targetRabbitConnSource = new LinkedHashMap<>();
		Set<String> keys = dynamicDataSourceProperties.getConnections().keySet();
		Map<String, RabbitMqProperty> mqPropMap = dynamicDataSourceProperties.getConnections();
		for (String instance : keys) {
			RabbitMqProperty rabbitMqConfig = mqPropMap.get(instance);
			if (rabbitMqConfig != null) {
				rabbitMqConfig.setInstance(instance);
				targetRabbitConnSource.put(instance, createConnSource(rabbitMqConfig));
			}
		}
		DynamicRabbitConnSource dataSource = new DynamicRabbitConnSource();
		dataSource.setTargetRabbitConnSource(targetRabbitConnSource);
		dataSource.afterPropertiesSet();
		return dataSource;
	}
	
	/**
	 * 创建使用的RabbitTemplate对象
	 *
	 * @return
	 */
	@Order(50)
	@Bean(name = "dynamicRabbitTemplateSource")
	@ConditionalOnMissingBean(DynamicRabbitTempSource.class)
	public DynamicRabbitTempSource createRabbitTemplateSource() {
		Map<String, RabbitMqRealTemplateFactory> targetRabbitTempSource = new LinkedHashMap<>();
		Set<String> keys = dynamicDataSourceProperties.getConnections().keySet();
		Map<String, RabbitMqProperty> mqPropMap = dynamicDataSourceProperties.getConnections();
		for (String instance : keys) {
			RabbitMqProperty rabbitMqConfig = mqPropMap.get(instance);
			if (rabbitMqConfig != null) {
				rabbitMqConfig.setInstance(instance);
			}
			RabbitMqPoolConfig poolConfig = rabbitMqConfig.getPool();
			poolConfig.setInstanceName(instance);
			//获得bean对象
			RabbitMqTempPool tempPool = new RabbitMqTempPool(poolConfig, rabbitMqConfig);
			//实例对象.
			RabbitMqRealTemplateFactory templateFactory = new RabbitMqRealTemplateFactory(tempPool);
			targetRabbitTempSource.put(instance, templateFactory);
		}
		DynamicRabbitTempSource dataSource = new DynamicRabbitTempSource();
		dataSource.setTargetRabbitTemplateSource(targetRabbitTempSource);
		dataSource.afterPropertiesSet();
		return dataSource;
	}
	
	/**
	 * 创建使用的工厂对象
	 *
	 * @return
	 */
	@Bean
	@Order(100)
	@ConditionalOnMissingBean(DynamicRabbitSessionSource.class)
	public DynamicRabbitSessionSource createRabbitSessionSource() {
		Map<String, RabbitMqRealSessionFactory> targetRabbitConnSource = new LinkedHashMap<>();
		Set<String> keys = dynamicDataSourceProperties.getConnections().keySet();
		Map<String, RabbitMqProperty> mqPropMap = dynamicDataSourceProperties.getConnections();
		for (String instance : keys) {
			RabbitMqProperty rabbitMqConfig = mqPropMap.get(instance);
			if (rabbitMqConfig != null) {
				rabbitMqConfig.setInstance(instance);
			}
			RabbitMqPoolConfig poolConfig = rabbitMqConfig.getPool();
			poolConfig.setInstanceName(instance);
			//获得bean对象
			DynamicRabbitConnSource rabbitConnSource = BootBeanGetter.getClassBean(DynamicRabbitConnSource.class);
			RabbitMqConnPool connPool = new RabbitMqConnPool(poolConfig, rabbitConnSource);
			//实例对象.
			RabbitMqRealSessionFactory sessionFactory = new RabbitMqRealSessionFactory(connPool, rabbitConnSource);
			String instanceName = instance + "RabbitMqRealSessionFactory";
			BootBeanSetter.registerBean(instanceName, RabbitMqRealSessionFactory.class, connPool);
			targetRabbitConnSource.put(instance, sessionFactory);
		}
		DynamicRabbitSessionSource dataSource = new DynamicRabbitSessionSource();
		dataSource.setTargetRabbitSessionSource(targetRabbitConnSource);
		dataSource.afterPropertiesSet();
		return dataSource;
	}
}
