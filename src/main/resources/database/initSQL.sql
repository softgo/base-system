-- ----------------------------
-- sql for create database.
-- ----------------------------
DROP DATABASE if exists basesystem;
CREATE DATABASE test1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use basesystem;

-- ----------------------------
-- Table structure for SYSTEM_LOG
-- ----------------------------

DROP TABLE IF EXISTS SYSTEM_LOG;
CREATE TABLE SYSTEM_LOG (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  is_delete tinyint(1) DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) DEFAULT '' COMMENT '系统生成的唯一标识',
  create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_user int(6) DEFAULT 0 COMMENT '创建者',
  update_user int(6) DEFAULT 0 COMMENT '更新者',
  
  user_id int(11) DEFAULT NULL COMMENT '系统登陆用户id',
  user_name varchar(20) DEFAULT '' COMMENT '系统登陆用户名',
  from_ip varchar(40) DEFAULT '' COMMENT '登陆ip',
  status tinyint(1) DEFAULT 1 COMMENT '日志状态;1:可见,0:不可见',
  log_type tinyint(2) DEFAULT 1 COMMENT '日志类型;1:登陆,2:系统',
  module varchar(30) DEFAULT '' COMMENT '操作哪个模块',
  action varchar(500) DEFAULT '' COMMENT '操作详述',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='登陆日志记录表';

-- ----------------------------
-- Table structure for SYSTEM_SERVER_INFO 
-- ----------------------------

DROP TABLE IF EXISTS SYSTEM_SERVER_INFO;
CREATE TABLE SYSTEM_SERVER_INFO (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '信息id',
  is_delete tinyint(1) DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) DEFAULT '' COMMENT '系统生成的唯一标识',
  create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_user int(6) DEFAULT 0 COMMENT '创建者',
  update_user int(6) DEFAULT 0 COMMENT '更新者',
  
  cpu_usage varchar(255) DEFAULT '' COMMENT 'cpu内存',
  set_cpu_usage varchar(255) DEFAULT '' COMMENT '设置cpu内存',
  jvm_usage varchar(255) DEFAULT '' COMMENT 'jvm内存',
  set_jvm_usage varchar(255) DEFAULT '' COMMENT '设置jvm内存',
  ram_usage varchar(255) DEFAULT '' COMMENT '内存使用',
  set_ram_usage varchar(255) DEFAULT '' COMMENT '设置内存使用',
  manager_email varchar(255) DEFAULT '' COMMENT '管理的email',
  mark varchar(255) DEFAULT '' COMMENT '系统备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统机器配置';

-- ----------------------------
-- Table structure for SYSTEM_ROLE
-- ----------------------------

DROP TABLE IF EXISTS SYSTEM_ROLE;
CREATE TABLE SYSTEM_ROLE (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  is_delete tinyint(1) DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) DEFAULT '' COMMENT '系统生成的唯一标识',
  create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_user int(6) DEFAULT 0 COMMENT '创建者',
  update_user int(6) DEFAULT 0 COMMENT '更新者',
  
  parent_id int(11) DEFAULT '-1' COMMENT '角色父级id',
  role_name varchar(50) DEFAULT '' COMMENT '角色名字',
  role_key varchar(50) DEFAULT '' COMMENT '角色key',
  description varchar(200) DEFAULT '' COMMENT '角色介绍',
  status tinyint(2) DEFAULT 1 COMMENT '是否可用:1 可用,0 禁用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色管理表';

-- ----------------------------
-- Table structure for SYSTEM_SOURCE
-- ----------------------------

DROP TABLE IF EXISTS SYSTEM_SOURCE;
CREATE TABLE SYSTEM_SOURCE (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  is_delete tinyint(1) DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) DEFAULT '' COMMENT '系统生成的唯一标识',
  create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_user int(6) DEFAULT 0 COMMENT '创建者',
  update_user int(6) DEFAULT 0 COMMENT '更新者',
  
  source_name varchar(50) NOT NULL DEFAULT '' COMMENT '资源名称',
  parent_id smallint(11) NOT NULL COMMENT '资源父级Id',
  source_key varchar(50) DEFAULT '' COMMENT '资源key,是唯一的访问Key',
  source_type varchar(10) DEFAULT '' COMMENT '资源类型,0:菜单,1:按钮,2:链接',
  source_url varchar(200) NOT  NULL DEFAULT '' COMMENT '资源访问的url',
  source_level smallint(11) DEFAULT 0 COMMENT '资源等级,从1开始,1,2,3,4',
  description varchar(200) DEFAULT '' COMMENT '资源介绍',
  source_status tinyint(2) DEFAULT 1 COMMENT '资源状态;1:启用,0:弃用',
  is_iframe tinyint(2) DEFAULT 0 COMMENT '是否是iframe;1:是,0:否',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='访问资源管理表';

