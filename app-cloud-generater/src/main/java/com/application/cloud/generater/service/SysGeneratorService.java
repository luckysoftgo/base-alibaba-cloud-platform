package com.application.cloud.generater.service;

import com.application.cloud.generater.mapper.GeneratorDao;
import com.application.cloud.generater.gencdoe.GenerateHolder;
import com.application.cloud.generater.mongo.config.MongoManager;
import com.application.cloud.generater.mongo.dao.MongoDBGeneratorDao;
import com.application.cloud.generater.mongo.factory.MongoDBCollectionFactory;
import com.application.cloud.generater.utils.page.PageProcessor;
import com.application.cloud.generater.utils.page.PageQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author : 孤狼
 * @NAME: SysGeneratorService
 * @DESC: SysGeneratorService 实现设计
 **/
@Service
public class SysGeneratorService  {
	
	@Autowired
	private GeneratorDao generatorDao;
	
	@Autowired(required = false)
	private MongoDBCollectionFactory mongoDBCollectionFactory;
	
	/**
	 * 查询表(集合)列表
	 * @param query
	 * @return
	 */
	public PageProcessor queryList(PageQuery query) {
		Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<Map<String, Object>> list = generatorDao.queryList(query);
		int total = (int) page.getTotal();
		if (generatorDao instanceof MongoDBGeneratorDao) {
			total = mongoDBCollectionFactory.getCollectionTotal(query);
		}
		return new PageProcessor(list, total, query.getLimit(), query.getPage());
	}
	
	/**
	 * 查詢单个
	 * @param tableName
	 * @return
	 */
	public Map<String, String> queryTable(String tableName) {
		return generatorDao.queryTable(tableName);
	}
	
	/**
	 * 查询列信息.
	 * @param tableName
	 * @return
	 */
	public List<Map<String, String>> queryColumns(String tableName) {
		return generatorDao.queryColumns(tableName);
	}
	
	/**
	 * 生成代码.
	 * @param tableNames
	 * @return
	 */
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		for (String tableName : tableNames) {
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenerateHolder.generatorCode(table, columns, zip);
		}
		if (MongoManager.isMongo()) {
			GenerateHolder.generatorMongoCode(tableNames, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
}
