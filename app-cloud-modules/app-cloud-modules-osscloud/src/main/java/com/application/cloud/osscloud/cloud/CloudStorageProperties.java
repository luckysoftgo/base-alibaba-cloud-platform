package com.application.cloud.osscloud.cloud;

import com.application.cloud.osscloud.cloud.config.AliYunConfig;
import com.application.cloud.osscloud.cloud.config.LocalConfig;
import com.application.cloud.osscloud.cloud.config.QiNiuConfig;
import com.application.cloud.osscloud.cloud.config.QqCloudConfig;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 云存储配置信息
 * @author : 孤狼
 * @NAME: CloudStorageProperties
 * @DESC: CloudStorageProperties 类设计
 **/
@Data
@Component
@ConfigurationProperties(prefix = "oss.file")
public class CloudStorageProperties {
	
	/**
	 * 是否是云文件服务器.(默认为false)
	 */
	private boolean ossFlag;
    
	/**
	 * 类型 1：七牛  2：阿里云  3：腾讯云
	 */
	@Range(min=1, max=3, message = "类型错误")
    private Integer type;
	
	/**
	 * 本地
	 */
	private LocalConfig local = new LocalConfig();
	
	/**
	 * 阿里云
	 */
	private AliYunConfig aliyun = new AliYunConfig();
	
	/**
	 * 七牛云
	 */
	private QiNiuConfig qiniu = new QiNiuConfig();
	
	/**
	 * 腾讯云
	 */
	private QqCloudConfig qqcloud = new QqCloudConfig();
	
	public boolean isOssFlag() {
		return ossFlag;
	}
	
	public void setOssFlag(boolean ossFlag) {
		this.ossFlag = ossFlag;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public LocalConfig getLocal() {
		return local;
	}
	
	public void setLocal(LocalConfig local) {
		this.local = local;
	}
	
	public AliYunConfig getAliyun() {
		return aliyun;
	}
	
	public void setAliyun(AliYunConfig aliyun) {
		this.aliyun = aliyun;
	}
	
	public QiNiuConfig getQiniu() {
		return qiniu;
	}
	
	public void setQiniu(QiNiuConfig qiniu) {
		this.qiniu = qiniu;
	}
	
	public QqCloudConfig getQqcloud() {
		return qqcloud;
	}
	
	public void setQqcloud(QqCloudConfig qqcloud) {
		this.qqcloud = qqcloud;
	}
}
