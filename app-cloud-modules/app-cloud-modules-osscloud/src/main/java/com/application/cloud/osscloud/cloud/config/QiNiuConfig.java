package com.application.cloud.osscloud.cloud.config;

import com.application.cloud.osscloud.cloud.group.QiNiuGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : 孤狼
 * @NAME: QiNiuConfig
 * @DESC: QiNiuConfig类设计
 **/
@Data
public class QiNiuConfig implements Serializable {
	
	/**
	 * 七牛绑定的域名
	 */
	@NotBlank(message="七牛绑定的域名不能为空", groups = QiNiuGroup.class)
	@URL(message = "七牛绑定的域名格式不正确", groups = QiNiuGroup.class)
	private String domain;
	
	/**
	 * 七牛路径前缀
	 */
	private String prefix;
	
	/**
	 * 七牛ACCESS_KEY
	 */
	@NotBlank(message="七牛AccessKey不能为空", groups = QiNiuGroup.class)
	private String accessKey;
	
	/**
	 * 七牛SECRET_KEY
	 */
	@NotBlank(message="七牛SecretKey不能为空", groups = QiNiuGroup.class)
	private String secretKey;
	
	/**
	 * 七牛存储空间名
	 */
	@NotBlank(message="七牛空间名不能为空", groups = QiNiuGroup.class)
	private String bucketName;
	
}
