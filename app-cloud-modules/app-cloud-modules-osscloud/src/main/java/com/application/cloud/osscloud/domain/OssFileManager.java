package com.application.cloud.osscloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.application.cloud.common.core.annotation.Excel;
import com.application.cloud.common.core.web.domain.BaseEntity;

/**
 * 文件/附件管理对象 oss_file_manager
 * 
 * @author app-cloud
 * @date 2020
 */
public class OssFileManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long ossId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long fileSize;

    /** 文件的路径 */
    @Excel(name = "文件的路径")
    private String filePath;

    /** 文件唯一标识 */
    @Excel(name = "文件唯一标识")
    private String uniqueKey;

    /** 文件所属的业务id */
    @Excel(name = "文件所属的业务id")
    private String moduleId;

    /** 文件所属的业务code */
    @Excel(name = "文件所属的业务code")
    private String moduleCode;

    /** 描述 */
    @Excel(name = "描述")
    private String infoDesc;

    public void setOssId(Long ossId) 
    {
        this.ossId = ossId;
    }

    public Long getOssId() 
    {
        return ossId;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setUniqueKey(String uniqueKey) 
    {
        this.uniqueKey = uniqueKey;
    }

    public String getUniqueKey() 
    {
        return uniqueKey;
    }
    public void setModuleId(String moduleId) 
    {
        this.moduleId = moduleId;
    }

    public String getModuleId() 
    {
        return moduleId;
    }
    public void setModuleCode(String moduleCode) 
    {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() 
    {
        return moduleCode;
    }
    public void setInfoDesc(String infoDesc) 
    {
        this.infoDesc = infoDesc;
    }

    public String getInfoDesc() 
    {
        return infoDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ossId", getOssId())
            .append("fileName", getFileName())
            .append("fileType", getFileType())
            .append("fileSize", getFileSize())
            .append("filePath", getFilePath())
            .append("uniqueKey", getUniqueKey())
            .append("moduleId", getModuleId())
            .append("moduleCode", getModuleCode())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("infoDesc", getInfoDesc())
            .toString();
    }
}
