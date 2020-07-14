package com.application.cloud.visual.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 监控中心
 * 
 * @author admin
 */
@EnableAdminServer
@SpringCloudApplication
public class AppCloudMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppCloudMonitorApplication.class, args);
    }
}
