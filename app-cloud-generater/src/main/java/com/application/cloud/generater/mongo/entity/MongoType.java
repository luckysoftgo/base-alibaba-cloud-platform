package com.application.cloud.generater.mongo.entity;

import java.util.Objects;

/**
 * @author : 孤狼
 * @NAME: MongoType
 * @DESC: MongoType类设计
 **/
public enum  MongoType {
	
	/***
	 * 类型 和对应mongodb api 的$type的数字
	 **/
	DOUBLE(1),
	VARCHAR(2),
	NUMBER(16),
	BIGINT(18),
	OBJECT(3),
	ARRAY(4),
	DATE(9),
	BIT(8),
	;
	
	private final int num;
	
	MongoType(int num) {
		this.num = num;
	}
	
	public static String typeInfo(int num) {
		MongoType[] values = values();
		for (MongoType value : values) {
			if (Objects.equals(num, value.num)) {
				return value.toString();
			}
		}
		return null;
	}
}
