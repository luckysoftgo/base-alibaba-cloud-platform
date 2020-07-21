package com.application.cloud.common.security.access;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启安全访问，根据 security 中权限进行判断
 * @author : 孤狼
 * @NAME: EnableSecurityAccess
 * @DESC: EnableSecurityAccess 类设计
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SecurityAccessAutoConfiguration.class)
public @interface EnableSecurityAccess {
	
	//只要不启用这个配置,就不会用到配置相关的属性
	
}
