DROP database IF EXISTS cloud_app_test;
CREATE DATABASE IF NOT EXISTS cloud_app_test DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- NonEntity
DROP TABLE IF EXISTS cloud_test_info;
CREATE TABLE cloud_test_info (
  inst_id varchar(64) NOT NULL DEFAULT ''COMMENT '实例的ID',
  login_port int(12) NOT NULL DEFAULT 0 COMMENT '登录端口',
  login_ip varchar(64) NOT NULL DEFAULT '' COMMENT '登录ip',
  req_server varchar(20) DEFAULT '' COMMENT '服务名称',
  req_param varchar(256) DEFAULT '' COMMENT '请求参数',
  PRIMARY KEY (inst_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录信息记录';


-- IdEntity
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
  id bigint(11) NOT NULL AUTO_INCREMENT COMMENT '认证配置主键',
  client_id varchar(64) NOT NULL DEFAULT ''COMMENT '认证应用标识',
  application_name varchar(64) NOT NULL DEFAULT '' COMMENT '认证应用名称',
  resource_ids varchar(1024) DEFAULT '' COMMENT '资源限定串(逗号分割)',
  client_secret varchar(256) DEFAULT '' COMMENT '认证应用密钥(bcyt加密)',
  client_secret_content varchar(256) DEFAULT '' COMMENT '认证应用密钥(明文)',
  client_scope varchar(256) DEFAULT '' COMMENT '认证应用范围',
  authorized_grant_types varchar(256) DEFAULT '' COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
  web_server_redirect_uri varchar(256) DEFAULT '' COMMENT '认证回调地址',
  authorities varchar(256) DEFAULT '' COMMENT '认证权限设置',
  access_token_validity int(11) DEFAULT 0 COMMENT 'access_token有效期',
  refresh_token_validity int(11) DEFAULT 0 COMMENT 'refresh_token有效期',
  additional_information varchar(4096) DEFAULT '{}' COMMENT '{}认证附加信息',
  auto_approve tinyint(1) DEFAULT 1 COMMENT '是否自动授权:1:是;2:否',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='认证端管理信息';


-- BasicEntity
DROP TABLE IF EXISTS cloud_test_score;
CREATE TABLE IF NOT EXISTS cloud_test_score (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
   user_id  bigint(20) NOT NULL DEFAULT 0 COMMENT '学生id',
   user_name  varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
   chinese_socre  int(10) NOT NULL DEFAULT 0 COMMENT '语文',
   math_socre  int(10) NOT NULL DEFAULT 0 COMMENT '数学',
   english_socre  int(10) NOT NULL DEFAULT 0 COMMENT '英语',
   create_by  varchar(20) DEFAULT '' COMMENT '创建者',
   create_time  datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
   update_by  varchar(20) DEFAULT '' COMMENT '更新者',
   update_time  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='成绩信息';


-- GenericEntity
DROP TABLE IF EXISTS cloud_test_teacher;
CREATE TABLE IF NOT EXISTS cloud_test_teacher (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
   disabled  tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
   inst_id   varchar(32) DEFAULT '' COMMENT '业务主键id',
   teach_name  varchar(20) DEFAULT '' COMMENT '教师名称',
   teach_gender  tinyint(1) NOT NULL DEFAULT 0 COMMENT '性别,1男,2女',
   teach_age  int(10) NOT NULL DEFAULT 0 COMMENT '年龄',
   teach_phone  char(11) NOT NULL DEFAULT '' COMMENT '联系电话',
   graduate_date  datetime NOT NULL COMMENT '毕业时间',
   teac_salary decimal(10,2) NOT NULL DEFAULT '0.0' COMMENT '薪资待遇',
   info_desc  text COMMENT '描述信息',
   create_by  varchar(20) DEFAULT '' COMMENT '创建者',
   create_time  datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
   update_by  varchar(20) DEFAULT '' COMMENT '更新者',
   update_time  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='教师信息表';

