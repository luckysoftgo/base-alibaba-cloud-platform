package com.application.cloud.generater.mongo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author : 孤狼
 * @NAME: MongoCondition
 * @DESC: MongoCondition类设计
 **/
@Slf4j
public class MongoNullCondition implements Condition {
	
	/**
	 * 条件中是否配置的有mongodb的配置,如果有就加载,否则就不加载.
	 * @param context
	 * @param metadata
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String database = context.getEnvironment().getProperty("generater.database");
		log.info("读取generater.database的配置信息：{}",database);
		return !"mongodb".equalsIgnoreCase(database);
	}
}