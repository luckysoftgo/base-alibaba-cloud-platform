package com.application.cloud.osscloud.cloud;


import com.application.cloud.osscloud.excetpion.OSSException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 腾讯云存储
 * @author : 孤狼
 * @NAME: QqcloudCloudStorageService
 * @DESC: QqcloudCloudStorageService 类设计
 *
 * https://cloud.tencent.com/document/product/436/10199
 *
 **/
public class QqcloudCloudStorageService extends CloudStorageService {
	
    private COSClient client;

    public QqcloudCloudStorageService(CloudStorageProperties config){
        this.config = config;
	    //1.初始化用户身份信息（secretId, secretKey）。
	    COSCredentials cred = new BasicCOSCredentials(config.getQqcloud().getSecretId(), config.getQqcloud().getSecretKey());
		//2.设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
		// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
	    Region region = new Region(config.getQqcloud().getRegion());
	    ClientConfig clientConfig = new ClientConfig(region);
		//3.生成 cos 客户端
	    client = new COSClient(cred, clientConfig);
    }

    @Override
    public String upload(byte[] data, String path) {
        //腾讯云必需要以"/"开头
        if(!path.startsWith("/")) {
            path = "/" + path;
        }
	    // 指定要上传的文件
	    File localFile = new File(path);
		// 指定要上传到的存储桶
	    String bucketName = config.getQqcloud().getBucketName();
		// 指定要上传到 COS 上对象键
	    String key = "exampleobject";
	    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
	    PutObjectResult putObjectResult = client.putObject(putObjectRequest);
        return config.getQqcloud().getDomain()+ putObjectResult.getRequestId();
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
        return upload(data, getPath(config.getQqcloud().getPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQqcloud().getPrefix(), suffix));
    }
}
