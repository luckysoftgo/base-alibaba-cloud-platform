package com.application.cloud.demo.test.service;

import com.application.cloud.demo.test.dto.CloudTestScoreDto;
import com.application.cloud.demo.test.entity.CloudTestScoreEntity;
import com.application.cloud.dynamic.datasource.datapage.BasicPageVO;
import com.application.cloud.dynamic.datasource.datapage.HelperProcessor;
import com.application.cloud.dynamic.datasource.datapage.PageProcessor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 成绩信息
 *
 * @author 孤狼
 * @email 1577620678@qq.com
 * @date 1970-01-01 00:00:00
 */
public interface CloudTestScoreService extends IService<CloudTestScoreEntity> {

    /**
    * 分页查询
    * @param params
    * @return
    */
    PageProcessor queryMapPage(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * @param pageVO
	 * @return
	 */
	PageProcessor queryEntityPage(BasicPageVO pageVO,CloudTestScoreDto scoreDto);
	
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	HelperProcessor queryMapInfoPage(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * @param pageVO
	 * @return
	 */
	HelperProcessor queryEntityInfoPage(BasicPageVO pageVO,CloudTestScoreDto scoreDto);
	
}

