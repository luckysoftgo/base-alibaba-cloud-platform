package com.application.cloud.gen;

import com.application.cloud.common.security.annotation.EnableCustomConfig;
import com.application.cloud.common.security.annotation.EnableCloudFeignClients;
import com.application.cloud.common.swagger.annotation.EnableCustomSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 代码生成
 * 
 * @author admin
 */
@Slf4j
@EnableCustomConfig
@EnableCustomSwagger2
@EnableCloudFeignClients
@SpringCloudApplication
public class AppCloudGenApplication
{
    public static void main(String[] args)
    {
	    log.info("**************************代码生成服务开始启动 ************************** ");
	    SpringApplication.run(AppCloudGenApplication.class, args);
	    log.info("**************************代码生成服务启动完成 ************************** ");
    }
}
