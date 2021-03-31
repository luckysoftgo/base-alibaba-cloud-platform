package com.application.cloud.common;

import com.application.cloud.common.redis.configure.EnableRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author ：admin
 * @description:
 * @modified By：
 * @version: 1.0.0
 */
@EnableRedis
@SpringBootApplication
public class RedisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
}
