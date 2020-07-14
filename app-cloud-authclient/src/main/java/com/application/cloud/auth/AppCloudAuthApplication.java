package com.application.cloud.auth;

import com.application.cloud.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 认证授权中心
 * 
 * @author admin
 */
@EnableRyFeignClients
@SpringCloudApplication
public class AppCloudAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppCloudAuthApplication.class, args);
    }
}
