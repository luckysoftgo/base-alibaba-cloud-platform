package com.application.cloud.generater.gencdoe;

import com.application.cloud.generater.entity.ColumnBean;
import com.application.cloud.generater.entity.TableBean;
import com.application.cloud.generater.exception.GenException;
import com.application.cloud.generater.mongo.config.MongoManager;
import com.application.cloud.generater.mongo.entity.MongoDefinition;
import com.application.cloud.generater.mongo.entity.MongoGeneratorEntity;
import com.application.cloud.generater.utils.DateUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author : 孤狼
 * @NAME: GenerateHolder
 * @DESC: GenerateHolder类设计
 **/
public class GenerateHolder {
	
	/**
	 * 不需要输出的字段.
	 */
	private static List<String> nonNeedColumns = Arrays.asList(new String[]{"id","create_by","create_time","update_by","update_time","remark","info_desc","disabled"});
	
	private static String currentTableName;
	
	/**
	 * 加载模板.
	 * @return
	 */
	public static List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		
		templates.add("template/Entity.java.vm");
		templates.add("template/Dao.xml.vm");
		templates.add("template/Menu.sql.vm");
		
		templates.add("template/Dao.java.vm");
		templates.add("template/Service.java.vm");
		templates.add("template/ServiceImpl.java.vm");
		templates.add("template/Controller.java.vm");
		
