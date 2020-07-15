## 运行必备
redis 3.2+ ;
mysql 5.7+ ;
nacos 1.3+ ;
sentinel 1.7+ ;
maven 3.6+ ; 
...

## 使用

```bash
# 克隆项目
git clone https://github.com/luckysoftgo/base-alibaba-cloud-platform.git

# 初始化数据库
执行initSql/mysql下的数据文件到指定的mysql中去.

# 修改配置
nacos :
    下载:
        https://github.com/alibaba/nacos/tags
    访问:
        http://127.0.0.1:8848/nacos/index.html

修改其中mysql的连接配置.主要是mysql的数据库名,用户名,密码,字符集等等.

# 配置sentinel
Sentinel :
    下载:
        https://github.com/alibaba/Sentinel/tags
    访问:
        http://localhost:port/#/dashboard/home

此配置主要是在nacos配置表sentinel-app-cloud-gateway的配置项.


# 启动服务
无关顺序,可以挨个启动每个SpringBoot项目.
在启动前端工程app-cloudwebsite
如此就可以进行系统的访问了.


## 默认
1.默认登录 Sentinel 的用户名和密码是: sentinel/sentinel
2.默认登录管理端的用户名和密码是: admin /admin123
3.默认监控的用户名和密码是:app-cloud / 123456

