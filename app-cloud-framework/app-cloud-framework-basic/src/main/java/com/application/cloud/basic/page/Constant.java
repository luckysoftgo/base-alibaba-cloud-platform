package com.application.cloud.basic.page;

/**
 * @author : 孤狼
 * @NAME: Constant
 * @DESC: Constant类设计
 **/
public class Constant {
	
	
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
	
	/**
	 * 当前页码
	 */
	public static final String PAGE_NUM = "pageNum";
	/**
	 * 每页显示记录数
	 */
	public static final String PAGE_SIZE = "pageSize";
	
	/**
	 * mybatis-plus 需要的参数
	 */
	public static final String MYBATIS_PLUS_PAGE = "page";
	/**
	 * mybatis-plus 需要的参数
	 */
	public static final String MYBATIS_PLUS_LIMIT = "limit";
	/**
	 * mybatis-plus 需要的参数
	 */
	public static final String MYBATIS_PLUS_OFFSET = "offset";
	/**
	 * mybatis-plus 需要的参数
	 */
	public static final String MYBATIS_PLUS_SIDX = "sidx";
	/**
	 * mybatis-plus 需要的参数
	 */
	public static final String MYBATIS_PLUS_ORDER = "order";
	
	/**
	 * 排序字段
	 */
	public static final String ORDER_FIELD = "orderField";
	
	/**
	 * 排序方式
	 */
	public static final String ORDER_BY = "orderBy";
	
	/**
	 *  升序
	 */
	public static final String ASC = "asc";
	
	/**
	 * 菜单类型
	 */
	public enum MenuType {
		/**
		 * 目录
		 */
		CATALOG(0),
		
		/**
		 * 菜单
		 */
		MENU(1),
		
		/**
		 * 按钮
		 */
		BUTTON(2);
		
		private int value;
		
		MenuType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	/**
	 * 定时任务状态
	 */
	public enum ScheduleStatus {
		/**
		 * 正常
		 */
		NORMAL(0),
		/**
		 * 暂停
		 */
		PAUSE(1);
		
		private int value;
		
		ScheduleStatus(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
}
