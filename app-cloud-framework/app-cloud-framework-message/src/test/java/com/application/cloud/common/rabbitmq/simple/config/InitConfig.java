package com.application.cloud.common.rabbitmq.simple.config;

import com.application.cloud.common.rabbitmq.simple.notice.BusinessConfirmCallback;
import com.application.cloud.common.rabbitmq.simple.notice.BusinessReturnCallback;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：admin
 * @date ：2021-3-31
 * @description:
 * @modified By：
 * @version: 1.0.0
 */
@Configuration
@AllArgsConstructor
public class InitConfig implements InitializingBean {
	
	@Autowired
	@Qualifier(value = "defaultRabbitTemplate")
	private RabbitTemplate defaultRabbitTemplate;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		defaultRabbitTemplate.setConfirmCallback(new BusinessConfirmCallback());
		defaultRabbitTemplate.setReturnCallback(new BusinessReturnCallback());
	}
}
