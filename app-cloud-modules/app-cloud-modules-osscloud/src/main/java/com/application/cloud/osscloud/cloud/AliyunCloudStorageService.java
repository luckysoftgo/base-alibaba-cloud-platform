package com.application.cloud.osscloud.cloud;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.application.cloud.osscloud.excetpion.OSSException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云存储
 * @author : 孤狼
 * @NAME: AliyunCloudStorageService
 * @DESC: AliyunCloudStorageService 类设计
 *
 * https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.798.2bc645dchuLQhj
 *
 **/
public class AliyunCloudStorageService extends CloudStorageService {
	
	/**
	 * 实例对象
	 */
    private OSS client;

    public AliyunCloudStorageService(CloudStorageProperties config){
        this.config = config;
	    //该方式已经被放弃,采用如下方式实例化对象
	    //https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.798.2bc645dchuLQhj
	    //client = new OSSClient(config.getAliyun().getEndPoint(), config.getAliyun().getAccessKeyId(),config.getAliyun().getAccessKeySecret());
	    client = new OSSClientBuilder().build(config.getAliyun().getEndPoint(), config.getAliyun().getAccessKeyId(),config.getAliyun().getAccessKeySecret());
	
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(config.getAliyun().getBucketName(), path, inputStream);
        } catch (Exception e){
            throw new OSSException("上传文件失败，请检查配置信息", e);
        }
        return config.getAliyun().getDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getAliyun().getPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getAliyun().getPrefix(), suffix));
    }
}
