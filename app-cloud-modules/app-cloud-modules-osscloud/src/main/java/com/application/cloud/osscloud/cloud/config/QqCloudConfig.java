package com.application.cloud.osscloud.cloud.config;

import com.application.cloud.osscloud.cloud.group.QcloudGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : 孤狼
 * @NAME: QqCloudConfig
 * @DESC: QqCloudConfig类设计
 **/
@Data
public class QqCloudConfig implements Serializable {
	
	/**
	 * 腾讯云绑定的域名
	 */
	@NotBlank(message="腾讯云绑定的域名不能为空", groups = QcloudGroup.class)
	@URL(message = "腾讯云绑定的域名格式不正确", groups = QcloudGroup.class)
	private String domain;
	
	/**
	 * 腾讯云路径前缀
	 */
	private String prefix;
	
	/**
	 * 腾讯云AppId
	 */
	@NotNull(message="腾讯云AppId不能为空", groups = QcloudGroup.class)
	private Integer appId;
	
	/**
	 * 腾讯云SecretId
	 */
	@NotBlank(message="腾讯云SecretId不能为空", groups = QcloudGroup.class)
	private String secretId;
	
	/**
	 * 腾讯云SecretKey
	 */
	@NotBlank(message="腾讯云SecretKey不能为空", groups = QcloudGroup.class)
	private String secretKey;
	
	/**
	 * 腾讯云BucketName
	 */
	@NotBlank(message="腾讯云BucketName不能为空", groups = QcloudGroup.class)
	private String bucketName;
	
	/**
	 * 腾讯云COS所属地区
	 */
	@NotBlank(message="所属地区不能为空", groups = QcloudGroup.class)
	private String region;
	
}
