package com.application.cloud.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关启动程序
 * 
 * @author admin
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AppCloudGatewayApplication
{
    public static void main(String[] args)
    {
	
	    log.info("************************** 网关服务开始启动 ************************** ");
	    SpringApplication.run(AppCloudGatewayApplication.class, args);
	    log.info("************************** 网关服务启动完成 ************************** ");
     
    }
}