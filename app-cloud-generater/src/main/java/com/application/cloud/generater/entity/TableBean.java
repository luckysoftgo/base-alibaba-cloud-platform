package com.application.cloud.generater.entity;

import java.util.List;

/**
 * 表数据
 * @author : 孤狼
 * @NAME: GeneratorDao
 * @DESC: GeneratorDao 接口设计
 **/
public class TableBean {
	/**
	 * 表的名称
	 */
	private String tableName;
	/**
	 * 表的备注
	 */
	private String tableComment;
	/**
	 * 表的主键
	 */
	private ColumnBean primaryKey;
	/**
	 * 表的列名(不包含主键)
	 */
	private List<ColumnBean> tableColumns;
	/**
	 * 所有表的列名
	 */
	private List<ColumnBean> allTableColumns;
	/**
	 * 类名(第一个字母大写)，如：sys_user => SysUser
	 */
	private String upperClassName;
	/**
	 * 类名(第一个字母小写)，如：sys_user => sysUser
	 */
	private String lowerClassName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public ColumnBean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(ColumnBean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<ColumnBean> getTableColumns() {
		return tableColumns;
	}

	public void setTableColumns(List<ColumnBean> tableColumns) {
		this.tableColumns = tableColumns;
	}
	
	public List<ColumnBean> getAllTableColumns() {
		return allTableColumns;
	}
	
	public void setAllTableColumns(List<ColumnBean> allTableColumns) {
		this.allTableColumns = allTableColumns;
	}
	
	public String getUpperClassName() {
		return upperClassName;
	}

	public void setUpperClassName(String upperClassName) {
		this.upperClassName = upperClassName;
	}

	public String getLowerClassName() {
		return lowerClassName;
	}

	public void setLowerClassName(String lowerClassName) {
		this.lowerClassName = lowerClassName;
	}
}
