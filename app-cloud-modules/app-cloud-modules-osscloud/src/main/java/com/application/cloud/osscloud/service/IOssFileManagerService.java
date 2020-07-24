package com.application.cloud.osscloud.service;

import com.application.cloud.osscloud.domain.OssFileManager;

import java.util.List;

/**
 * 文件/附件管理Service接口
 * 
 * @author app-cloud
 * @date 2020
 */
public interface IOssFileManagerService 
{
    /**
     * 查询文件/附件管理
     * 
     * @param ossId 文件/附件管理ID
     * @return 文件/附件管理
     */
    public OssFileManager selectOssFileManagerById(Long ossId);

    /**
     * 查询文件/附件管理列表
     * 
     * @param ossFileManager 文件/附件管理
     * @return 文件/附件管理集合
     */
    public List<OssFileManager> selectOssFileManagerList(OssFileManager ossFileManager);

    /**
     * 新增文件/附件管理
     * 
     * @param ossFileManager 文件/附件管理
     * @return 结果
     */
    public int insertOssFileManager(OssFileManager ossFileManager);

    /**
     * 修改文件/附件管理
     * 
     * @param ossFileManager 文件/附件管理
     * @return 结果
     */
    public int updateOssFileManager(OssFileManager ossFileManager);

    /**
     * 批量删除文件/附件管理
     * 
     * @param ossIds 需要删除的文件/附件管理ID
     * @return 结果
     */
    public int deleteOssFileManagerByIds(Long[] ossIds);

    /**
     * 删除文件/附件管理信息
     * 
     * @param ossId 文件/附件管理ID
     * @return 结果
     */
    public int deleteOssFileManagerById(Long ossId);
}
