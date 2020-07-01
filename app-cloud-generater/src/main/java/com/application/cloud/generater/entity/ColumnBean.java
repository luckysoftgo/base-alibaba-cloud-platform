package com.application.cloud.generater.entity;

/**
 * 列的属性
 * @author : 孤狼
 * @NAME: GeneratorDao
 * @DESC: GeneratorDao 接口设计
 **/
public class ColumnBean {
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 列名
	 */
    private String columnName;
	/**
	 * 对应的静态变量名
	 */
	private String staticFinalName;
	/**
	 * 字段排序号
	 */
	private Integer ordinalPosition;
	/**
	 * 列的默认值
	 */
	private String columnDefault;
	/**
	 * 是否是null
	 */
	private String isNullable;
	/**
	 * 列名类型
	 */
	private String dataType;
	/**
	 * 字符串的长度.
	 */
	private Integer characterMaximumLength;
	/**
	 * 数字类型的长度.
	 */
	private Integer numericPrecision;
	/**
	 * 字段的描述
	 */
	private String columnComment;
	/**
	 * 是否是主键-唯一主键：PRI,UNI
	 */
	private String columnKey;
	/**
	 * 属性名称(第一个字母大写)，如：user_name => UserName
	 */
	private String upperAttrName;
	/**
	 * 属性名称(第一个字母小写)，如：user_name => userName
	 */
	private String lowerAttrName;
	/**
	 * 属性类型
	 */
	private String attributeType;
	/**+
	 *  主键标识 auto_increment
	 */
	private String extra;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getStaticFinalName() {
		return staticFinalName;
	}

	public void setStaticFinalName(String staticFinalName) {
		this.staticFinalName = staticFinalName;
	}

	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getCharacterMaximumLength() {
		return characterMaximumLength;
	}

	public void setCharacterMaximumLength(Integer characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}

	public Integer getNumericPrecision() {
		return numericPrecision;
	}

	public void setNumericPrecision(Integer numericPrecision) {
		this.numericPrecision = numericPrecision;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getUpperAttrName() {
		return upperAttrName;
	}

	public void setUpperAttrName(String upperAttrName) {
		this.upperAttrName = upperAttrName;
	}

	public String getLowerAttrName() {
		return lowerAttrName;
	}

	public void setLowerAttrName(String lowerAttrName) {
		this.lowerAttrName = lowerAttrName;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
}
