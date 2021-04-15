package com.application.cloud.common.redis.configure;

import com.application.cloud.common.redis.service.RedisService;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis配置
 *
 * @author cloud
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	
	@Bean(name = "redisOperClient")
	public RedisService redisOperClient(RedisTemplate<Object, Object> redisTemplate) {
		RedisService redisOperClient = new RedisService(redisTemplate);
		return redisOperClient;
	}
	
}
