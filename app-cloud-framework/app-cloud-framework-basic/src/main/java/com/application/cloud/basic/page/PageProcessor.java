package com.application.cloud.basic.page;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @author : 孤狼
 * @NAME: PageProcessor
 * @DESC: PageProcessor 实现设计
 **/
public class PageProcessor implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 总记录数
	 */
	private Integer totalCount;
	/**
	 * 每页记录数
	 */
	private Integer pageSize;
	/**
	 * 总页数
	 */
	private Integer totalPage;
	/**
	 * 当前页数
	 */
	private Integer currentPage;
	/**
	 * 列表数据
	 */
	private List<?> dataList;
	
	/**
	 * 分页
	 * @param dataList        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currentPage    当前页数
	 */
	public PageProcessor(List<?> dataList, Integer totalCount, Integer pageSize, Integer currentPage) {
		this.dataList = dataList;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}
	
	
	/**
	 * 分页
	 */
	public PageProcessor(IPage<?> page) {
		this.dataList = page.getRecords();
		this.totalCount = (int)page.getTotal();
		this.pageSize = (int)page.getSize();
		this.currentPage = (int)page.getCurrent();
		this.totalPage = (int)page.getPages();
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> datalist) {
		this.dataList = dataList;
	}
}
