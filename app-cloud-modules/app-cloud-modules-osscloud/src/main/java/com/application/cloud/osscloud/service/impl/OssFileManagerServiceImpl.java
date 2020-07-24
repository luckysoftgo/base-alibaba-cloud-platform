package com.application.cloud.osscloud.service.impl;

import com.application.cloud.common.core.utils.DateUtils;
import com.application.cloud.osscloud.domain.OssFileManager;
import com.application.cloud.osscloud.mapper.OssFileManagerMapper;
import com.application.cloud.osscloud.service.IOssFileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件/附件管理Service业务层处理
 * 
 * @author app-cloud
 * @date 2020
 */
@Service
public class OssFileManagerServiceImpl implements IOssFileManagerService 
{
    @Autowired
    private OssFileManagerMapper ossFileManagerMapper;

    /**
     * 查询文件/附件管理
     * 
     * @param ossId 文件/附件管理ID
     * @return 文件/附件管理
     */
    @Override
    public OssFileManager selectOssFileManagerById(Long ossId)
    {
        return ossFileManagerMapper.selectOssFileManagerById(ossId);
    }

    /**
     * 查询文件/附件管理列表
     * 
     * @param ossFileManager 文件/附件管理
     * @return 文件/附件管理
     */
    @Override
    public List<OssFileManager> selectOssFileManagerList(OssFileManager ossFileManager)
    {
        return ossFileManagerMapper.selectOssFileManagerList(ossFileManager);
    }

    /**
     * 新增文件/附件管理
     * 
     * @param ossFileManager 文件/附件管理
     * @return 结果
     */
    @Override
    public int insertOssFileManager(OssFileManager ossFileManager)
    {
        ossFileManager.setCreateTime(DateUtils.getNowDate());
        return ossFileManagerMapper.insertOssFileManager(ossFileManager);
    }

    /**
     * 修改文件/附件管理
     * 
     * @param ossFileManager 文件/附件管理
     * @return 结果
     */
    @Override
    public int updateOssFileManager(OssFileManager ossFileManager)
    {
        ossFileManager.setUpdateTime(DateUtils.getNowDate());
        return ossFileManagerMapper.updateOssFileManager(ossFileManager);
    }

    /**
     * 批量删除文件/附件管理
     * 
     * @param ossIds 需要删除的文件/附件管理ID
     * @return 结果
     */
    @Override
    public int deleteOssFileManagerByIds(Long[] ossIds)
    {
        return ossFileManagerMapper.deleteOssFileManagerByIds(ossIds);
    }

    /**
     * 删除文件/附件管理信息
     * 
     * @param ossId 文件/附件管理ID
     * @return 结果
     */
    @Override
    public int deleteOssFileManagerById(Long ossId)
    {
        return ossFileManagerMapper.deleteOssFileManagerById(ossId);
    }
}
