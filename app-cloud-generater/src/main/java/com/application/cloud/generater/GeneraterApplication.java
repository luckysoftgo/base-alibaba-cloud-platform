package com.application.cloud.generater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author : 孤狼
 * @NAME: GeneraterApplication
 * @DESC: GeneraterApplication 实现设计
 **/
//@SpringBootApplication
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//@MapperScan("com.application.cloud.**.dao")
public class GeneraterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneraterApplication.class, args);
	}
}
