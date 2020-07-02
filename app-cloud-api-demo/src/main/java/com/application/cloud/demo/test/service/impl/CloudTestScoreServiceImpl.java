package com.application.cloud.demo.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.application.cloud.demo.test.dao.CloudTestScoreDao;
import com.application.cloud.demo.test.dto.CloudTestScoreDto;
import com.application.cloud.demo.test.entity.CloudTestScoreEntity;
import com.application.cloud.demo.test.service.CloudTestScoreService;
import com.application.cloud.dynamic.datasource.datapage.BasicPageVO;
import com.application.cloud.dynamic.datasource.datapage.HelperProcessor;
import com.application.cloud.dynamic.datasource.datapage.PageHolder;
import com.application.cloud.dynamic.datasource.datapage.PageProcessor;
import com.application.cloud.dynamic.datasource.datapage.query.QueryWhereProcessor;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Slf4j
@Service("cloudTestScoreService")
public class CloudTestScoreServiceImpl extends ServiceImpl<CloudTestScoreDao, CloudTestScoreEntity> implements CloudTestScoreService {
	
	@Override
	public PageProcessor queryMapPage(Map<String, Object> params) {
		IPage<CloudTestScoreEntity> page = this.page(
				new PageHolder<CloudTestScoreEntity>().getQueryPage(params),
				new QueryWrapper<CloudTestScoreEntity>()
		);
		return new PageProcessor(page);
	}
	
	@Override
	public PageProcessor queryEntityPage(BasicPageVO pageVO, CloudTestScoreDto scoreDto) {
		IPage<CloudTestScoreEntity> page = this.page(
				new PageHolder<CloudTestScoreEntity>().getQueryPage(pageVO),
				QueryWhereProcessor.invoke(scoreDto)
		);
		return new PageProcessor(page);
	}
	
	@Override
	public HelperProcessor queryMapInfoPage(Map<String, Object> params) {
		int pageNum = Integer.parseInt(Objects.toString(params.get("pageNum"),"1"));
		int pageSize = Integer.parseInt(Objects.toString(params.get("pageSize"),"10"));
		params.clear();
		PageHelper.startPage(pageNum,pageSize);
		List<CloudTestScoreEntity> list = super.listByMap(params);
		HelperProcessor processor = new HelperProcessor(list);
		return processor;
	}
	
	@Override
	public HelperProcessor queryEntityInfoPage(BasicPageVO pageVO, CloudTestScoreDto scoreDto) {
		int pageNum = Integer.parseInt(Objects.toString(pageVO.getPageNum(),"1"));
		int pageSize = Integer.parseInt(Objects.toString(pageVO.getPageSize(),"10"));
		PageHelper.startPage(pageNum,pageSize);
		Wrapper<CloudTestScoreEntity> wrapper = QueryWhereProcessor.invoke(scoreDto);
		System.out.println("88888888:"+ JSON.toJSONString(wrapper));
		List<CloudTestScoreEntity> list = super.list(wrapper);
		HelperProcessor processor = new HelperProcessor(list);
		return processor;
	}
	
}