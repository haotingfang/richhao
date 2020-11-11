-- ----------------------------
-- Table structure for 用户信息表
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openId',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '用户类型\r\n0：管理员  1：店长 ；2：教练 ；3：普通会员 4：VIP会员',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（1:男；2：女）',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言',
  `country` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user
values(1,  103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2',
'0', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00');
insert into sys_user values(2,  105, 'ry',    '若依', '00', 'ry@qq.com',  '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2',
'0', '0','admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00');


-- ----------------------------
-- Table structure for 角色表
-- ----------------------------
DROP TABLE IF EXISTS `role`;
create table role (
  id                   bigint(20)      not null auto_increment    comment '角色ID',
  role_name            varchar(30)     not null                   comment '角色名称',
  role_code            varchar(30)    not null                   comment '角色编号',
  role_sort            int(4)          not null                   comment '显示顺序',
  status               char(1)         not null                   comment '角色状态（0正常 1停用）',
  del_flag             char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by            varchar(64)     default ''                 comment '创建者',
  create_time          datetime                                   comment '创建时间',
  update_by            varchar(64)     default ''                 comment '更新者',
  update_time          datetime                                   comment '更新时间',
  remark               varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=100 comment = '角色信息表';

-- 默认数据


-- ----------------------------
-- 菜单权限
-- ----------------------------
drop table if exists menu;
create table menu (
  id                bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  path              varchar(200)    default ''                 comment '路由地址',
  component         varchar(255)    default null               comment '组件路径',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  status            char(1)         default 0                  comment '菜单状态（0正常 1停用）',
  perms             varchar(100)    default null               comment '权限标识',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';

-- 权限表默认数据

-- ----------------------------
-- 用户和角色关联表  用户-角色
-- ----------------------------
drop table if exists relation_user_role;
create table relation_user_role (
  id        bigint(20) not null auto_increment    comment 'id',
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',
  primary key(id)
) engine=innodb comment = '用户和角色关联表';


-- ----------------------------
-- 角色和菜单关联表  角色N-N菜单
-- ----------------------------
drop table if exists relation_role_menu;
create table relation_role_menu (
  id        bigint(20) not null auto_increment    comment 'id',
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(id)
) engine=innodb comment = '角色和菜单关联表';


-- ----------------------------
-- 商品表
-- ----------------------------
drop table if exists commodity;
create table commodity (
  id                bigint(20)      not null auto_increment    comment '商品id',
  title             varchar(50)     default ''                 comment '商品标题',
  description       varchar(255)    default ''                 comment '商品描述',
  picture_id        bigint(20)      default 0                  comment '图片文件id（关联文件id）',
  picture_url       varchar(255)    default ''                 comment '图片文件url（目前没有存储图片服务器 保留字段）',
  price             varchar(10)     default ''                 comment '价格',
  course_count      bigint(20)      default 0                  comment '课时',
  effective_time    bigint(20)      default 0                  comment '有效时长（天数）',
  status            int(1)          default 0                  comment '商品状态（0上架 1下架）',
  del_flag          int(1)          default 0                  comment '删除状态（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=100 comment = '商品表';



-- ----------------------------
-- 订单表
-- ----------------------------
drop table if exists sys_order;
create table sys_order (
	id                bigint(20)      not null auto_increment    comment '订单id',
  user_id           bigint(20)      not null                 comment '用户id',
  commodity_id      bigint(20)      not null                 comment '商品id',
  price             varchar(10)     default ''                 comment '价格',
  course_count      bigint(20)      default 0                  comment '课时',
  effective_time    bigint(20)      default 0                  comment '有效时长（天数）',
  open_time         datetime                                   comment '开卡时间',
  expired_time      datetime                                   comment '过期时间',
  pay_type          char(1)         default ''                 comment '支付方式(0：微信 1：支付宝 2：现金)',
  status            int(1)          default 0                  comment '订单状态（0待付款 1待自提 2待评价）',
  del_flag          int(1)          default 0                  comment '删除状态（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=100 comment = '订单表';


-- ----------------------------
-- 课程类别表
-- ----------------------------
drop table if exists course_type;
create table course_type (
  id                bigint(20)      not null auto_increment    comment '课程类型id',
  course_name       varchar(50)     default ''                 comment '课程名称',
  course_remark     varchar(255)    default ''                 comment '课程备注',
  hard_grade        char(1)         default 0                  comment '1-5 分别代表几颗星（星越多 难度系数越高）',
  status            int(1)          default 0                  comment '状态（0正常 1停用）',
  del_flag          int(1)          default 0                  comment '删除状态（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=100 comment = '课程类型表';

-- ----------------------------
-- 课程基础表 店长设置课程基础表，通过课程基础表一键生成（或定时任务） 课程表数据
-- ----------------------------
drop table if exists course_basic;
create table course_basic (
  id                bigint(20)      not null auto_increment    comment '课程id',
  course_type_id    bigint(20)      default ''                 comment '课程类型id(关联课程类型表id)',
  teacher_id        bigint(20)      default ''                 comment '老师id(关联用户表id)',
  day_type          char(1)         default 0                  comment '1-7 分别代表礼拜几',
  start_time        datetime                                   comment '课程开始时间',
  end_time          datetime                                   comment '课程结束时间',
  status            int(1)          default 0                  comment '状态（0正常 1停用）',
  del_flag          int(1)          default 0                  comment '删除状态（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=100 comment = '课程基础表';


-- ----------------------------
-- 课程表
-- ----------------------------
drop table if exists course;
create table course (
  id                bigint(20)      not null auto_increment    comment '课程id',
  course_type_id    bigint(20)      default ''                 comment '课程类型id(关联课程类型表id)',
  teacher_id        bigint(20)      default ''                 comment '老师id(关联用户表id)',
  day_time          datetime                                   comment '日期',
  start_time        datetime                                   comment '课程开始时间',
  end_time          datetime                                   comment '课程结束时间',
  status            int(1)          default 0                  comment '状态（0正常 1停用）',
  del_flag          int(1)          default 0                  comment '删除状态（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=100 comment = '课程表';


-- ----------------------------
-- 约课表
-- ----------------------------
drop table if exists appointment_course;
create table appointment_course (
  id                bigint(20)      not null auto_increment    comment '课程id',
  course_type_id    bigint(20)      default ''                 comment '课程类型id(关联课程类型表id)',
  teacher_id        bigint(20)      default ''                 comment '老师id(关联用户表id)',
  day_time          datetime                                   comment '日期',
  start_time        datetime                                   comment '课程开始时间',
  end_time          datetime                                   comment '课程结束时间',
  status            int(1)          default 0                  comment '状态（0正常 1停用）',
  del_flag          int(1)          default 0                  comment '删除状态（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=100 comment = '约课';





-- ----------------------------
-- 操作日志记录
-- ----------------------------
drop table if exists oper_log;
create table oper_log (
  id           bigint(20)      not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  business_type     int(2)          default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
  method            varchar(100)    default ''                 comment '方法名称',
  request_method    varchar(10)     default ''                 comment '请求方式',
  operator_type     int(1)          default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  oper_name         varchar(50)     default ''                 comment '操作人员',
  dept_name         varchar(50)     default ''                 comment '部门名称',
  oper_url          varchar(255)    default ''                 comment '请求URL',
  oper_location     varchar(255)    default ''                 comment '操作地点',
  oper_param        varchar(2000)   default ''                 comment '请求参数',
  json_result       varchar(2000)   default ''                 comment '返回参数',
  status            int(1)          default 0                  comment '操作状态（0正常 1异常）',
  error_msg         varchar(2000)   default ''                 comment '错误消息',
  oper_time         datetime                                   comment '操作时间',
  primary key (id)
) engine=innodb auto_increment=100 comment = '操作日志记录';





