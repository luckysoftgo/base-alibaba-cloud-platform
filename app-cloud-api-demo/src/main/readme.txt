======================================================== demo->测试多数据源的操作 ====================================================================
接口文档访问:
1.knife4j:
    http://localhost:${server.port}/doc.html
2.swagger2:
    http://localhost:${server.port}/swagger-ui.html


3.本地运行的时候,一定要将pom文件中的:
    <targetPath>${project.build.directory}/config</targetPath>
  注释掉,打包的时候,一定要将这个放开。


4.测试的接口:
    A.mybatis-plus 默认分页方式:
        http://127.0.0.1:${server.port}/test/cloudtestscore/list1?pageNum=1&pageSize=1
    B.pagehelper 分页方式:
        http://127.0.0.1:${server.port}/test/cloudtestscore/list2?pageNum=1&pageSize=1


5.数据库监控服务:
    http://127.0.0.1:${server.port}/druid/index.html
    admin/druid

6.数据操作:
    https://mybatis.plus/guide

7.使用:
    默认是使用master作为分库的主表,不配做的情况下,一定要配置上master数据源;另外指定访问的数据源可以使用注解:@DataSourceType,详细可以参见注解的内部描述.

8.本地校验服务和应用:
    http://127.0.0.1:${server.port}/doc.html
    http://127.0.0.1:${server.port}/swagger-ui.html
    http://127.0.0.1:${server.port}/test/cloudtestscore/list1?pageNum=1&pageSize=1
    http://127.0.0.1:${server.port}/test/cloudtestscore/list2?pageNum=1&pageSize=1
    http://127.0.0.1:${server.port}/druid/index.html




















































