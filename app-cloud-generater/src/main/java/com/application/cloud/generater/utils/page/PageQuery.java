package com.application.cloud.generater.utils.page;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : 孤狼
 * @NAME: PageQuery
 * @DESC: Query类设计
 **/
public class PageQuery<T> extends LinkedHashMap<String, Object> {
	/**
	 * 当前页码
	 */
	private Integer page;
	
	/**
	 * 每页条数
	 */
	private Integer limit;
	
	/**
	 * 查询集合
	 * @param params
	 */
	public PageQuery(Map<String, Object> params){
		this.putAll(params);
		//分页参数默认值
		Integer currentPage = 1;
		Integer pageSize = 10;
		if(params.get(Constant.MYBATIS_PLUS_PAGE) != null){
			pageSize = Integer.parseInt(Objects.toString(params.get(Constant.MYBATIS_PLUS_PAGE),"0"));
		}
		if(params.get(Constant.MYBATIS_PLUS_LIMIT) != null){
			currentPage = Integer.parseInt(Objects.toString(params.get(Constant.MYBATIS_PLUS_LIMIT),"0"));
		}
		this.page = pageSize;
		this.limit = currentPage;

		//分页参数
		Integer offset = ((currentPage - 1) * pageSize);
		this.put(Constant.MYBATIS_PLUS_PAGE,currentPage);
		this.put(Constant.MYBATIS_PLUS_OFFSET, offset);
		this.put(Constant.MYBATIS_PLUS_LIMIT,pageSize);
		//防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
		String orderField = SQLFilter.sqlInject(Objects.toString(params.get(Constant.MYBATIS_PLUS_SIDX),""));
		this.put(Constant.MYBATIS_PLUS_SIDX, orderField);
		//排序字段
		String order = Objects.toString(params.get(Constant.MYBATIS_PLUS_ORDER),"");
		this.put(Constant.MYBATIS_PLUS_ORDER,order);
	}
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getLimit() {
		return limit;
	}
	
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}