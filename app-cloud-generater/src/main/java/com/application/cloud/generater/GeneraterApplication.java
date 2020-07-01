package com.application.cloud.generater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author : 孤狼
 * @NAME: GeneraterApplication
 * @DESC: GeneraterApplication 实现设计:生成mongo 时候,使用@SpringBootApplication 注解,否则使用默认
 **/
//@SpringBootApplication
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class GeneraterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneraterApplication.class, args);
	}
}
