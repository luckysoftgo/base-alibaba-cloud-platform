DROP DATABASE IF EXISTS app_cloud_config;

CREATE DATABASE  app_cloud_config DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE app_cloud_config;

-- ----------------------------
-- 表名称 = config_info
-- ----------------------------
drop table if exists config_info;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) DEFAULT NULL,
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(20) DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) DEFAULT NULL,
  `c_use` varchar(64) DEFAULT NULL,
  `effect` varchar(64) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  `c_schema` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='nacos管理配置表';

INSERT INTO `config_info` VALUES('1', 'application-dev.yml', 'DEFAULT_GROUP', '#请求处理的超时时间\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000\n\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 认证配置\nsecurity:\n  oauth2:\n    client:\n      client-id: app-cloud\n      client-secret: 123456\n      scope: server\n    resource:\n      loadBalanced: true\n      token-info-uri: http://app-cloud-authclient/oauth/check_token\n    ignore:\n      urls:\n        - /v2/api-docs\n        - /actuator/**\n        - /user/info/*\n        - /operlog\n        - /logininfor\n', 'ee32a74bd3e4692be21f50341dbc589e', '2019-11-29 16:31:20', '2020-07-15 06:41:07', NULL, '0:0:0:0:0:0:0:1', '', '', '通用配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES('2', 'app-cloud-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\r\n  redis:\r\n    host: localhost\r\n    port: 6379\r\n    password: \r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          lowerCaseServiceId: true\r\n          enabled: true\r\n      routes:\r\n        # 认证中心\r\n        - id: app-cloud-authclient\r\n          uri: lb://app-cloud-authclient\r\n          predicates:\r\n            - Path=/auth/**\r\n          filters:\r\n            # 验证码处理\r\n            - ValidateCodeFilter\r\n            - StripPrefix=1\r\n        # 代码生成\r\n        - id: app-cloud-gencode\r\n          uri: lb://app-cloud-gencode\r\n          predicates:\r\n            - Path=/code/**\r\n          filters:\r\n            - StripPrefix=1\r\n        # 定时任务\r\n        - id: app-cloud-jobtask\r\n          uri: lb://app-cloud-jobtask\r\n          predicates:\r\n            - Path=/schedule/**\r\n          filters:\r\n            - StripPrefix=1\r\n        # 系统模块\r\n       - id: app-cloud-system\r\n          uri: lb://app-cloud-system\r\n          predicates:\r\n            - Path=/system/**\r\n          filters:\r\n            - name: BlackListUrlFilter\r\n              args:\r\n                blacklistUrl:\r\n                  - /user/info/*\r\n            - StripPrefix=1\r\n        # 文件模块\r\n        - id: app-cloud-osscloud\r\n          uri: lb://app-cloud-osscloud\r\n          predicates:\r\n            - Path=/oss/**\r\n          filters:\r\n            - StripPrefix=1', 'd1d7607e248e921323e61e8d3c8ea7d7', '2020-05-14 14:17:55', '2020-07-24 03:47:39', NULL, '0:0:0:0:0:0:0:1', '', '', '网关模块', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES('3', 'app-cloud-authclient-dev.yml', 'DEFAULT_GROUP', 'spring: \r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/app_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: 123456\r\n  redis:\r\n    host: localhost\r\n    port: 6379\r\n    password: \r\n', '2d935540e7e2942a02f59ed6fe98825a', '2020-05-14 13:20:49', '2020-07-13 08:29:18', NULL, '0:0:0:0:0:0:0:1', '', '', '认证中心', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES('4', 'app-cloud-monitor-dev.yml', 'DEFAULT_GROUP', '# Spring\r\nspring: \r\n  security:\r\n    user:\r\n      name: app-cloud\r\n      password: 123456\r\n  boot:\r\n    admin:\r\n      ui:\r\n        title: 青云服务状态监控\r\n', '8e49d78998a7780d780305aeefe4fb1b', '2020-05-19 15:14:01', '2020-05-19 18:50:44', NULL, '0:0:0:0:0:0:0:1', '', '', '监控中心', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES('5', 'app-cloud-system-dev.yml', 'DEFAULT_GROUP', '# Spring\r\nspring: \r\n  redis:\r\n    host: localhost\r\n    port: 6379\r\n    password: \r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/app_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: 123456\r\n\r\n# Mybatis配置\r\nmybatis:\r\n    # 搜索指定包别名\r\n    typeAliasesPackage: com.application.cloud.system\r\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\r\n    mapperLocations: classpath:mapper/**/*.xml\r\n\r\n# swagger 配置\r\nswagger:\r\n  title: 系统模块接口文档\r\n  license: Powered By app-cloud\r\n  licenseUrl: https://app-cloud.vip\r\n  authorization:\r\n    name: Cloud OAuth\r\n    auth-regex: ^.*$\r\n    authorization-scope-list:\r\n      - scope: server\r\n        description: 客户端授权范围\r\n    token-url-list:\r\n      - http://localhost:8080/auth/oauth/token\r\n', '8866962d417d694b63162ce131a1cba6', '2020-05-14 13:37:04', '2020-07-13 08:29:44', NULL, '0:0:0:0:0:0:0:1', '', '', '系统模块', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES('6', 'app-cloud-gencode-dev.yml', 'DEFAULT_GROUP', '# Spring\r\nspring: \r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/app_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: 123456\r\n\r\n# Mybatis配置\r\nmybatis:\r\n    # 搜索指定包别名\r\n    typeAliasesPackage: com.application.cloud.gen.domain\r\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\r\n    mapperLocations: classpath:mapper/**/*.xml\r\n\r\n# swagger 配置\r\nswagger:\r\n  title: 代码生成接口文档\r\n  license: Powered By app-cloud\r\n  licenseUrl: https://app-cloud.vip\r\n  authorization:\r\n    name: Cloud OAuth\r\n    auth-regex: ^.*$\r\n    authorization-scope-list:\r\n      - scope: server\r\n        description: 客户端授权范围\r\n    token-url-list:\r\n      - http://localhost:8080/auth/oauth/token\r\n\r\n# 代码生成\r\ngen: \r\n  # 作者\r\n  author: app-cloud\r\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\r\n  packageName: com.application.cloud.system\r\n  # 自动去除表前缀，默认是false\r\n  autoRemovePre: false\r\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\r\n  tablePrefix: sys_\r\n', '6aed4deafd11d825ae2e2ea31f3cb03f', '2020-05-14 13:54:50', '2020-07-13 08:30:05', NULL, '0:0:0:0:0:0:0:1', '', '', '代码生成', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES('7', 'app-cloud-jobtask-dev.yml', 'DEFAULT_GROUP', '# Spring\r\nspring: \r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/app_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: 123456\r\n\r\n# Mybatis配置\r\nmybatis:\r\n    # 搜索指定包别名\r\n    typeAliasesPackage: com.application.cloud.job.domain\r\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\r\n    mapperLocations: classpath:mapper/**/*.xml\r\n\r\n# swagger 配置\r\nswagger:\r\n  title: 定时任务接口文档\r\n  license: Powered By app-cloud\r\n  licenseUrl: https://app-cloud.vip\r\n  authorization:\r\n    name: Cloud OAuth\r\n    auth-regex: ^.*$\r\n    authorization-scope-list:\r\n      - scope: server\r\n        description: 客户端授权范围\r\n    token-url-list:\r\n      - http://localhost:8080/auth/oauth/token\r\n', '0241c92e01a7ce408e8816037145c536', '2020-05-14 13:58:46', '2020-07-13 08:30:27', NULL, '0:0:0:0:0:0:0:1', '', '', '定时任务', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES('8', 'sentinel-app-cloud-gateway', 'DEFAULT_GROUP', '[\r\n    {\r\n        \"resource\": \"app-cloud-authclient\",\r\n        \"count\": 500,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"app-cloud-system\",\r\n        \"count\": 1000,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"app-cloud-gencode\",\r\n        \"count\": 200,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"app-cloud-jobtask\",\r\n        \"count\": 300,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    }\r\n    ,\r\n	{\r\n        \"resource\": \"app-cloud-osscloud\",\r\n        \"count\": 100,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    }\r\n]', '3685b8a8951375117b63935511ab2d4e', '2020-06-09 12:14:01', '2020-07-23 08:42:29', NULL, '0:0:0:0:0:0:0:1', '', '', '限流网关', 'null', 'null', 'json', 'null');
INSERT INTO `config_info` VALUES('9', 'app-cloud-osscloud-dev.yml', 'DEFAULT_GROUP', '# Spring\r\nspring:\r\n  redis:\r\n    host: localhost\r\n    port: 6379\r\n    password:\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/app_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: 123456\r\n\r\n# Mybatis配置\r\nmybatis:\r\n  # 搜索指定包别名\r\n  typeAliasesPackage: com.application.cloud.osscloud\r\n  # 配置mapper的扫描，找到所有的mapper.xml映射文件\r\n  mapperLocations: classpath:mapper/**/*.xml\r\n\r\n# swagger 配置\r\nswagger:\r\n  title: 系统模块接口文档\r\n  license: Powered By app-cloud\r\n  licenseUrl: https://app-cloud.vip\r\n  authorization:\r\n    name: Cloud OAuth\r\n    auth-regex: ^.*$\r\n    authorization-scope-list:\r\n      - scope: server\r\n        description: 客户端授权范围\r\n    token-url-list:\r\n      - http://localhost:8080/auth/oauth/token\r\n\r\n\r\n# 文件系统\r\noss:\r\n  file:\r\n    # 是否是云服务器\r\n    ossFlag: false\r\n    # 类型 1：阿里云  2：七牛云  3：腾讯云\r\n    type: 0\r\n    # 本地存储\r\n    local:\r\n      filePath: d:\\\\data\\\\files\r\n    # 阿里云服务器的配置\r\n    aliyun:\r\n      domain: www.ali.com\r\n      prefix: ali_\r\n      endPoint: 123456\r\n      accessKeyId: 123456\r\n      accessKeySecret: 123456\r\n      bucketName: test\r\n    # 七牛云服务器的配置\r\n    qiniu:\r\n      domain: www.ali.com\r\n      prefix: ali_\r\n      accessKey: 123456\r\n      secretKey: 123456\r\n      bucketName: test\r\n    # 腾讯云服务器的配置\r\n    qqcloud:\r\n      domain: www.ali.com\r\n      prefix: ali_\r\n      appId: 123456\r\n      secretId: 123456\r\n      secretKey: 123456\r\n      bucketName: test\r\n      region: demo\r\n\r\n', 'b2bc48e831837f653f0920e0a3478c1c', '2020-07-23 08:39:47', '2020-07-23 09:34:26', NULL, '0:0:0:0:0:0:0:1', '', '', '文件服务', 'null', 'null', 'yaml', 'null');


-- ----------------------------
-- 表名称 = config_info_aggr
-- ----------------------------
drop table if exists config_info_aggr;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(128) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `datum_id` varchar(128) NOT NULL COMMENT 'datum_id',
  `content` longtext NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='增加租户字段';


-- ----------------------------
-- 表名称 = config_info_beta
-- ----------------------------
drop table if exists config_info_beta;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(20) DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='nacos管理配置表beta版';


-- ----------------------------
-- 表名称 = config_info_tag
-- ----------------------------
drop table if exists config_info_tag;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(20) DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='nacos管理配置表tag';


-- ----------------------------
-- 表名称 = config_tags_relation
-- ----------------------------
drop table if exists config_tags_relation;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='nacos管理配置表依赖';


-- ----------------------------
-- 表名称 = group_capacity
-- ----------------------------
drop table if exists group_capacity;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='集群、各Group容量信息表';


-- ----------------------------
-- 表名称 = his_config_info
-- ----------------------------
drop table if exists his_config_info;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text,
  `src_ip` varchar(20) DEFAULT NULL,
  `op_type` char(10) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='多租户改造';


-- ----------------------------
-- 表名称 = tenant_capacity
-- ----------------------------
drop table if exists tenant_capacity;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='租户容量信息表';

-- ----------------------------
-- 表名称 = tenant_info
-- ----------------------------
drop table if exists tenant_info;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) default '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) default '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='租户信息表';

-- ----------------------------
-- 表名称 = users
-- ----------------------------
drop table if exists users;
CREATE TABLE `users` (
	`username` varchar(50) NOT NULL PRIMARY KEY,
	`password` varchar(500) NOT NULL,
	`enabled` boolean NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='nacos用户表';

-- ----------------------------
-- 表名称 = roles
-- ----------------------------
drop table if exists roles;
CREATE TABLE `roles` (
	`username` varchar(50) NOT NULL,
	`role` varchar(50) NOT NULL,
	UNIQUE INDEX `idx_user_role` (`username` ASC, `role` ASC) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='nacos角色表';

-- ----------------------------
-- 表名称 = permissions
-- ----------------------------
drop table if exists permissions;
CREATE TABLE `permissions` (
    `role` varchar(50) NOT NULL,
    `resource` varchar(512) NOT NULL,
    `action` varchar(8) NOT NULL,
    UNIQUE INDEX `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='nacos权限表';

INSERT INTO users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);
INSERT INTO roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');
