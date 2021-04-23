package com.application.cloud.common.redis.configure;

import com.application.cloud.common.redis.service.RedisService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author ：admin
 * @date ：2021/3/25
 * @description : redis配置项,配置信息用 springboot 提供的,不要再重复造轮子
 * <p>
 * spring:
 * redis:
 * ##单机配置信息(单机配置下打开,并关闭哨兵配置)
 * host: 10.128.202.144
 * port: 6379
 * ##单机模式设置单机模式的密码;哨兵模式设置哨兵模式的密码
 * password: 123456
 * #采用哪个数据库
 * database: 0
 * ##哨兵配置信息(哨兵redis配置下打开,并关闭单机配置)
 * #sentinel:
 * #master: mymaster
 * #nodes: 10.128.202.56:26380,10.128.202.57:26380,10.128.202.65:26380
 * ##连接池信息:
 * pool:
 * #连接池最大连接数,默认8个，（使用负值表示没有限制）
 * maxTotal: 8
 * #连接池中的最大空闲连接
 * maxIdle: 8
 * #连接池最大阻塞等待时间（使用负值表示没有限制）
 * maxWait: 5000
 * #连接池中的最小空闲连接
 * minIdle: 1
 * #在从对象池获取对象时是否检测对象有效
 * testOnBorrow: true
 * @modified By：
 * @version: ： 1.0.0
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    
    @Bean(name = "redisOperClient")
    public RedisService redisOperClient(RedisTemplate<Object, Object> redisTemplate) {
        RedisService redisOperClient = new RedisService(redisTemplate);
        return redisOperClient;
    }
    
    /**
     * 不会序列化 key
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        template.setEnableTransactionSupport(true);
        return template;
    }
    
}

