package com.application.cloud.generater.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: GeneratorDao
 * @DESC: GeneratorDao 接口设计
 **/
public interface GeneratorDao {
    /**
     * 查询所有的表
     * @param map
     * @return
     */
    List<Map<String, Object>> queryList(Map<String, Object> map);

    /**
     *查询单个表
     * @param tableName
     * @return
     */
    Map<String, String> queryTable(String tableName);

    /**
     * 查询表的列信息
     * @param tableName
     * @return
     */
    List<Map<String, String>> queryColumns(String tableName);

}
