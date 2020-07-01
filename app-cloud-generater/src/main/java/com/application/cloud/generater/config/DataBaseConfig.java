package com.application.cloud.generater.config;

import com.application.cloud.generater.dao.GeneratorDao;
import com.application.cloud.generater.dao.MySQLGeneratorDao;
import com.application.cloud.generater.dao.OracleGeneratorDao;
import com.application.cloud.generater.dao.PostgreSQLGeneratorDao;
import com.application.cloud.generater.dao.SQLServerGeneratorDao;
import com.application.cloud.generater.exception.GenException;
import com.application.cloud.generater.mongo.dao.MongoDBGeneratorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author : 孤狼
 * @NAME: DataBaseConfig
 * @DESC: DataBaseConfig 类设计
 **/
@Configuration
public class DataBaseConfig {

    @Value("${generater.database: mysql}")
    private String database;
	
    @Autowired
    private MySQLGeneratorDao mySQLGeneratorDao;

    @Autowired
    private OracleGeneratorDao oracleGeneratorDao;

    @Autowired
    private SQLServerGeneratorDao sqlServerGeneratorDao;

    @Autowired
    private PostgreSQLGeneratorDao postgreSQLGeneratorDao;
    
	@Autowired(required = false)
	private MongoDBGeneratorDao mongoDBGeneratorDao;
	
	/**
	 * 是否是mongodb数据库.
	 */
    private static boolean isMongo = false;
	
	/**
	 * 初始化操作.
	 * @return
	 */
	@Bean
    @Primary
    public GeneratorDao getGeneratorDao(){
        database = database.trim();
        if("mysql".equalsIgnoreCase(database)){
            return mySQLGeneratorDao;
        }else if("oracle".equalsIgnoreCase(database)){
            return oracleGeneratorDao;
        }else if("sqlserver".equalsIgnoreCase(database)){
            return sqlServerGeneratorDao;
        }else if("postgresql".equalsIgnoreCase(database)){
            return postgreSQLGeneratorDao;
        } else if ("mongodb".equalsIgnoreCase(database)) {
	        isMongo = true;
	        return mongoDBGeneratorDao;
        } else {
	        throw new GenException("不支持当前数据库：" + database);
        }
    }
	
	/**
	 * 是否 mongodb
	 * @return
	 */
	public static boolean isMongo() {
		return isMongo;
	}
	
}
