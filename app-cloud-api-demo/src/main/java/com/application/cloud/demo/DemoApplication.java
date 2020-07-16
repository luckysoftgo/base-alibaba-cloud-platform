package com.application.cloud.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : 孤狼
 * @NAME: DemoApplication
 * @DESC: DemoApplication类设计
 **/
@Slf4j
@SpringBootApplication
public class DemoApplication {
	
	public static void main(String[] args) {
		
		log.info("************************** 测试服务开始启动 ************************** ");
		SpringApplication.run(DemoApplication.class, args);
		log.info("************************** 测试服务启动完成 ************************** ");
	}
}
