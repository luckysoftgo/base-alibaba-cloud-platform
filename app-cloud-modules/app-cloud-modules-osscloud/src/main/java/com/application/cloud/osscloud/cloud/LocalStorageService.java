package com.application.cloud.osscloud.cloud;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
		path = config.getLocal().getFilePath()+"/"+path;
		File file = new File(path);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(data);
			fileOutputStream.flush();
		} catch (IOException e) {
			throw new RuntimeException("Write file error!");
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					throw new RuntimeException("Write file error!");
				}
			}
		}
		return path;
	}
	
	@Override
	public String uploadSuffix(byte[] data, String suffix) {
		return null;
	}
	
	@Override
	public String upload(InputStream inputStream, String path) {
		path = config.getLocal().getFilePath()+"/"+path;
		File file = new File(path);
		if(file.exists()){
			throw new IllegalArgumentException("相同KEY的文件已存在");
		}
		FileOutputStream output = null;
		try {
			file.createNewFile();
			output =  new FileOutputStream(file);
			IOUtils.copy(inputStream,output);
			output.flush();
		} catch (IOException e) {
			throw new RuntimeException("存储文件失败：" + e);
		}finally {
			if(output != null){
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path;
	}
	
	@Override
	public String uploadSuffix(InputStream inputStream, String suffix) {
		return null;
	}
}
