package com.application.cloud.auth;

import com.application.cloud.common.security.annotation.EnableCloudFeignClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 认证授权中心
 * 
 * @author admin
 */
@Slf4j
@EnableCloudFeignClients
@SpringCloudApplication
public class AppCloudAuthApplication
{
    public static void main(String[] args){
	   log.info("************************** 认证服务开始启动 ************************** ");
	   SpringApplication.run(AppCloudAuthApplication.class, args);
	   log.info("************************** 认证服务启动完成 ************************** ");
    }
}
