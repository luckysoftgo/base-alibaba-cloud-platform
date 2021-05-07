package com.application.cloud.common.rabbitmq.simple.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：admin
 * @description: 是否启用rabbitmq消息
 *  如果启动的配置类在：com.application.cloud 下就可以不用这个注解;否则就需要使用这个注解.
 * @modified By：
 * @version: 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RabbitMqConfig.class)
public @interface EnableRabbitMQ {

}
