package com.application.cloud.auth;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author : 孤狼
 * @NAME: AuthClientApplication
 * @DESC: AuthClientApplication类设计
 **/
@SpringCloudApplication
@EnableResourceServer
@EnableCaching
@EnableOAuth2Client
@EnableFeignClients
public class AuthClientApplication {

}
