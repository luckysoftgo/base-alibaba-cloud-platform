package com.application.cloud.generater.utils.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author : 孤狼
 * @NAME: PageQuery
 * @DESC: Query类设计
 **/
public class PageHolder<T> implements Serializable {

	/**
	 * 分页设置
	 * @param params
	 * @return
	 */
	public IPage<T> getQueryPage(Map<String, Object> params) {
		return this.getQueryPage(params, null, false);
	}
	
	/**
	 * 获得分页设置.
	 * @param params : 参数.
	 * @param defaultOrderFields : 排序字段.
	 * @param isAsc : 是否是 asc 排序模式.
	 * @return
	 */
	public IPage<T> getQueryPage(Map<String, Object> params, String[] defaultOrderFields, boolean isAsc) {
		//分页参数默认值
		long currentPage = 1;
		long pageSize = 10;
		if(params.get(Constant.PAGE_NUM) != null){
			currentPage = Long.parseLong(Objects.toString(params.get(Constant.PAGE_NUM),"1"));
		}
		if(params.get(Constant.PAGE_SIZE) != null){
			pageSize = Long.parseLong(Objects.toString(params.get(Constant.PAGE_SIZE),"10"));
		}
		//分页对象
		Page<T> page = new Page<>(currentPage, pageSize);
		
		//分页参数
		Long offset = ((currentPage - 1) * pageSize);
		params.put(Constant.MYBATIS_PLUS_OFFSET, offset);
		params.put(Constant.MYBATIS_PLUS_PAGE,currentPage);
		params.put(Constant.MYBATIS_PLUS_LIMIT,pageSize);
		
		//排序字段
		//防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
		String orderField = SQLFilter.sqlInject(Objects.toString(params.get(Constant.ORDER_FIELD),""));
		String order = Objects.toString(params.get(Constant.ORDER_BY),"");
		params.put(Constant.MYBATIS_PLUS_SIDX, orderField);
		params.put(Constant.MYBATIS_PLUS_ORDER,order);
		//前端字段排序
		if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
			if(Constant.ASC.equalsIgnoreCase(order)) {
				return page.addOrder(OrderItem.ascs(orderField));
			}else {
				return page.addOrder(OrderItem.descs(orderField));
			}
		}
		//没有排序字段，则不排序
		if(defaultOrderFields==null || defaultOrderFields.length==0){
			return page;
		}
		//默认排序
		if(isAsc) {
			page.addOrder(OrderItem.ascs(defaultOrderFields));
		}else {
			page.addOrder(OrderItem.descs(defaultOrderFields));
		}
		return page;
	}
}