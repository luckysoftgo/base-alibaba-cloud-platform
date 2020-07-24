package com.application.cloud.osscloud.cloud.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : 孤狼
 * @NAME: LocalConfig
 * @DESC: LocalConfig 类设计
 **/
@Data
public class LocalConfig implements Serializable {
	
	/**
	 * 文件上传的根路径.
	 */
	private String filePath;

}
