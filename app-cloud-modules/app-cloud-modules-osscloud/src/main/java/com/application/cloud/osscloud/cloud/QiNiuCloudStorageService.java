package com.application.cloud.osscloud.cloud;

import com.application.cloud.osscloud.excetpion.OSSException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云存储
 * @author : 孤狼
 * @NAME: QiNiuCloudStorageService
 * @DESC: QiNiuCloudStorageService 类设计
 *
 *https://developer.qiniu.com/kodo/sdk/1239/java
 **/
public class QiNiuCloudStorageService extends CloudStorageService {
	
	/**
	 * 上传管理类
	 */
    private UploadManager uploadManager;
	/**
	 * 使用的token
	 */
	private String token;
	

    public QiNiuCloudStorageService(CloudStorageProperties config){
        this.config = config;
	    uploadManager = new UploadManager(new Configuration());
	    token = Auth.create(config.getQiniu().getAccessKey(), config.getQiniu().getSecretKey()).uploadToken(config.getQiniu().getBucketName());
    }
    
    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
            throw new OSSException("上传文件失败，请核对七牛配置信息", e);
        }

        return config.getQiniu().getDomain() + "/" + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new OSSException("上传文件失败", e);
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQiniu().getPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQiniu().getPrefix(), suffix));
    }
}
