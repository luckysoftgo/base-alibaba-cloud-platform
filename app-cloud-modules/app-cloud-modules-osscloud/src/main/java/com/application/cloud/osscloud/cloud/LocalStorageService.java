package com.application.cloud.osscloud.cloud;

import java.io.InputStream;

/**
 * 本地存储
 * @author : 孤狼
 * @NAME: LocalStorageService
 * @DESC: LocalStorageService 类设计
 *
 **/
public class LocalStorageService extends CloudStorageService {
	
	@Override
	public String upload(byte[] data, String path) {
		return null;
	}
	
	@Override
	public String uploadSuffix(byte[] data, String suffix) {
		return null;
	}
	
	@Override
	public String upload(InputStream inputStream, String path) {
		return null;
	}
	
	@Override
	public String uploadSuffix(InputStream inputStream, String suffix) {
		return null;
	}
}
