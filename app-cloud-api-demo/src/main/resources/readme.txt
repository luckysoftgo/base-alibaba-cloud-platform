1.本地运行的时候,一定要将pom文件中的:
    <targetPath>${project.build.directory}/config</targetPath>
  注释掉,打包的时候,一定要将这个放开。

2.测试的接口:
    http://127.0.0.1:${server.port}/test/cloudtestteacher/list?pageNum=1&pageSize=1

3.数据库监控服务:
http://127.0.0.1:${server.port}/druid/index.html

4.多数据源配置:
https://mybatis.plus/guide/dynamic-datasource.html

