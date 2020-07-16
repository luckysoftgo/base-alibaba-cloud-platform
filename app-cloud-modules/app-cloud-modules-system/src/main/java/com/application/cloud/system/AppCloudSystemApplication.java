package com.application.cloud.system;

import com.application.cloud.common.security.annotation.EnableCustomConfig;
import com.application.cloud.common.security.annotation.EnableCloudFeignClients;
import com.application.cloud.common.swagger.annotation.EnableCustomSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 系统模块
 * 
 * @author admin
 */
@Slf4j
@EnableCustomConfig
@EnableCustomSwagger2
@EnableCloudFeignClients
@SpringCloudApplication
public class AppCloudSystemApplication
{
    public static void main(String[] args) {
	    log.info("**************************系统访问服务开始启动 ************************** ");
	    SpringApplication.run(AppCloudSystemApplication.class, args);
	    log.info("**************************系统访问服务启动完成 ************************** ");
    }
}
