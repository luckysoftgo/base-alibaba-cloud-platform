package com.application.cloud.osscloud;

import com.application.cloud.common.security.annotation.EnableCloudFeignClients;
import com.application.cloud.common.security.annotation.EnableCustomConfig;
import com.application.cloud.common.swagger.annotation.EnableCustomSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 启动服务
 * @author : 孤狼
 * @NAME: AppCloudOSSApplication
 * @DESC: AppCloudOSSApplication 类设计
 **/
@Slf4j
@EnableCustomConfig
@EnableCustomSwagger2
@EnableCloudFeignClients
@SpringCloudApplication
public class AppCloudOSSApplication {
	public static void main(String[] args) {
		
		log.info("**************************文件系统服务开始启动 ************************** ");
		SpringApplication.run(AppCloudOSSApplication.class, args);
		log.info("**************************文件系统服务启动完成 ************************** ");
	}
}
