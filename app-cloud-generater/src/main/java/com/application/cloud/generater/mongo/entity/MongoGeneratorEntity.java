package com.application.cloud.generater.mongo.entity;

import com.application.cloud.generater.entity.TableBean;

import java.util.List;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: MongoGeneratorEntity
 * @DESC: MongoGeneratorEntity类设计
 **/
public class MongoGeneratorEntity {
	
	/***表信息**/
	private Map<String, String> tableInfo;
	/***主类的列名信息**/
	private List<Map<String, String>> columns;
	
	public TableBean toTableEntity() {
		TableBean tableEntity = new TableBean();
		Map<String, String> tableInfo = this.tableInfo;
		tableEntity.setTableName(tableInfo.get("tableName"));
		tableEntity.setTableComment("");
		return tableEntity;
	}
	
	
	public Map<String, String> getTableInfo() {
		return tableInfo;
	}
	
	public MongoGeneratorEntity setTableInfo(Map<String, String> tableInfo) {
		this.tableInfo = tableInfo;
		return this;
	}
	
	public List<Map<String, String>> getColumns() {
		return columns;
	}
	
	public MongoGeneratorEntity setColumns(List<Map<String, String>> columns) {
		this.columns = columns;
		return this;
	}
	
}