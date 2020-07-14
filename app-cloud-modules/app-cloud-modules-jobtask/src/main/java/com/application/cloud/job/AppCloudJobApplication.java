package com.application.cloud.job;

import com.application.cloud.common.security.annotation.EnableCustomConfig;
import com.application.cloud.common.security.annotation.EnableRyFeignClients;
import com.application.cloud.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 定时任务
 * 
 * @author admin
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class AppCloudJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppCloudJobApplication.class, args);
       
    }
}
