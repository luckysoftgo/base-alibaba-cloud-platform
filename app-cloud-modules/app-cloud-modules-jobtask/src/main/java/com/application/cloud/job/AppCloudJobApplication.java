package com.application.cloud.job;

import com.application.cloud.common.security.annotation.EnableCustomConfig;
import com.application.cloud.common.security.annotation.EnableCloudFeignClients;
import com.application.cloud.common.swagger.annotation.EnableCustomSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 定时任务
 * 
 * @author admin
 */
@Slf4j
@EnableCustomConfig
@EnableCustomSwagger2
@EnableCloudFeignClients
@SpringCloudApplication
public class AppCloudJobApplication
{
    public static void main(String[] args)
    {
	    log.info("**************************定时任务服务开始启动 ************************** ");
	    SpringApplication.run(AppCloudJobApplication.class, args);
	    log.info("**************************定时任务服务启动完成 ************************** ");
    }
}
