package com.application.cloud.demo.test.dao;

import com.application.cloud.demo.test.entity.CloudTestScoreEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成绩信息接口
 * 
 * @author 孤狼
 * @email 1577620678@qq.com
 * @date 1970-01-01 00:00:00
 */
@Mapper
public interface CloudTestScoreDao extends BaseMapper<CloudTestScoreEntity> {
	
}