-- ----------------------------
-- Table structure for SYSTEM_SOURCE_ROLE
-- ----------------------------

DROP TABLE IF EXISTS SYSTEM_SOURCE_ROLE;
CREATE TABLE SYSTEM_SOURCE_ROLE (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '资源角色的id',
  is_delete tinyint(1) DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) DEFAULT '' COMMENT '系统生成的唯一标识',
  create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_user int(6) DEFAULT 0 COMMENT '创建者',
  update_user int(6) DEFAULT 0 COMMENT '更新者',
  
  source_id int(11) NOT NULL DEFAULT 0 COMMENT '资源id',
  role_id int(11) NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='访问资源和角色管理关联表';


-- ----------------------------
-- Table structure for SYSTEM_USER 
-- ----------------------------

DROP TABLE IF EXISTS SYSTEM_USER;
CREATE TABLE SYSTEM_USER (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '系统用户的id',
  is_delete tinyint(1) DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) DEFAULT '' COMMENT '系统生成的唯一标识',
  create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_user int(6) DEFAULT 0 COMMENT '创建者',
  update_user int(6) DEFAULT 0 COMMENT '更新者',
  
  parent_id int(11) DEFAULT -1 COMMENT '系统用户父级id',
  user_name varchar(20) DEFAULT '' COMMENT '系统用户的名字',
  user_pass varchar(100) DEFAULT '' COMMENT '系统登陆密码',
  user_sex tinyint(2) DEFAULT 1 COMMENT '用户的性别: 1:男,0:女',
  user_address varchar(100) DEFAULT '' COMMENT '住址',
  user_phone varchar(50) DEFAULT '' COMMENT '手机号码',
  user_mail varchar(50) DEFAULT '' COMMENT '邮箱地址',
  user_qq varchar(30) DEFAULT '' COMMENT '联系qq',
  status tinyint(2) DEFAULT 1 COMMENT '用户状态;1:启用,0:弃用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统用户管理表';

-- ----------------------------
-- Table structure for SYSTEM_USER_ROLE
-- ----------------------------

DROP TABLE IF EXISTS SYSTEM_USER_ROLE;
CREATE TABLE SYSTEM_USER_ROLE (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色的id',
  is_delete tinyint(1) DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) DEFAULT '' COMMENT '系统生成的唯一标识',
  create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_user int(6) DEFAULT 0 COMMENT '创建者',
  update_user int(6) DEFAULT 0 COMMENT '更新者',
  
  user_id int(11) NOT NULL DEFAULT 0 COMMENT '系统用户的id',
  role_id int(11) NOT NULL DEFAULT 0 COMMENT '系统角色的id',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户和角色管理关联表';


-- ----------------------------
-- init database data 
-- ----------------------------

-- user init data
INSERT INTO SYSTEM_USER (parent_id,user_name,user_pass,user_sex,user_address,user_phone,user_mail,user_qq,status) VALUES (-1,'admin','hoIPI+1VeV3GFzJijYH9cQQzJwLy4WG+',1,'贝吉塔星球','010-5211110','testrocky@163.com','1577620678',1); -- admin admin 

-- role  init data
INSERT INTO SYSTEM_ROLE (parent_id,role_name,role_key,description,status) VALUES (-1,'admin','SYS_USER_ROLE','管理员',1);

