源码来源于网上开源系统设计.
1.前提:
    必须将:app-cloud-framework-basic 的模块 clean install 进去才行.

2.配置信息
    https://www.cnblogs.com/lianggp/p/7573653.html

3.生成mongo代码的配置(默认代码是启用rdbms的mysql数据库)
    A.在yml配置文件中配置:如下例
    mongodb:
      host: localhost
      port: 27017
      auth: false #是否使用密码验证
      username: tincery
      password: Txy123.com
      source: tincery_pro
      database: admin

    B.将yml配置文件中的数据库指定修改为：
    generater:
      database: mongo

    C.启用mongo的配置,在 GeneraterApplication.java 中注释掉:
    @SpringBootApplication
    //@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})

4.代码生成器,生成完的代码,运行的前提有:
  A.mybatis-plus 分页插件
    @Configuration
    public class MybatisConfig {
        @Bean
        public PaginationInterceptor paginationInterceptor() {
            return new PaginationInterceptor();
        }

    }
