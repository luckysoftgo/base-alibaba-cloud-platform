package com.application.cloud.common.rabbitmq.boot.config;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：孤狼
 * @description: rabbitmq 连接池配置类
 * @modified By：
 * @version: 1.0.0
 */
@Data
@ConfigurationProperties(prefix = RabbitMqPoolConfig.PREFIX)
public class RabbitMqPoolConfig extends GenericObjectPoolConfig {
	
	/**
	 * 配置信息.
	 */
	public static final String PREFIX = "spring.rabbitmq.dynamic";
	
	/**
	 * 连接池的配置名称
	 */
	private String instanceName;
	/**
	 * GenericObjectPoolConfig 池中最大链接数，默认是8
	 */
	private int maxTotal = 20;
	
	/**
	 * GenericObjectPoolConfig 池中最大空闲链接数目，默认是8
	 */
	private int maxIdle = 8;
	
	/**
	 * GenericObjectPoolConfig 池中最小空闲链接数目，默认为0
	 */
	private int minIdle = 2;
	
	/**
	 * BaseObjectPoolConfig 当链接池资源耗尽时，等待时间，超出抛异常，默认为-1，阻塞。
	 */
	private long maxWaitMillis = 18000;
	
	/**
	 * BaseObjectPoolConfig 默认是false，创建一个链接时检测是否链接有效，无效则剔除，并尝试继续获取新链接。
	 */
	private boolean testOnCreate = true;
	/**
	 * BaseObjectPoolConfig 默认是false，借取一个链接时检测是否有效，无效则剔除，并尝试继续获取新链接。
	 */
	private boolean testOnBorrow = true;
	/**
	 * BaseObjectPoolConfig 默认为false，归还一个对象时检测是否有效，无效则不放入链接池内。
	 */
	private boolean testOnReturn = true;
	/**
	 * BaseObjectPoolConfig 默认为false，指明空闲链接是否需要被【空闲链接回收器】【evict方法】检测，检测出链接无效则被移除。
	 */
	private boolean testWhileIdle = true;
	/**
	 * BaseObjectPoolConfig .空闲链接回收器运行的周期，单位ms，默认-1，永不执行检测。
	 */
	private long timeBetweenEvictionRunsMillis = 60000;
	/**
	 * BaseObjectPoolConfig 空闲链接回收器运行时检查的空闲链接数量，默认是3个。
	 */
	private int numTestsPerEvictionRun = -1;
	
}