-- source init data
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('用户管理',1010,'system_user','0','system_user',1,'用户管理',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('用户列表',1,'system_user_find','1','/background/systemUser/find.html',2,'用户列表',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('新增用户',2,'system_user_add','2','/background/systemUser/add.html',3,'新增用户',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('编辑用户',2,'system_user_edit','2','/background/systemUser/findById.html',4,'编辑用户',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('删除用户',2,'system_user_delete','2','/background/systemUser/deleteById.html',5,'删除用户',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('分配角色',2,'sytemuser_arrange_role','2','sytemuser_arrange_role',6,'分配角色',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('详细信息',2,'system_user_info','2','system_user_info',7,'显示详细信息',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('角色管理',1010,'system_role','0','system_role',8,'角色管理',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('角色列表',8,'system_role_find','1','/background/systemRole/find.html',9,'角色列表',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('新增角色',9,'system_role_add','2','/background/systemRole/add.html',10,'新增角色',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('编辑角色',9,'system_role_edit','2','/background/systemRole/findById.html',11,'编辑角色',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('删除角色',9,'system_role_delete','2','/background/systemRole/deleteById.html',12,'删除角色',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('分配权限',9,'system_role_permissions','2','system_role_permissions',13,'分配权限',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('详细信息',9,'system_role_info','2','system_role_info',14,'显示详细信息',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('资源管理',1010,'system_source','0','system_source',15,'资源管理',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('资源列表',15,'system_source_find','1','/background/systemSource/find.html',16,'资源列表',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('添加资源',15,'system_source_add','1','/background/systemSource/addUI.html',17,'添加资源',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('编辑资源',16,'system_source_edit','2','/background/systemSource/findById.html',18,'编辑权限资源',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('删除资源',16,'system_source_delete','2','/background/systemSource/deleteById.html',19,'删除权限资源',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('详细信息',16,'system_source_info','2','system_source_info',20,'显示详细信息',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('服务管理',1010,'system_server_info','0','system_server_info',21,'服务器配置管理',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('状态列表',21,'system_server_info_list','1','/background/systemServerInfo/find.html',22,'状态列表',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('预警设置',21,'system_server_info_warn','1','/background/systemServerInfo/forecast.html',23,'预警设置',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('服务器状态',21,'system_server_info_status','1','/background/systemServerInfo/show.html',24,'服务器状态',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('日志管理',1010,'system_log_manager','0','system_log_manager',25,'日志管理',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('操作日志',25,'system_sysLog','1','/background/systemLog/systemLog.html',26,'操作日志',1,0);
INSERT INTO SYSTEM_SOURCE (source_name,parent_id,source_key,source_type,source_url,source_level,description,source_status,is_iframe) VALUES ('登录日志',25,'system_logLog','1','/background/systemLog/loginLog.html',27,'登录日志',1,0);
-- source_role init data
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (1,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (2,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (3,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (4,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (5,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (6,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (7,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (8,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (9,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (10,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (11,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (12,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (13,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (14,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (15,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (16,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (17,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (18,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (19,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (20,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (21,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (22,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (23,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (24,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (25,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (26,1);
INSERT INTO SYSTEM_SOURCE_ROLE (source_id,role_id) VALUES (27,1);

-- user_role init data
INSERT INTO SYSTEM_USER_ROLE (user_id,role_id) VALUES (1,1);

-- server_info init data 
INSERT INTO SYSTEM_SERVER_INFO (cpu_usage,set_cpu_usage,jvm_usage,set_jvm_usage,ram_usage,set_ram_usage,manager_email,mark) VALUES ('9.3','20','64.0','80','75.0','80','1577620678@qq.com','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>');
INSERT INTO SYSTEM_SERVER_INFO (cpu_usage,set_cpu_usage,jvm_usage,set_jvm_usage,ram_usage,set_ram_usage,manager_email,mark) VALUES ('0.8','20','60.0','80','75.0','80','1577620678@qq.com','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>');
INSERT INTO SYSTEM_SERVER_INFO (cpu_usage,set_cpu_usage,jvm_usage,set_jvm_usage,ram_usage,set_ram_usage,manager_email,mark) VALUES ('1.5','20','59.0','80','75.0','80','1577620678@qq.com','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>');
INSERT INTO SYSTEM_SERVER_INFO (cpu_usage,set_cpu_usage,jvm_usage,set_jvm_usage,ram_usage,set_ram_usage,manager_email,mark) VALUES ('0.7','20','57.0','80','75.0','80','1577620678@qq.com','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>');
INSERT INTO SYSTEM_SERVER_INFO (cpu_usage,set_cpu_usage,jvm_usage,set_jvm_usage,ram_usage,set_ram_usage,manager_email,mark) VALUES ('2.3','20','57.0','80','75.0','80','1577620678@qq.com','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>');
INSERT INTO SYSTEM_SERVER_INFO (cpu_usage,set_cpu_usage,jvm_usage,set_jvm_usage,ram_usage,set_ram_usage,manager_email,mark) VALUES ('17.9','20','57.0','80','77.0','80','1577620678@qq.com','<font color=\'red\'>内存当前：77.0%,超出预设值  60%</font>');



