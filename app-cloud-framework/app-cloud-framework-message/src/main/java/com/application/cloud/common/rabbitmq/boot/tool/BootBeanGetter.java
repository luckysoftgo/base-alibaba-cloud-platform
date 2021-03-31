package com.application.cloud.common.rabbitmq.boot.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ：孤狼
 * @description: 获取bean实例
 * @modified By：
 * @version: 1.0.0
 */
@Component
public class BootBeanGetter implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		setApplicationContextStatic(applicationContext);
	}
	
	public static <T> T getClassBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}
	
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static void setApplicationContextStatic(final ApplicationContext applicationContext) {
		BootBeanGetter.applicationContext = applicationContext;
	}
	
}
