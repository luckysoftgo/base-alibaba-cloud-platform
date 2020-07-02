package com.application.cloud.generater.gencdoe;

import java.util.Arrays;
import java.util.List;

/**
 * @author : 孤狼
 * @NAME: GenConstant
 * @DESC: GenConstant类设计
 **/
public class GenConstant {
	
	/**
	 * 不需要输出的字段.
	 */
	public final static List<String> dateColumns = Arrays.asList(new String[]{"datetime","date","timestamp","timestamp(6)"});
	
	/**
	 * 需要输出的字段.
	 */
	public final static List<String> idColumns = Arrays.asList(new String[]{"id"});
	
	/**
	 * 需要输出的字段.
	 */
	public final static List<String> basicColumns = Arrays.asList(new String[]{"id","create_by","create_time","update_by","update_time"});
	
	/**
	 * 需要输出的字段.
	 */
	public final static List<String> genericColumns = Arrays.asList(new String[]{"id","inst_id","create_by","create_time","update_by","update_time","info_desc","disabled"});
	
	/**
	 * ID类
	 */
	public final static String ID_ENTITY="IdEntity";
	
	/**
	 * 一般的类
	 */
	public final static String BASIC_ENTITY="BasicEntity";
	
	/**
	 * 通用的类
	 */
	public final static String GENERIC_ENTITY="GenericEntity";
	
}
