package com.application.cloud.common.rabbitmq.complex.boot.tool;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ：孤狼
 * @description: 动态注册bean到ApplicationContext中去
 * https://blog.csdn.net/ttyy1112/article/details/91462179
 * @modified By：
 * @version: 1.0.0
 */
@Component
public class BootBeanSetter implements ApplicationContextAware {
	
	/**
	 * 配置bean实例.
	 */
	private static ConfigurableApplicationContext applicationContext;
	
	public BootBeanSetter(ConfigurableApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 动态将bean放入
	 *
	 * @param name
	 * @param clazz
	 * @param args
	 */
	public static void registerBean(String name, Class clazz, Object... args) {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		if (args.length > 0) {
			for (Object arg : args) {
				beanDefinitionBuilder.addConstructorArgValue(arg);
			}
		}
		BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
		BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
		//注册bean对象.
		beanFactory.registerBeanDefinition(name, beanDefinition);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BootBeanSetter.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
}
