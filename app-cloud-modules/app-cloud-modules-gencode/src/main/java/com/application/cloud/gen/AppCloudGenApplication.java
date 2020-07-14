package com.application.cloud.gen;

import com.application.cloud.common.security.annotation.EnableCustomConfig;
import com.application.cloud.common.security.annotation.EnableRyFeignClients;
import com.application.cloud.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 代码生成
 * 
 * @author admin
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class AppCloudGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppCloudGenApplication.class, args);
    }
}
