package com.application.cloud.osscloud.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 孤狼
 * @NAME: CloudStorageConfiguration
 * @DESC: CloudStorageConfigurtion类设计
 **/
@Slf4j
@Configuration
public class CloudStorageConfiguration {
	
	/**
	 * 属性的配置.
	 */
	@Autowired
	private CloudStorageProperties cloudStorageProperties;
	
	/**
	 *  当容器没有这个 Bean 的时候才创建这个 Bean
	 */
	@Bean
	@ConditionalOnMissingBean(CloudStorageService.class)
	public CloudStorageService getCloudStorageInstance() {
		if (null==cloudStorageProperties){
			log.error("获得对象为空");
			return null;
		}
		boolean ossFlag = cloudStorageProperties.isOssFlag();
		if (ossFlag){
			return new LocalStorageService();
		}
		Integer type = cloudStorageProperties.getType();
		switch (type){
			case 1:
				return new AliyunCloudStorageService(cloudStorageProperties);
			case 2:
				return new QiNiuCloudStorageService(cloudStorageProperties);
			case 3:
				return new QqcloudCloudStorageService(cloudStorageProperties);
			default:
				log.info("没有找到存储对象");
				return null;
		}
	}

}
