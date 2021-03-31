package com.application.cloud.common.redis.configure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：admin
 * @description: 是否启用redis消息
 * @modified By：
 * @version: 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisConfig.class)
public @interface EnableRedis {

}
