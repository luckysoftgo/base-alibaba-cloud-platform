package com.application.cloud.osscloud.cloud.config;

import com.application.cloud.osscloud.cloud.group.AliyunGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : 孤狼
 * @NAME: AliYunConfig
 * @DESC: AliYunConfig类设计
 *
 **/
@Data
public class AliYunConfig implements Serializable {
	
	/**
	 * 阿里云绑定的域名
	 */
	@NotBlank(message="阿里云绑定的域名不能为空", groups = AliyunGroup.class)
	@URL(message = "阿里云绑定的域名格式不正确", groups = AliyunGroup.class)
	private String domain;
	
	/**
	 * 阿里云路径前缀
	 */
	private String prefix;
	
	/**
	 * 阿里云EndPoint
	 */
	@NotBlank(message="阿里云EndPoint不能为空", groups = AliyunGroup.class)
	private String endPoint;
	
	/**
	 * 阿里云AccessKeyId
	 */
	@NotBlank(message="阿里云AccessKeyId不能为空", groups = AliyunGroup.class)
	private String accessKeyId;
	
	/**
	 * 阿里云AccessKeySecret
	 */
	@NotBlank(message="阿里云AccessKeySecret不能为空", groups = AliyunGroup.class)
	private String accessKeySecret;
	
	/**
	 * 阿里云BucketName
	 */
	@NotBlank(message="阿里云BucketName不能为空", groups = AliyunGroup.class)
	private String bucketName;
}
