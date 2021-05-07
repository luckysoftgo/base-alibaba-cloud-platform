package com.application.cloud.common.rabbitmq.simple.config;

import com.application.cloud.common.rabbitmq.exception.RabbitMqException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

/**
 * @author ：admin
 * @description: rabbitmq的封装
 * @modified By：
 * @version: 1.0.0
 */
@EnableRabbit
@Configuration
public class RabbitMqConfig {
	
	/**
	 * 定义连接rabbitmq
	 */
	@Primary
	@Bean(name = "defaultConnectionFactory")
	@ConditionalOnProperty(prefix = "spring.rabbitmq.default", value = {"enabled"}, havingValue = "true")
	public ConnectionFactory defaultConnectionFactory(
			@Value("${spring.rabbitmq.default.addresses}") String addresses,
			@Value("${spring.rabbitmq.default.username}") String username,
			@Value("${spring.rabbitmq.default.password}") String password,
			@Value("${spring.rabbitmq.default.virtualHost}") String virtualHost) {
		return connectionFactory(addresses, username, password, virtualHost);
	}
	
	@Primary
	@Bean(name = "defaultRabbitTemplate")
	public RabbitTemplate defaultRabbitTemplate(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory) {
		return createRabbitTemplate(connectionFactory);
	}
	
	@Bean(name = "defaultListenerContainerFactory")
	public SimpleRabbitListenerContainerFactory defaultListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
	                                                                            @Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory) {
		return createSimpleRabbitListenerContainerFactory(configurer, connectionFactory);
	}
	
	/**
	 * 定义连接rabbitmq
	 */
	@Bean(name = "firstConnectionFactory")
	@ConditionalOnProperty(prefix = "spring.rabbitmq.first", value = {"enabled"}, havingValue = "true")
	public ConnectionFactory firstConnectionFactory(
			@Value("${spring.rabbitmq.first.addresses}") String addresses,
			@Value("${spring.rabbitmq.first.username}") String username,
			@Value("${spring.rabbitmq.first.password}") String password,
			@Value("${spring.rabbitmq.first.virtualHost}") String virtualHost) {
		return connectionFactory(addresses, username, password, virtualHost);
	}
	
	@Bean(name = "firstRabbitTemplate")
	@ConditionalOnBean(name = {"firstConnectionFactory"})
	public RabbitTemplate firstRabbitTemplate(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
		return createRabbitTemplate(connectionFactory);
	}
	
	@Bean(name = "firstListenerContainerFactory")
	@ConditionalOnBean(name = {"firstConnectionFactory"})
	public SimpleRabbitListenerContainerFactory firstListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
	                                                                          @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
		return createSimpleRabbitListenerContainerFactory(configurer, connectionFactory);
	}
	
	/**
	 * 定义连接rabbitmq
	 */
	@ConditionalOnProperty(prefix = "spring.rabbitmq.second", value = {"enabled"}, havingValue = "true")
	@Bean(name = "secondConnectionFactory")
	public ConnectionFactory secondConnectionFactory(
			@Value("${spring.rabbitmq.second.addresses}") String addresses,
			@Value("${spring.rabbitmq.second.username}") String username,
			@Value("${spring.rabbitmq.second.password}") String password,
			@Value("${spring.rabbitmq.second.virtualHost}") String virtualHost) {
		return connectionFactory(addresses, username, password, virtualHost);
	}
	
	@Bean(name = "secondRabbitTemplate")
	@ConditionalOnBean(name = {"secondConnectionFactory"})
	public RabbitTemplate secondRabbitTemplate(@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory) {
		return createRabbitTemplate(connectionFactory);
	}
	
	@Bean(name = "secondListenerContainerFactory")
	@ConditionalOnBean(name = {"secondConnectionFactory"})
	public SimpleRabbitListenerContainerFactory secondListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
	                                                                           @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory) {
		return createSimpleRabbitListenerContainerFactory(configurer, connectionFactory);
	}
	
	/**
	 * connectionFactory 的配置信息
	 *
	 * @param addresses：host1:port1,host2:port2,host3:port3 必须传递的参数
	 * @param username：链接用户名
	 * @param password：链接密码
	 * @param virtualHost：访问host
	 * @return
	 */
	public CachingConnectionFactory connectionFactory(String addresses, String username, String password, String virtualHost) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		if (StringUtils.isEmpty(addresses)) {
			throw new RabbitMqException("消息主机的链接地址和端口配置没有指定");
		} else {
			connectionFactory.setAddresses(addresses);
		}
		if (!StringUtils.isEmpty(username)) {
			connectionFactory.setUsername(username);
		}
		if (!StringUtils.isEmpty(password)) {
			connectionFactory.setPassword(password);
		}
		if (!StringUtils.isEmpty(virtualHost)) {
			connectionFactory.setVirtualHost(virtualHost);
		}
		//生产端配置
		// 发布返回
		connectionFactory.setPublisherReturns(true);
		// 开启发布确认,就是confirm模式. 消费端ack应答后,才将消息从队列中删除
		connectionFactory.setPublisherConfirms(true);
		return connectionFactory;
	}
	
	/**
	 * 创建RabbitTemplate对象.
	 *
	 * @param connectionFactory
	 * @return
	 */
	private RabbitTemplate createRabbitTemplate(@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory) {
		RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory);
		//模板配置
		//设置为 true 后 消费者在消息没有被路由到合适队列情况下会被return监听，而不会自动删除
		secondRabbitTemplate.setMandatory(true);
		return secondRabbitTemplate;
	}
	
	/**
	 * 创建监听容器工厂
	 *
	 * @param configurer
	 * @param connectionFactory
	 * @return
	 */
	private SimpleRabbitListenerContainerFactory createSimpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		//设置当前的消费者数量
		factory.setConcurrentConsumers(1);
		//设置当前的消费者数量上限数量
		factory.setMaxConcurrentConsumers(5);
		//在单个请求中处理的消息个数，他应该大于等于事务数量(unack的最大数量)
		factory.setPrefetchCount(1);
		//设置是否重回队列
		factory.setDefaultRequeueRejected(true);
		//设置手动签收 --》 这里指定确认方式 AUTO为自动ack ; MANUAL为手动ack
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		configurer.configure(factory, connectionFactory);
		return factory;
	}
	
	
	/**
	 * 声明交换机 amq.topic topic类型的，其他类型的直接改TopicExchange
	 */
	@Bean
	public TopicExchange basicExchange() {
		return new TopicExchange("amq.topic", true, false);
	}
	
	/**
	 * 声明消息队列 启动创建，停止自动删除
	 */
	@Bean(name = "basicQueue")
	public Queue basicQueue() {
		return new Queue("basic.Queue", false, true, true);
	}
	
	/**
	 * 队列绑定交换机 *.*.*.*.*.*为rountingkey
	 */
	@Bean
	public Binding basicBinding() {
		return BindingBuilder.bind(basicQueue()).to(basicExchange()).with("*.*.*.*.*.*");
	}
}
