package com.application.cloud.common.rabbitmq.simple.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：admin
 * @description: 是否启用rabbitmq消息
 * @modified By：
 * @version: 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RabbitMqConfig.class)
public @interface EnableRabbitMQ {

}
