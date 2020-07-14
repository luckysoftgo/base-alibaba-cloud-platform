package com.application.cloud.system;

import com.application.cloud.common.security.annotation.EnableCustomConfig;
import com.application.cloud.common.security.annotation.EnableRyFeignClients;
import com.application.cloud.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 系统模块
 * 
 * @author admin
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class AppCloudSystemApplication
{
    public static void main(String[] args) {
	    SpringApplication.run(AppCloudSystemApplication.class, args);
    }
}
