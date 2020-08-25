DROP DATABASE IF EXISTS app_cloud;

CREATE DATABASE app_cloud DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE app_cloud;

-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id           bigint(20)      not null auto_increment    comment '部门id',
  parent_id         bigint(20)      default 0                  comment '父部门id',
  ancestors         varchar(50)     default ''                 comment '祖级列表',
  dept_name         varchar(30)     default ''                 comment '部门名称',
  order_num         int(4)          default 0                  comment '显示顺序',
  leader            varchar(20)     default null               comment '负责人',
  phone             varchar(11)     default null               comment '联系电话',
  email             varchar(50)     default null               comment '邮箱',
  status            char(1)         default '0'                comment '部门状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (dept_id)
) ENGINE=InnoDB auto_increment=200 DEFAULT CHARSET=utf8mb4 COMMENT= '部门表';

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept values(100,  0,   '0',          '青云科技',   0, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(101,  100, '0,100',      '深圳总公司', 1, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(102,  100, '0,100',      '长沙分公司', 2, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(103,  101, '0,100,101',  '研发部门',   1, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(104,  101, '0,100,101',  '市场部门',   2, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(105,  101, '0,100,101',  '测试部门',   3, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(106,  101, '0,100,101',  '财务部门',   4, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(107,  101, '0,100,101',  '运维部门',   5, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(108,  102, '0,100,102',  '市场部门',   1, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');
insert into sys_dept values(109,  102, '0,100,102',  '财务部门',   2, '青云', '15888888888', 'cloud@qq.com', '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00');


-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id           bigint(20)      not null auto_increment    comment '用户ID',
  dept_id           bigint(20)      default null               comment '部门ID',
  user_name         varchar(30)     not null                   comment '用户账号',
  nick_name         varchar(30)     not null                   comment '用户昵称',
  user_type         varchar(2)      default '00'               comment '用户类型（00系统用户）',
  email             varchar(50)     default ''                 comment '用户邮箱',
  phonenumber       varchar(11)     default ''                 comment '手机号码',
  sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
  avatar            varchar(100)    default ''                 comment '头像地址',
  password          varchar(100)    default ''                 comment '密码',
  status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  login_ip          varchar(50)     default ''                 comment '最后登陆IP',
  login_date        datetime                                   comment '最后登陆时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (user_id)
) ENGINE=InnoDB auto_increment=100 DEFAULT CHARSET=utf8mb4 COMMENT= '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user values(1,  103, 'admin', '青云', '00', 'cloud@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2020-03-16 11-33-00', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '管理员'); -- admin/admin123
insert into sys_user values(2,  105, 'cloud',    '青云', '00', 'cloud@qq.com',  '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2020-03-16 11-33-00', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '测试员'); -- cloud/admin123


-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
  post_id       bigint(20)      not null auto_increment    comment '岗位ID',
  post_code     varchar(64)     not null                   comment '岗位编码',
  post_name     varchar(50)     not null                   comment '岗位名称',
  post_sort     int(4)          not null                   comment '显示顺序',
  status        char(1)         not null                   comment '状态（0正常 1停用）',
  create_by     varchar(64)     default ''                 comment '创建者',
  create_time   datetime                                   comment '创建时间',
  update_by     varchar(64)     default ''			       comment '更新者',
  update_time   datetime                                   comment '更新时间',
  remark        varchar(500)    default null               comment '备注',
  primary key (post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT= '岗位信息表';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post values(1, 'ceo',  '董事长',    1, '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_post values(2, 'se',   '项目经理',  2, '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_post values(3, 'hr',   '人力资源',  3, '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_post values(4, 'user', '普通员工',  4, '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id           bigint(20)      not null auto_increment    comment '角色ID',
  role_name         varchar(30)     not null                   comment '角色名称',
  role_key          varchar(100)    not null                   comment '角色权限字符串',
  role_sort         int(4)          not null                   comment '显示顺序',
  data_scope        char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  status            char(1)         not null                   comment '角色状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (role_id)
) ENGINE=InnoDB auto_increment=100 DEFAULT CHARSET=utf8mb4 COMMENT= '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values('1', '系统管理员',  'admin',  1, 1, '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统管理员');
insert into sys_role values('2', '普通角色',    'common', 2, 2, '0', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '普通角色');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  path              varchar(200)    default ''                 comment '路由地址',
  component         varchar(255)    default null               comment '组件路径',
  is_frame          int(1)          default 1                  comment '是否为外链（0是 1否）',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  visible           char(1)         default 0                  comment '菜单状态（0显示 1隐藏）',
  status            char(1)         default 0                  comment '菜单状态（0正常 1停用）',
  perms             varchar(100)    default null               comment '权限标识',
  icon              varchar(100)    default '#'                comment '菜单图标',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (menu_id)
) ENGINE=InnoDB auto_increment=2000 DEFAULT CHARSET=utf8mb4 COMMENT= '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu values('1', '系统管理', '0', '1', 'system',           null,   1, 'M', '0', '0', '', 'system',   'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统管理目录');
insert into sys_menu values('2', '系统监控', '0', '2', 'monitor',          null,   1, 'M', '0', '0', '', 'monitor',  'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统监控目录');
insert into sys_menu values('3', '系统工具', '0', '3', 'tool',             null,   1, 'M', '0', '0', '', 'tool',     'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统工具目录');
insert into sys_menu values('2000', '文件系统', '0', '4', 'oss',           null,   1, 'M', '0', '0', '', 'upload',   'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统工具目录');

-- 二级菜单
insert into sys_menu values('100',  '用户管理',       '1',   '1',  'user',                                'system/user/index',       1, 'C', '0', '0', 'system:user:list',        'user',          'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '用户管理菜单');
insert into sys_menu values('101',  '角色管理',       '1',   '2',  'role',                                'system/role/index',       1, 'C', '0', '0', 'system:role:list',        'peoples',       'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '角色管理菜单');
insert into sys_menu values('102',  '菜单管理',       '1',   '3',  'menu',                                'system/menu/index',       1, 'C', '0', '0', 'system:menu:list',        'tree-table',    'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '菜单管理菜单');
insert into sys_menu values('103',  '部门管理',       '1',   '4',  'dept',                                'system/dept/index',       1, 'C', '0', '0', 'system:dept:list',        'tree',          'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '部门管理菜单');
insert into sys_menu values('104',  '岗位管理',       '1',   '5',  'post',                                'system/post/index',       1, 'C', '0', '0', 'system:post:list',        'post',          'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '岗位管理菜单');
insert into sys_menu values('105',  '字典管理',       '1',   '6',  'dict',                                'system/dict/index',       1, 'C', '0', '0', 'system:dict:list',        'dict',          'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '字典管理菜单');
insert into sys_menu values('106',  '参数设置',       '1',   '7',  'config',                              'system/config/index',     1, 'C', '0', '0', 'system:config:list',      'edit',          'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '参数设置菜单');
insert into sys_menu values('107',  '终端设置',       '1',   '8',  'client',                              'system/client/index',     1, 'C', '0', '0', 'system:client:list',      'client',        'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '终端设置菜单');
insert into sys_menu values('108',  '通知公告',       '1',   '9',  'notice',                              'system/notice/index',     1, 'C', '0', '0', 'system:notice:list',      'message',       'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '通知公告菜单');
insert into sys_menu values('109',  '日志管理',       '1',   '10', 'log',                                 'system/log/index',        1, 'M', '0', '0', '',                        'log',           'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '日志管理菜单');
insert into sys_menu values('110',  '定时任务',       '2',   '1',  'job',                                 'monitor/job/index',       1, 'C', '0', '0', 'monitor:job:list',        'job',           'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '定时任务菜单');
insert into sys_menu values('111',  'Sentinel控制台', '2',   '2',  'http://localhost:8718',                '',                       1, 'C', '0', '0', 'monitor:sentinel:list',   'sentinel',      'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '流量控制菜单');
insert into sys_menu values('112',  'Nacos控制台',    '2',   '3',  'http://localhost:8848/nacos',          '',                       1, 'C', '0', '0', 'monitor:nacos:list',      'nacos',         'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '服务治理菜单');
insert into sys_menu values('113',  'Admin控制台',    '2',   '4',  'http://localhost:9100/login',          '',                       1, 'C', '0', '0', 'monitor:server:list',     'server',        'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '服务监控菜单');
insert into sys_menu values('114',  '表单构建',       '3',   '1',  'build',                                'tool/build/index',       1 ,'C', '0', '0', 'tool:build:list',         'build',         'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '表单构建菜单');
insert into sys_menu values('115',  '代码生成',       '3',   '2',  'gen',                                  'tool/gen/index',         1, 'C', '0', '0', 'tool:gen:list',           'code',          'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '代码生成菜单');
insert into sys_menu values('116',  '系统接口',       '3',   '3',  'http://localhost:8080/swagger-ui.html', '',                      1, 'C', '0', '0', 'tool:swagger:list',       'swagger',       'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统接口菜单');
insert into sys_menu values('2001', '文件管理',       '2000', '4', 'file',                                  'oss/file/index',        1, 'C', '0', '0', 'oss:file:list',           'client',        'admin', '2020-07-24 10:47:13', 'cloud', '2020-07-24 10:47:13', '文件系统菜单');

-- 三级菜单
insert into sys_menu values('500',  '操作日志', '109', '1', 'operlog',    'system/operlog/index',     1, 'C', '0', '0', 'system:operlog:list',     'form',          'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '操作日志菜单');
insert into sys_menu values('501',  '登录日志', '109', '2', 'logininfor', 'system/logininfor/index',  1, 'C', '0', '0', 'system:logininfor:list',  'logininfor',    'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '登录日志菜单');

-- 用户管理按钮
insert into sys_menu values('1001', '用户查询', '100', '1',  '', '', 1, 'F', '0', '0', 'system:user:query',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1002', '用户新增', '100', '2',  '', '', 1, 'F', '0', '0', 'system:user:add',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1003', '用户修改', '100', '3',  '', '', 1, 'F', '0', '0', 'system:user:edit',           '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1004', '用户删除', '100', '4',  '', '', 1, 'F', '0', '0', 'system:user:remove',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1005', '用户导出', '100', '5',  '', '', 1, 'F', '0', '0', 'system:user:export',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1006', '用户导入', '100', '6',  '', '', 1, 'F', '0', '0', 'system:user:import',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1007', '重置密码', '100', '7',  '', '', 1, 'F', '0', '0', 'system:user:resetPwd',       '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 角色管理按钮
insert into sys_menu values('1008', '角色查询', '101', '1',  '', '', 1, 'F', '0', '0', 'system:role:query',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1009', '角色新增', '101', '2',  '', '', 1, 'F', '0', '0', 'system:role:add',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1010', '角色修改', '101', '3',  '', '', 1, 'F', '0', '0', 'system:role:edit',           '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1011', '角色删除', '101', '4',  '', '', 1, 'F', '0', '0', 'system:role:remove',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1012', '角色导出', '101', '5',  '', '', 1, 'F', '0', '0', 'system:role:export',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 菜单管理按钮
insert into sys_menu values('1013', '菜单查询', '102', '1',  '', '', 1, 'F', '0', '0', 'system:menu:query',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1014', '菜单新增', '102', '2',  '', '', 1, 'F', '0', '0', 'system:menu:add',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1015', '菜单修改', '102', '3',  '', '', 1, 'F', '0', '0', 'system:menu:edit',           '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1016', '菜单删除', '102', '4',  '', '', 1, 'F', '0', '0', 'system:menu:remove',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 部门管理按钮
insert into sys_menu values('1017', '部门查询', '103', '1',  '', '', 1, 'F', '0', '0', 'system:dept:query',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1018', '部门新增', '103', '2',  '', '', 1, 'F', '0', '0', 'system:dept:add',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1019', '部门修改', '103', '3',  '', '', 1, 'F', '0', '0', 'system:dept:edit',           '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1020', '部门删除', '103', '4',  '', '', 1, 'F', '0', '0', 'system:dept:remove',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 岗位管理按钮
insert into sys_menu values('1021', '岗位查询', '104', '1',  '', '', 1, 'F', '0', '0', 'system:post:query',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1022', '岗位新增', '104', '2',  '', '', 1, 'F', '0', '0', 'system:post:add',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1023', '岗位修改', '104', '3',  '', '', 1, 'F', '0', '0', 'system:post:edit',           '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1024', '岗位删除', '104', '4',  '', '', 1, 'F', '0', '0', 'system:post:remove',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1025', '岗位导出', '104', '5',  '', '', 1, 'F', '0', '0', 'system:post:export',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 字典管理按钮
insert into sys_menu values('1026', '字典查询', '105', '1', '#', '', 1, 'F', '0', '0', 'system:dict:query',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1027', '字典新增', '105', '2', '#', '', 1, 'F', '0', '0', 'system:dict:add',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1028', '字典修改', '105', '3', '#', '', 1, 'F', '0', '0', 'system:dict:edit',           '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1029', '字典删除', '105', '4', '#', '', 1, 'F', '0', '0', 'system:dict:remove',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1030', '字典导出', '105', '5', '#', '', 1, 'F', '0', '0', 'system:dict:export',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 参数设置按钮
insert into sys_menu values('1031', '参数查询', '106', '1', '#', '', 1, 'F', '0', '0', 'system:config:query',        '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1032', '参数新增', '106', '2', '#', '', 1, 'F', '0', '0', 'system:config:add',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1033', '参数修改', '106', '3', '#', '', 1, 'F', '0', '0', 'system:config:edit',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1034', '参数删除', '106', '4', '#', '', 1, 'F', '0', '0', 'system:config:remove',       '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1035', '参数导出', '106', '5', '#', '', 1, 'F', '0', '0', 'system:config:export',       '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 终端设置按钮
insert into sys_menu values('1036', '终端查询', '107', '1', '#', '', 1, 'F', '0', '0', 'system:client:query',        '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1037', '终端新增', '107', '2', '#', '', 1, 'F', '0', '0', 'system:client:add',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1038', '终端修改', '107', '3', '#', '', 1, 'F', '0', '0', 'system:client:edit',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1039', '终端删除', '107', '4', '#', '', 1, 'F', '0', '0', 'system:client:remove',       '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1040', '终端导出', '107', '5', '#', '', 1, 'F', '0', '0', 'system:client:export',       '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 通知公告按钮
insert into sys_menu values('1041', '公告查询', '108', '1', '#', '', 1, 'F', '0', '0', 'system:notice:query',        '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1042', '公告新增', '108', '2', '#', '', 1, 'F', '0', '0', 'system:notice:add',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1043', '公告修改', '108', '3', '#', '', 1, 'F', '0', '0', 'system:notice:edit',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1044', '公告删除', '108', '4', '#', '', 1, 'F', '0', '0', 'system:notice:remove',       '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 操作日志按钮
insert into sys_menu values('1045', '操作查询', '500', '1', '#', '', 1, 'F', '0', '0', 'system:operlog:query',       '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1046', '操作删除', '500', '2', '#', '', 1, 'F', '0', '0', 'system:operlog:remove',      '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1047', '日志导出', '500', '4', '#', '', 1, 'F', '0', '0', 'system:operlog:export',      '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 登录日志按钮
insert into sys_menu values('1048', '登录查询', '501', '1', '#', '', 1, 'F', '0', '0', 'system:logininfor:query',    '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1049', '登录删除', '501', '2', '#', '', 1, 'F', '0', '0', 'system:logininfor:remove',   '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1050', '日志导出', '501', '3', '#', '', 1, 'F', '0', '0', 'system:logininfor:export',   '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 定时任务按钮
insert into sys_menu values('1051', '任务查询', '110', '1', '#', '', 1, 'F', '0', '0', 'monitor:job:query',          '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1052', '任务新增', '110', '2', '#', '', 1, 'F', '0', '0', 'monitor:job:add',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1053', '任务修改', '110', '3', '#', '', 1, 'F', '0', '0', 'monitor:job:edit',           '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1054', '任务删除', '110', '4', '#', '', 1, 'F', '0', '0', 'monitor:job:remove',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1055', '状态修改', '110', '5', '#', '', 1, 'F', '0', '0', 'monitor:job:changeStatus',   '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1056', '任务导出', '110', '7', '#', '', 1, 'F', '0', '0', 'monitor:job:export',         '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 代码生成按钮
insert into sys_menu values('1057', '生成查询', '115', '1', '#', '', 1, 'F', '0', '0', 'tool:gen:query',             '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1058', '生成修改', '115', '2', '#', '', 1, 'F', '0', '0', 'tool:gen:edit',              '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1059', '生成删除', '115', '3', '#', '', 1, 'F', '0', '0', 'tool:gen:remove',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1060', '导入代码', '115', '2', '#', '', 1, 'F', '0', '0', 'tool:gen:import',            '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1061', '预览代码', '115', '4', '#', '', 1, 'F', '0', '0', 'tool:gen:preview',           '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_menu values('1062', '生成代码', '115', '5', '#', '', 1, 'F', '0', '0', 'tool:gen:code',              '#', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');

-- 文件系统菜单

insert into sys_menu values('2002', '文件管理查询', '2001', '1', '#',    '',               1, 'F', '0', '0', 'oss:file:query', '#',      'admin', '2020-03-01 00:00:00', 'cloud', '2020-03-01 00:00:00', '');
insert into sys_menu values('2003', '文件管理新增', '2001', '2', '#',    '',               1, 'F', '0', '0', 'oss:file:add',   '#',      'admin', '2020-03-01 00:00:00', 'cloud', '2020-03-01 00:00:00', '');
insert into sys_menu values('2004', '文件管理修改', '2001', '3', '#',    '',               1, 'F', '0', '0', 'oss:file:edit',  '#',      'admin', '2020-03-01 00:00:00', 'cloud', '2020-03-01 00:00:00', '');
insert into sys_menu values('2005', '文件管理删除', '2001', '4', '#',    '',               1, 'F', '0', '0', 'oss:file:remove','#',      'admin', '2020-03-01 00:00:00', 'cloud', '2020-03-01 00:00:00', '');
insert into sys_menu values('2006', '文件管理导出', '2001', '5', '#',    '',               1, 'F', '0', '0', 'oss:file:export','#',      'admin', '2020-03-01 00:00:00', 'cloud', '2020-03-01 00:00:00', '');
insert into sys_menu values('2007', '文件管理上传', '2001', '6', '#',    '',               1, 'F', '0', '0', 'oss:file:upload','#',      'admin', '2020-03-01 00:00:00', 'cloud', '2020-03-01 00:00:00', '');
insert into sys_menu values('2008', '文件管理下载', '2001', '7', '#',    '',               1, 'F', '0', '0', 'oss:file:download','#',    'admin', '2020-03-01 00:00:00', 'cloud', '2020-03-01 00:00:00', '');

-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',
  primary key(user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT= '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('2', '2');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT= '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('2', '1');
insert into sys_role_menu values ('2', '2');
insert into sys_role_menu values ('2', '3');
insert into sys_role_menu values ('2', '100');
insert into sys_role_menu values ('2', '101');
insert into sys_role_menu values ('2', '102');
insert into sys_role_menu values ('2', '103');
insert into sys_role_menu values ('2', '104');
insert into sys_role_menu values ('2', '105');
insert into sys_role_menu values ('2', '106');
insert into sys_role_menu values ('2', '107');
insert into sys_role_menu values ('2', '108');
insert into sys_role_menu values ('2', '109');
insert into sys_role_menu values ('2', '110');
insert into sys_role_menu values ('2', '111');
insert into sys_role_menu values ('2', '112');
insert into sys_role_menu values ('2', '113');
insert into sys_role_menu values ('2', '114');
insert into sys_role_menu values ('2', '115');
insert into sys_role_menu values ('2', '500');
insert into sys_role_menu values ('2', '501');
insert into sys_role_menu values ('2', '1000');
insert into sys_role_menu values ('2', '1001');
insert into sys_role_menu values ('2', '1002');
insert into sys_role_menu values ('2', '1003');
insert into sys_role_menu values ('2', '1004');
insert into sys_role_menu values ('2', '1005');
insert into sys_role_menu values ('2', '1006');
insert into sys_role_menu values ('2', '1007');
insert into sys_role_menu values ('2', '1008');
insert into sys_role_menu values ('2', '1009');
insert into sys_role_menu values ('2', '1010');
insert into sys_role_menu values ('2', '1011');
insert into sys_role_menu values ('2', '1012');
insert into sys_role_menu values ('2', '1013');
insert into sys_role_menu values ('2', '1014');
insert into sys_role_menu values ('2', '1015');
insert into sys_role_menu values ('2', '1016');
insert into sys_role_menu values ('2', '1017');
insert into sys_role_menu values ('2', '1018');
insert into sys_role_menu values ('2', '1019');
insert into sys_role_menu values ('2', '1020');
insert into sys_role_menu values ('2', '1021');
insert into sys_role_menu values ('2', '1022');
insert into sys_role_menu values ('2', '1023');
insert into sys_role_menu values ('2', '1024');
insert into sys_role_menu values ('2', '1025');
insert into sys_role_menu values ('2', '1026');
insert into sys_role_menu values ('2', '1027');
insert into sys_role_menu values ('2', '1028');
insert into sys_role_menu values ('2', '1029');
insert into sys_role_menu values ('2', '1030');
insert into sys_role_menu values ('2', '1031');
insert into sys_role_menu values ('2', '1032');
insert into sys_role_menu values ('2', '1033');
insert into sys_role_menu values ('2', '1034');
insert into sys_role_menu values ('2', '1035');
insert into sys_role_menu values ('2', '1036');
insert into sys_role_menu values ('2', '1037');
insert into sys_role_menu values ('2', '1038');
insert into sys_role_menu values ('2', '1039');
insert into sys_role_menu values ('2', '1040');
insert into sys_role_menu values ('2', '1041');
insert into sys_role_menu values ('2', '1042');
insert into sys_role_menu values ('2', '1043');
insert into sys_role_menu values ('2', '1044');
insert into sys_role_menu values ('2', '1045');
insert into sys_role_menu values ('2', '1046');
insert into sys_role_menu values ('2', '1047');
insert into sys_role_menu values ('2', '1048');
insert into sys_role_menu values ('2', '1049');
insert into sys_role_menu values ('2', '1050');
insert into sys_role_menu values ('2', '1051');
insert into sys_role_menu values ('2', '1052');
insert into sys_role_menu values ('2', '1053');
insert into sys_role_menu values ('2', '1054');
insert into sys_role_menu values ('2', '1055');
insert into sys_role_menu values ('2', '1056');
insert into sys_role_menu values ('2', '1057');
insert into sys_role_menu values ('2', '1058');
insert into sys_role_menu values ('2', '1059');
insert into sys_role_menu values ('2', '1060');
insert into sys_role_menu values ('2', '1061');
insert into sys_role_menu values ('2', '1062');

-- 文件系统
insert into sys_role_menu values ('2', '2000');
insert into sys_role_menu values ('2', '2001');
insert into sys_role_menu values ('2', '2002');
insert into sys_role_menu values ('2', '2003');
insert into sys_role_menu values ('2', '2004');
insert into sys_role_menu values ('2', '2005');
insert into sys_role_menu values ('2', '2006');
insert into sys_role_menu values ('2', '2007');
insert into sys_role_menu values ('2', '2008');

-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept (
  role_id   bigint(20) not null comment '角色ID',
  dept_id   bigint(20) not null comment '部门ID',
  primary key(role_id, dept_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT= '角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
insert into sys_role_dept values ('2', '100');
insert into sys_role_dept values ('2', '101');
insert into sys_role_dept values ('2', '105');


-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
  user_id   bigint(20) not null comment '用户ID',
  post_id   bigint(20) not null comment '岗位ID',
  primary key (user_id, post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT= '用户与岗位关联表';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into sys_user_post values ('1', '1');
insert into sys_user_post values ('2', '2');


-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id           bigint(20)      not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  business_type     int(2)          default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
  method            varchar(100)    default ''                 comment '方法名称',
  request_method    varchar(10)     default ''                 comment '请求方式',
  operator_type     int(1)          default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  oper_name         varchar(50)     default ''                 comment '操作人员',
  dept_name         varchar(50)     default ''                 comment '部门名称',
  oper_url          varchar(255)    default ''                 comment '请求URL',
  oper_ip           varchar(50)     default ''                 comment '主机地址',
  oper_location     varchar(255)    default ''                 comment '操作地点',
  oper_param        varchar(2000)   default ''                 comment '请求参数',
  json_result       varchar(2000)   default ''                 comment '返回参数',
  status            int(1)          default 0                  comment '操作状态（0正常 1异常）',
  error_msg         varchar(2000)   default ''                 comment '错误消息',
  oper_time         datetime                                   comment '操作时间',
  primary key (oper_id)
) ENGINE=InnoDB auto_increment=100 DEFAULT CHARSET=utf8mb4 COMMENT= '操作日志记录';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
  dict_id          bigint(20)      not null auto_increment    comment '字典主键',
  dict_name        varchar(100)    default ''                 comment '字典名称',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_id),
  unique (dict_type)
) ENGINE=InnoDB auto_increment=100 DEFAULT CHARSET=utf8mb4 COMMENT= '字典类型表';

insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统开关列表');
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '任务状态列表');
insert into sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '任务分组列表');
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统是否列表');
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '通知类型列表');
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '通知状态列表');
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '操作类型列表');
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '登录状态列表');
insert into sys_dict_type values(11, '授权类型', 'sys_grant_type',      '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '授权类型列表');

-- ----------------------------
-- 12、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
  dict_code        bigint(20)      not null auto_increment    comment '字典编码',
  dict_sort        int(4)          default 0                  comment '字典排序',
  dict_label       varchar(100)    default ''                 comment '字典标签',
  dict_value       varchar(100)    default ''                 comment '字典键值',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  css_class        varchar(100)    default null               comment '样式属性（其他样式扩展）',
  list_class       varchar(100)    default null               comment '表格回显样式',
  is_default       char(1)         default 'N'                comment '是否默认（Y是 N否）',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_code)
) ENGINE=InnoDB auto_increment=100 DEFAULT CHARSET=utf8mb4 COMMENT= '字典数据表';

insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '性别男');
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '性别女');
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '性别未知');
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '显示菜单');
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '隐藏菜单');
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '正常状态');
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '停用状态');
insert into sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '正常状态');
insert into sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',      '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '停用状态');
insert into sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '默认分组');
insert into sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统分组');
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统默认是');
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '系统默认否');
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '通知');
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '公告');
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '正常状态');
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '关闭状态');
insert into sys_dict_data values(18, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '新增操作');
insert into sys_dict_data values(19, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '修改操作');
insert into sys_dict_data values(20, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '删除操作');
insert into sys_dict_data values(21, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '授权操作');
insert into sys_dict_data values(22, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '导出操作');
insert into sys_dict_data values(23, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '导入操作');
insert into sys_dict_data values(24, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '强退操作');
insert into sys_dict_data values(25, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '生成操作');
insert into sys_dict_data values(26, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '清空操作');
insert into sys_dict_data values(27, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '正常状态');
insert into sys_dict_data values(28, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '停用状态');
insert into sys_dict_data values(29, 1,  '授权码模式',  'authorization_code',  'sys_grant_type',   '',   '',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '授权码模式');
insert into sys_dict_data values(30, 2,  '密码模式',    'password',            'sys_grant_type',   '',   '',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '密码模式');
insert into sys_dict_data values(31, 3,  '客户端模式',  'client_credentials',  'sys_grant_type',   '',   '',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '客户端模式');
insert into sys_dict_data values(32, 4,  '简化模式',    'implicit',            'sys_grant_type',   '',   '',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '简化模式');
insert into sys_dict_data values(33, 5,  '刷新模式',    'refresh_token',       'sys_grant_type',   '',   '',  'N', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '刷新模式');


-- ----------------------------
-- 13、参数配置表
-- ----------------------------
drop table if exists sys_config;
create table sys_config (
  config_id         int(5)          not null auto_increment    comment '参数主键',
  config_name       varchar(100)    default ''                 comment '参数名称',
  config_key        varchar(100)    default ''                 comment '参数键名',
  config_value      varchar(500)    default ''                 comment '参数键值',
  config_type       char(1)         default 'N'                comment '系统内置（Y是 N否）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (config_id)
) ENGINE=InnoDB auto_increment=100 DEFAULT CHARSET=utf8mb4 COMMENT= '参数配置表';

insert into sys_config values(1, '主框架页-默认皮肤样式名称', 'sys.index.skinName',     'skin-blue',     'Y', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow' );
insert into sys_config values(2, '用户管理-账号初始密码',     'sys.user.initPassword',  '123456',        'Y', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '初始化密码 123456' );
insert into sys_config values(3, '主框架页-侧边栏主题',       'sys.index.sideTheme',    'theme-dark',    'Y', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '深色主题theme-dark，浅色主题theme-light' );


-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id        bigint(20)     not null auto_increment   comment '访问ID',
  user_name      varchar(50)    default ''                comment '用户账号',
  ipaddr         varchar(50)    default ''                comment '登录IP地址',
  status         char(1)        default '0'               comment '登录状态（0成功 1失败）',
  msg            varchar(255)   default ''                comment '提示信息',
  access_time    datetime                                 comment '访问时间',
  primary key (info_id)
) ENGINE=InnoDB auto_increment=100 DEFAULT CHARSET=utf8mb4 COMMENT= '系统访问记录';


-- ----------------------------
-- 15、定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job (
  job_id              bigint(20)    not null auto_increment    comment '任务ID',
  job_name            varchar(64)   default ''                 comment '任务名称',
  job_group           varchar(64)   default 'DEFAULT'          comment '任务组名',
  invoke_target       varchar(500)  not null                   comment '调用目标字符串',
  cron_expression     varchar(255)  default ''                 comment 'cron执行表达式',
  misfire_policy      varchar(20)   default '3'                comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  concurrent          char(1)       default '1'                comment '是否并发执行（0允许 1禁止）',
  status              char(1)       default '0'                comment '状态（0正常 1暂停）',
  create_by           varchar(64)   default ''                 comment '创建者',
  create_time         datetime                                 comment '创建时间',
  update_by           varchar(64)   default ''                 comment '更新者',
  update_time         datetime                                 comment '更新时间',
  remark              varchar(500)  default ''                 comment '备注信息',
  primary key (job_id, job_name, job_group)
) ENGINE=InnoDB auto_increment=100 DEFAULT CHARSET=utf8mb4 COMMENT= '定时任务调度表';

insert into sys_job values(1, '系统默认（无参）', 'DEFAULT', 'cloudTask.ryNoParams',        '0/10 * * * * ?', '3', '1', '1', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_job values(2, '系统默认（有参）', 'DEFAULT', 'cloudTask.ryParams(\'cloud\')',  '0/15 * * * * ?', '3', '1', '1', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');
insert into sys_job values(3, '系统默认（多参）', 'DEFAULT', 'cloudTask.ryMultipleParams(\'cloud\', true, 2000L, 316.50D, 100)',  '0/20 * * * * ?', '3', '1', '1', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '');


-- ----------------------------
-- 16、定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log (
  job_log_id          bigint(20)     not null auto_increment    comment '任务日志ID',
  job_name            varchar(64)    not null                   comment '任务名称',
  job_group           varchar(64)    not null                   comment '任务组名',
  invoke_target       varchar(500)   not null                   comment '调用目标字符串',
  job_message         varchar(500)                              comment '日志信息',
  status              char(1)        default '0'                comment '执行状态（0正常 1失败）',
  exception_info      varchar(2000)  default ''                 comment '异常信息',
  create_time         datetime                                  comment '创建时间',
  primary key (job_log_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT= '定时任务调度日志表';


-- ----------------------------
-- 17、通知公告表
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice (
  notice_id         int(4)          not null auto_increment    comment '公告ID',
  notice_title      varchar(50)     not null                   comment '公告标题',
  notice_type       char(1)         not null                   comment '公告类型（1通知 2公告）',
  notice_content    varchar(2000)   default null               comment '公告内容',
  status            char(1)         default '0'                comment '公告状态（0正常 1关闭）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(255)    default null               comment '备注',
  primary key (notice_id)
) ENGINE=InnoDB auto_increment=10 DEFAULT CHARSET=utf8mb4 COMMENT= '通知公告表';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice values('1', '温馨提醒：2020-07-01 青云新版本发布啦', '2', '新版本内容', '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '管理员');
insert into sys_notice values('2', '维护通知：2020-07-01 青云系统凌晨维护', '1', '维护内容',   '0', 'admin', '2020-03-16 11-33-00', 'cloud', '2020-03-16 11-33-00', '管理员');


-- ----------------------------
-- 18、代码生成业务表
-- ----------------------------
drop table if exists gen_table;
create table gen_table (
  table_id          bigint(20)      not null auto_increment    comment '编号',
  table_name        varchar(200)    default ''                 comment '表名称',
  table_comment     varchar(500)    default ''                 comment '表描述',
  class_name        varchar(100)    default ''                 comment '实体类名称',
  tpl_category      varchar(200)    default 'crud'             comment '使用的模板（crud单表操作 tree树表操作）',
  package_name      varchar(100)                               comment '生成包路径',
  module_name       varchar(30)                                comment '生成模块名',
  business_name     varchar(30)                                comment '生成业务名',
  function_name     varchar(50)                                comment '生成功能名',
  function_author   varchar(50)                                comment '生成功能作者',
  gen_type          char(1)         default '0'                comment '生成代码方式（0zip压缩包 1自定义路径）',
  gen_path          varchar(200)    default '/'                comment '生成路径（不填默认项目路径）',
  options           varchar(1000)                              comment '其它生成选项',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (table_id)
) ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4 COMMENT= '代码生成业务表';


-- ----------------------------
-- 19、代码生成业务表字段
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column (
  column_id         bigint(20)      not null auto_increment    comment '编号',
  table_id          varchar(64)                                comment '归属表编号',
  column_name       varchar(200)                               comment '列名称',
  column_comment    varchar(500)                               comment '列描述',
  column_type       varchar(100)                               comment '列类型',
  java_type         varchar(500)                               comment 'JAVA类型',
  java_field        varchar(200)                               comment 'JAVA字段名',
  is_pk             char(1)                                    comment '是否主键（1是）',
  is_increment      char(1)                                    comment '是否自增（1是）',
  is_required       char(1)                                    comment '是否必填（1是）',
  is_insert         char(1)                                    comment '是否为插入字段（1是）',
  is_edit           char(1)                                    comment '是否编辑字段（1是）',
  is_list           char(1)                                    comment '是否列表字段（1是）',
  is_query          char(1)                                    comment '是否查询字段（1是）',
  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
  html_type         varchar(200)                               comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  dict_type         varchar(200)    default ''                 comment '字典类型',
  sort              int                                        comment '排序',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (column_id)
) ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4 COMMENT= '代码生成业务表字段';


-- ----------------------------
-- 20、终端配置表
-- ----------------------------
drop table if exists sys_oauth_client_details;
create table sys_oauth_client_details (
  client_id                  varchar(255)  not null          comment '终端编号',
  resource_ids               varchar(255)  default null      comment '资源ID标识',
  client_secret              varchar(255)  not null          comment '终端安全码',
  scope                      varchar(255)  not null          comment '终端授权范围',
  authorized_grant_types     varchar(255)  not null          comment '终端授权类型',
  web_server_redirect_uri    varchar(255)  default null      comment '服务器回调地址',
  authorities                varchar(255)  default null      comment '访问资源所需权限',
  access_token_validity      int(11)       default null      comment '设定终端的access_token的有效时间值（秒）',
  refresh_token_validity     int(11)       default null      comment '设定终端的refresh_token的有效时间值（秒）',
  additional_information     varchar(4096) default null      comment '附加信息',
  autoapprove                tinyint(4)    default null      comment '是否登录时跳过授权',
  origin_secret              varchar(255)  not null          comment '终端明文安全码',
  primary key (client_id)
) ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4 COMMENT= '终端配置表';


-- ----------------------------
-- 初始化-终端配置表数据
-- ----------------------------
insert into sys_oauth_client_details values ('web',  '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,refresh_token',                    '', NULL, 3600, 7200, NULL, NULL,'123456');
insert into sys_oauth_client_details values ('app-cloud',  '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,client_credentials,refresh_token', '', NULL, 3600, 7200, NULL, NULL,'123456');


-- ----------------------------
-- 21、文件/附件 表.
-- ----------------------------
drop table if exists oss_file_manager;
create table oss_file_manager (
  oss_id           bigint(20)      not null auto_increment    comment '文件id',
  file_name         varchar(50)     default ''                 comment '文件名称',
  file_type         varchar(30)     default ''                 comment '文件类型',
  file_size         bigint(20)      default 0                  comment '文件大小',
  file_path         varchar(100)    default ''                 comment '文件的路径',
  unique_key        varchar(50)     default ''                 comment '文件唯一标识',
  module_id         varchar(30)     default ''                 comment '文件所属的业务id',
  module_code       varchar(30)     default ''                 comment '文件所属的业务code',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  info_desc         varchar(500)    default ''                 comment '描述',
  primary key (oss_id)
) ENGINE=InnoDB auto_increment=0 DEFAULT CHARSET=utf8mb4 COMMENT= '文件/附件管理表';


-- ----------------------------
-- 22、动态路由
-- ----------------------------
drop table if exists gateway_dynamic_route;
create table gateway_dynamic_route (
  id           bigint(20)      not null auto_increment    comment '路由id',
  route_id          varchar(20)      default ''                 comment '路由的id',
  route_name        varchar(64)      default ''                 comment '路由的名称',
  route_uri         varchar(128)     default ''                 comment '路由规则转发的uri,可能会有http开头的,也可能是lb开头的',
  predicate_json    varchar(256)     default ''                 comment '路由断言集合配置json串',
  filter_json       varchar(256)     default ''                 comment '路由过滤器集合配置json串',
  route_order       int(11)          default 0                  comment '路由的执行顺序',
  info_desc         varchar(512)     default ''                 comment 'route的相关描述',
  status            int(1)           default 0                  comment '网关状态（0正常 1停用）',
  disabled          int(1)           default 0                  comment '删除标志（0正常 1删除）',
  create_by         varchar(64)      default ''                 comment '创建者',
  create_time 	    datetime                                    comment '创建时间',
  update_by         varchar(64)     default ''                  comment '更新者',
  update_time       datetime                                    comment '更新时间',
  primary key (id)
) ENGINE=InnoDB auto_increment=0 DEFAULT CHARSET=utf8mb4 COMMENT= 'GateWay动态路由配置表';

