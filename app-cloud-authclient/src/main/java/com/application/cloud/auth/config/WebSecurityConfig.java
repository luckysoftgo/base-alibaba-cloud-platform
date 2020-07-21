package com.application.cloud.auth.config;

import com.application.cloud.auth.integration.IntegrationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security 安全认证相关配置
 * Oauth2依赖于Security 默认情况下WebSecurityConfig执行比ResourceServerConfig优先
 * @author cloud
 */
@Order(99)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private IntegrationUserDetailsService integrationUserDetailsService;
	
	/**
	 * 认证的加密方式.
	 * @return
	 */
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(integrationUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
		//过滤的配置
        http
        .authorizeRequests()
        .antMatchers(
            "/actuator/**",
            "/oauth/*",
            "/token/**")
		.permitAll()
        .anyRequest()
		.authenticated()
        .and()
		.csrf()
		.disable();
    
    }
}
