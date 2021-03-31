package com.application.cloud.common.rabbitmq.simple;

import com.application.cloud.common.rabbitmq.simple.config.EnableRabbitMQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author ：admin
 * @description:
 * @modified By：
 * @version: 1.0.0
 */
@EnableRabbitMQ
@SpringBootApplication
public class MessageApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}
}