		templates.add("template/Index.vue.vm");
		templates.add("template/Operater.vue.vm");
		/**
		 * mongo操作
		 */
		if (MongoManager.isMongo()) {
			//mongo不需要mapper、sql 实体类需要替换
			templates.remove(0);
			templates.remove(1);
			templates.remove(2);
			templates.add("template/MongoEntity.java.vm");
		}
		return templates;
	}
	
	public static List<String> getMongoChildTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("template/MongoChildrenEntity.java.vm");
		return templates;
	}
	
	/**
	 * 生成代码
	 */
	public static void generatorCode(Map<String, String> table,
	                                 List<Map<String, String>> columns, ZipOutputStream zip) {
		//配置信息
		Configuration config = getConfig();
		boolean hasBigDecimal = false;
		boolean hasList = false;
		//表信息
		TableBean tableBean = new TableBean();
		tableBean.setTableName(table.get("tableName"));
		tableBean.setTableComment(table.get("tableComment"));
		//表名转换成Java类名
		String className = tableToJava(tableBean.getTableName(),config.getStringArray("tablePrefix"));
		tableBean.setUpperClassName(className);
		tableBean.setLowerClassName(StringUtils.uncapitalize(className));
		
		//列信息
		List<ColumnBean> columsList = new ArrayList<>();
		for (Map<String, String> column : columns) {
			ColumnBean columnBean = new ColumnBean();
			columnBean.setTableName(column.get("tableName"));
			String columnName = column.get("columnName");
			if (nonNeedColumns.contains(columnName)){
				continue;
			}
			columnBean.setColumnName(columnName);
			columnBean.setStaticFinalName(columnName.toUpperCase());
			columnBean.setOrdinalPosition(Integer.parseInt(Objects.toString(column.get("ordinalPosition"),"0")));
			columnBean.setColumnDefault(column.get("columnDefault"));
			columnBean.setIsNullable(column.get("isNullable").toUpperCase());
			columnBean.setDataType(column.get("dataType"));
			columnBean.setCharacterMaximumLength(Integer.parseInt(Objects.toString(column.get("characterMaximumLength"),"0")));
			columnBean.setNumericPrecision(Integer.parseInt(Objects.toString(column.get("numericPrecision"),"0")));
			columnBean.setColumnComment(column.get("columnComment"));
			columnBean.setColumnKey(column.get("columnKey"));
			//列名转换成Java属性名
			String attrName = columnToJava(columnBean.getColumnName());
			columnBean.setUpperAttrName(attrName);
			columnBean.setLowerAttrName(StringUtils.uncapitalize(attrName));
			//列的数据类型，转换成Java类型
			String attributeType = config.getString(columnBean.getDataType(), columnToJava(columnBean.getDataType()));
			columnBean.setAttributeType(attributeType);
			columnBean.setExtra(column.get("extra"));
			if (!hasList && "array".equals(columnBean.getExtra())) {
				hasList = true;
			}
			if (!hasBigDecimal && attributeType.equals("BigDecimal")) {
				hasBigDecimal = true;
			}
			//是否主键
			if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableBean.getPrimaryKey() == null) {
				tableBean.setPrimaryKey(columnBean);
			}
			columsList.add(columnBean);
		}
		tableBean.setTableColumns(columsList);
		
		//所有的列信息
		List<ColumnBean> allColumsList = new ArrayList<>();
		for (Map<String, String> column : columns) {
			ColumnBean columnBean = new ColumnBean();
			columnBean.setTableName(column.get("tableName"));
			String columnName = column.get("columnName");
			columnBean.setColumnName(columnName);
			columnBean.setStaticFinalName(columnName.toUpperCase());
			columnBean.setOrdinalPosition(Integer.parseInt(Objects.toString(column.get("ordinalPosition"),"0")));
			columnBean.setColumnDefault(column.get("columnDefault"));
			columnBean.setIsNullable(column.get("isNullable").toUpperCase());
			columnBean.setDataType(column.get("dataType"));
			columnBean.setCharacterMaximumLength(Integer.parseInt(Objects.toString(column.get("characterMaximumLength"),"0")));
			columnBean.setNumericPrecision(Integer.parseInt(Objects.toString(column.get("numericPrecision"),"0")));
			columnBean.setColumnComment(column.get("columnComment"));
			columnBean.setColumnKey(column.get("columnKey"));
			//列名转换成Java属性名
			String attrName = columnToJava(columnBean.getColumnName());
			columnBean.setUpperAttrName(attrName);
			columnBean.setLowerAttrName(StringUtils.uncapitalize(attrName));
			//列的数据类型，转换成Java类型
			String attributeType = config.getString(columnBean.getDataType(), columnToJava(columnBean.getDataType()));
			columnBean.setAttributeType(attributeType);
			columnBean.setExtra(column.get("extra"));
			if (!hasList && "array".equals(columnBean.getExtra())) {
				hasList = true;
			}
			if (!hasBigDecimal && attributeType.equals("BigDecimal")) {
				hasBigDecimal = true;
			}
			//是否主键
			if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableBean.getPrimaryKey() == null) {
				tableBean.setPrimaryKey(columnBean);
			}
			allColumsList.add(columnBean);
		}
		tableBean.setAllTableColumns(allColumsList);
		//没主键，则第一个字段为主键
		if (tableBean.getPrimaryKey() == null) {
			tableBean.setPrimaryKey(tableBean.getTableColumns().get(0));
		}
		
		//设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
		//加载资源.
		Velocity.init(prop);
		//main 地址
		String mainPath = config.getString("mainPath" );
		mainPath = StringUtils.isBlank(mainPath) ? "com.application.cloud" : mainPath;
		String moduleName = config.getString("moduleName" );
		String absolutePath = mainPath;
		if (StringUtils.isNotEmpty(moduleName)){
			absolutePath=mainPath+"."+moduleName;
		}
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableBean.getTableName());
		map.put("tableComment", tableBean.getTableComment());
		map.put("primaryKey", tableBean.getPrimaryKey());
		map.put("upperClassName", tableBean.getUpperClassName());
		map.put("lowerClassName", tableBean.getLowerClassName());
		map.put("pathName", tableBean.getLowerClassName().toLowerCase());
		map.put("tableColumns", tableBean.getTableColumns());
		map.put("allTableColumns", tableBean.getAllTableColumns());
		map.put("hasBigDecimal", hasBigDecimal);
		map.put("mainPath", mainPath);
		map.put("absolutePath", absolutePath);
		map.put("hasList", hasList);
		map.put("package", config.getString("package" ));
		map.put("moduleName", moduleName);
		map.put("author", config.getString("author" ));
		map.put("email", config.getString("email" ));
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		VelocityContext context = new VelocityContext(map);
		
		//获取模板列表
		List<String> templates = getTemplates();
		for (String template : templates) {
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			try {
				//添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableBean.getUpperClassName(),config.getString("package" ), config.getString("moduleName" ))));
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw new GenException("渲染模板失败，表名：" + tableBean.getTableName(), e);
			}
		}
	}
	
	/**
	 * 生成mongo其他实体类的代码
	 * @param tableNames
	 * @param zip
	 */
	public static void generatorMongoCode(String[] tableNames, ZipOutputStream zip) {
		for (String tableName : tableNames) {
			MongoDefinition info = MongoManager.getInfo(tableName);
			currentTableName = tableName;
			List<MongoGeneratorEntity> childrenInfo = info.getChildrenInfo(tableName);
			childrenInfo.remove(0);
			for (MongoGeneratorEntity mongoGeneratorEntity : childrenInfo) {
				generatorChildrenBeanCode(mongoGeneratorEntity, zip);
			}
		}
	}
	
	/**
	 * 生成mongo代码
	 * @param mongoGeneratorEntity
	 * @param zip
	 */
	private static void generatorChildrenBeanCode(MongoGeneratorEntity mongoGeneratorEntity, ZipOutputStream zip) {
		//配置信息
		Configuration config = getConfig();
		boolean hasList = false;
		//表信息
		TableBean tableEntity = mongoGeneratorEntity.toTableEntity();
		//表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(), config.getStringArray("tablePrefix"));
		tableEntity.setUpperClassName(className);
		tableEntity.setLowerClassName(StringUtils.uncapitalize(className));
		
		//列信息
		List<ColumnBean> columsList = new ArrayList<>();
		for (Map<String, String> column : mongoGeneratorEntity.getColumns()) {
			ColumnBean columnEntity = new ColumnBean();
			String columnName = column.get("columnName");
			if (columnName.contains(".")) {
				columnName = columnName.substring(columnName.lastIndexOf(".") + 1);
			}
			columnEntity.setColumnName(columnName);
			columnEntity.setDataType(column.get("dataType"));
			columnEntity.setExtra(column.get("extra"));
			//列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setUpperAttrName(attrName);
			columnEntity.setLowerAttrName(StringUtils.uncapitalize(attrName));
			
			//列的数据类型，转换成Java类型
			String attrType = config.getString(columnEntity.getDataType(), columnToJava(columnEntity.getDataType()));
			columnEntity.setAttributeType(attrType);
			
			if (!hasList && "array".equals(columnEntity.getExtra())) {
				hasList = true;
			}
			columsList.add(columnEntity);
		}
		tableEntity.setTableColumns(columsList);
		
		//设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);
		//main 地址
		String mainPath = config.getString("mainPath" );
		mainPath = StringUtils.isBlank(mainPath) ? "com.application.cloud" : mainPath;
		String moduleName = config.getString("moduleName" );
		String absolutePath = mainPath;
		if (StringUtils.isNotEmpty(moduleName)){
			absolutePath=mainPath+"."+moduleName;
		}
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("tableComment", tableEntity.getTableComment());
		map.put("primaryKey", tableEntity.getPrimaryKey());
		map.put("upperClassName", tableEntity.getUpperClassName());
		map.put("lowerClassName", tableEntity.getLowerClassName());
		map.put("pathName", tableEntity.getLowerClassName().toLowerCase());
		map.put("tableColumns", tableEntity.getTableColumns());
		map.put("mainPath", mainPath);
		map.put("absolutePath", absolutePath);
		map.put("hasList", hasList);
		map.put("package", config.getString("package" ));
		map.put("moduleName", moduleName);
		map.put("author", config.getString("author" ));
		map.put("email", config.getString("email" ));
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		
		VelocityContext context = new VelocityContext(map);
		//获取模板列表
		List<String> templates = getMongoChildTemplates();
		for (String template : templates) {
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			try {
				//添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getUpperClassName(), config.getString("package"), config.getString("moduleName"))));
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw new GenException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
			}
		}
		
	}
	
	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}
	
	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String[] tablePrefixArray) {
		if (null != tablePrefixArray && tablePrefixArray.length > 0) {
			for (String tablePrefix : tablePrefixArray) {
				tableName = tableName.replace(tablePrefix, "");
			}
		}
		return columnToJava(tableName);
	}
	
	/**
	 * 获取generator中的配置信息
	 */
	public static Configuration getConfig() {
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw new GenException("获取配置文件失败，", e);
		}
	}
	
	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String className, String packageName, String moduleName) {
		String packagePath = "main" + File.separator + "java" + File.separator;
		if (StringUtils.isNotBlank(packageName)) {
			packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
		}
		if (template.contains("MongoChildrenEntity.java.vm")) {
			return packagePath + "entity" + File.separator + "inner" + File.separator + currentTableName + File.separator + className + "InnerEntity.java";
		}
		if (template.contains("Entity.java.vm") || template.contains("MongoEntity.java.vm")) {
			return packagePath + "entity" + File.separator + className + "Entity.java";
		}
		
		if (template.contains("Dao.java.vm")) {
			return packagePath + "dao" + File.separator + className + "Dao.java";
		}
		
		if (template.contains("Service.java.vm")) {
			return packagePath + "service" + File.separator + className + "Service.java";
		}
		
		if (template.contains("ServiceImpl.java.vm")) {
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}
		
		if (template.contains("Controller.java.vm")) {
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}
		
		if (template.contains("Dao.xml.vm")) {
			return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
		}
		
		if (template.contains("Menu.sql.vm")) {
			return className.toLowerCase() + "_Menu.sql";
		}
		
		if (template.contains("Index.vue.vm")) {
			return "main" + File.separator + "resources" + File.separator + "src" + File.separator + "views" + File.separator + "modules" +
					File.separator + moduleName + File.separator + className.toLowerCase() + ".vue";
		}
		
		if (template.contains("Operater.vue.vm")) {
			return "main" + File.separator + "resources" + File.separator + "src" + File.separator + "views" + File.separator + "modules" +
					File.separator + moduleName + File.separator + className.toLowerCase() + "-Operater.vue";
		}
		return null;
	}
	
	private static String splitInnerName(String name){
		name = name.replaceAll("\\.","_");
		return name;
	}
}
