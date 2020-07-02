package com.application.cloud.test;

import com.alibaba.fastjson.JSON;
import com.application.cloud.BasicTest;
import com.application.cloud.demo.test.dto.CloudTestScoreDto;
import com.application.cloud.demo.test.service.CloudTestScoreService;
import com.application.cloud.dynamic.datasource.datapage.BasicPageVO;
import com.application.cloud.dynamic.datasource.datapage.HelperProcessor;
import com.application.cloud.dynamic.datasource.datapage.PageProcessor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: CloudTestScoreTest
 * @DESC: CloudTestScoreTest类设计
 **/
public class CloudTestScoreTest extends BasicTest {
	
	@Autowired
	private CloudTestScoreService cloudTestScoreService;
	
	@Test
	public void test1(){
		Map<String,Object> params= new HashMap<>();
		params.put("pageNum","1");
		params.put("pageSize","10");
		PageProcessor processor = cloudTestScoreService.queryMapPage(params);
		System.out.println(JSON.toJSONString(processor));
	}
	
	@Test
	public void test2(){
		BasicPageVO pageVO = new BasicPageVO();
		pageVO.setPageNum("1");
		pageVO.setPageSize("10");
		CloudTestScoreDto scoreDto = new CloudTestScoreDto();
		scoreDto.setUserId(1L);
		PageProcessor processor = cloudTestScoreService.queryEntityPage(pageVO,scoreDto);
		System.out.println(JSON.toJSONString(processor));
	}
	
	@Test
	public void test3(){
		Map<String,Object> params= new HashMap<>();
		params.put("pageNum","1");
		params.put("pageSize","10");
		HelperProcessor processor = cloudTestScoreService.queryMapInfoPage(params);
		System.out.println(JSON.toJSONString(processor));
	}
	
	@Test
	public void test4(){
		BasicPageVO pageVO = new BasicPageVO();
		pageVO.setPageNum("1");
		pageVO.setPageSize("10");
		CloudTestScoreDto scoreDto = new CloudTestScoreDto();
		scoreDto.setUserId(1L);
		HelperProcessor processor = cloudTestScoreService.queryEntityInfoPage(pageVO,scoreDto);
		System.out.println(JSON.toJSONString(processor));
	}
	
	@Test
	public void test5(){
		BasicPageVO pageVO = new BasicPageVO();
		pageVO.setPageNum("1");
		pageVO.setPageSize("10");
		//like 查询详细请参见
		CloudTestScoreDto scoreDto = new CloudTestScoreDto();
		scoreDto.setUserName("无天");
		scoreDto.setUserId(6L);
		HelperProcessor processor = cloudTestScoreService.queryEntityInfoPage(pageVO,scoreDto);
		System.out.println(JSON.toJSONString(processor));
	}
}
