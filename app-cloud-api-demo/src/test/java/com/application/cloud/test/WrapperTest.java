package com.application.cloud.test;

import com.alibaba.fastjson.JSON;
import com.application.cloud.demo.test.dto.CloudTestScoreDto;
import com.application.cloud.demo.test.entity.CloudTestScoreEntity;
import com.application.cloud.dynamic.datasource.datapage.query.QueryWhereProcessor;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * @author : 孤狼
 * @NAME: WrapperTest
 * @DESC: WrapperTest类设计
 **/
public class WrapperTest {
	
	public static void main(String[] args) {
		CloudTestScoreDto scoreDto = new CloudTestScoreDto();
		scoreDto.setUserName("无天");
		Wrapper<CloudTestScoreEntity> wrapper = QueryWhereProcessor.invoke(scoreDto);
		System.out.println("9999999:"+ JSON.toJSONString(wrapper)+"\n\t"+wrapper.getTargetSql());
	}
}
