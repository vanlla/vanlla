/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.228.130
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 192.168.228.130:3306
 Source Schema         : vanlla

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 15/07/2020 15:24:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_captcha
-- ----------------------------
DROP TABLE IF EXISTS `tb_captcha`;
CREATE TABLE `tb_captcha`  (
  `uuid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expire_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_captcha
-- ----------------------------
INSERT INTO `tb_captcha` VALUES ('123123', '2ayc', '2019-09-30 10:14:35');
INSERT INTO `tb_captcha` VALUES ('1231234', '63am', '2019-09-30 10:14:59');
INSERT INTO `tb_captcha` VALUES ('1231236', '3gwm', '2019-09-30 10:15:02');
INSERT INTO `tb_captcha` VALUES ('12312367', 'a6dn', '2019-09-30 10:15:07');
INSERT INTO `tb_captcha` VALUES ('x', '4fmf', '2020-01-19 18:24:21');
INSERT INTO `tb_captcha` VALUES ('xxx', '3m4m', '2019-12-31 17:46:17');

-- ----------------------------
-- Table structure for tb_external_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_external_user`;
CREATE TABLE `tb_external_user`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `internal_user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外键，用户表ID',
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外部用户ID',
  `user_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外部用户名称',
  `user_type` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外部用户类型',
  `organization` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外部用户组织信息',
  `ext1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_external_user
-- ----------------------------
INSERT INTO `tb_external_user` VALUES ('4d5e96942dfc0ee5f9a9abd0e12c1fbe', 'a9f57e16f904c8e8244e796ca32f2ba5', 'YuanXianXian@A', '道一澳电项目测试', '0', '15e3a3ea8ad84caea5ab9a2830361679', 'ww3ee4a8937d359ed5', NULL);

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `menu_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单编号',
  `parent_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父菜单ID',
  `type` int(1) NOT NULL COMMENT '类型',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题使用',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `is_leaf` tinyint(1) NULL DEFAULT 0 COMMENT '1为叶子节点，0不是叶子节点',
  `order_no` int(5) NULL DEFAULT NULL COMMENT '排序',
  `rw_access` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '读写权限 0为只读，1为读写',
  `is_sysmenu` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否系统菜单，0为系统菜单，1或空为业务菜单',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('289', '0', 0, '系统管理', NULL, NULL, 'system', 'system', 0, 1, NULL, NULL);
INSERT INTO `tb_menu` VALUES ('290', '289', 1, '角色管理', '/system/role/list', '', 'role', NULL, 0, 2, NULL, NULL);
INSERT INTO `tb_menu` VALUES ('293', '289', 1, '用户管理', '/system/user/list', NULL, 'user', NULL, 0, 1, NULL, NULL);
INSERT INTO `tb_menu` VALUES ('294', '289', 1, '菜单管理', '/system/menu/main', '', 'menu', NULL, 0, 3, NULL, NULL);
INSERT INTO `tb_menu` VALUES ('319', '293', 2, '用户列表', NULL, 'system:user:list', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('320', '293', 2, '查看用户', NULL, 'system:user:info', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('321', '293', 2, '新增用户', NULL, 'system:user:save', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('322', '293', 2, '更新用户', NULL, 'system:user:update', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('323', '293', 2, '删除用户', NULL, 'system:user:delete', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('324', '293', 2, '修改密码By管理员', NULL, 'system:user:updateByAdmin', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('325', '293', 2, '修改密码By当前用户', NULL, 'system:user:updatePasswordByUser', NULL, NULL, 1, NULL, NULL, NULL);
INSERT INTO `tb_menu` VALUES ('326', '290', 2, '角色列表', NULL, 'system:role:list', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('327', '290', 2, '查看角色', NULL, 'system:role:info', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('328', '290', 2, '新增角色', NULL, 'system:role:save', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('329', '290', 2, '更新角色', NULL, 'system:role:update', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('330', '290', 2, '删除角色', NULL, 'system:role:delete', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('331', '290', 2, '所有角色', NULL, 'system:role:listAll', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('332', '294', 2, '菜单列表', NULL, 'system:menu:list', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('333', '294', 2, '查看菜单', NULL, 'system:menu:info', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('334', '294', 2, '新增菜单', NULL, 'system:menu:save', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('335', '294', 2, '更新菜单', NULL, 'system:menu:update', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('336', '294', 2, '菜单删除', NULL, 'system:menu:delete', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('337', '294', 2, '登录用户菜单', NULL, 'system:menu:login', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('338', '289', 1, '定时任务', '/system/job/list', NULL, 'schedule', NULL, 0, 4, NULL, NULL);
INSERT INTO `tb_menu` VALUES ('339', '338', 2, '定时任务列表', NULL, 'quartz:job:list', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('340', '338', 2, '查看', NULL, 'quartz:job:info', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('341', '338', 2, '编辑', NULL, 'quartz:job:update', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('342', '338', 2, '新增', NULL, 'quartz:job:save', NULL, NULL, 1, NULL, '1', NULL);
INSERT INTO `tb_menu` VALUES ('343', '338', 2, '删除', NULL, 'quartz:job:delete', NULL, NULL, 1, NULL, '1', NULL);

-- ----------------------------
-- Table structure for tb_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `tb_quartz_job`;
CREATE TABLE `tb_quartz_job`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绑定指定IP执行',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表达式',
  `enable` int(2) NULL DEFAULT NULL COMMENT '是否启用，0：关闭，1：启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_quartz_job
-- ----------------------------
INSERT INTO `tb_quartz_job` VALUES (1, '第一个定时任务', '第一个定时任务', NULL, 'com.github.vanlla.job.job.FirstJob', 0, '0/3 * * * * ?', 0);

-- ----------------------------
-- Table structure for tb_quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_quartz_job_log`;
CREATE TABLE `tb_quartz_job_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_id` int(11) NULL DEFAULT NULL COMMENT '任务ID',
  `result` int(2) NULL DEFAULT NULL COMMENT '状态',
  `exception` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `complete_time` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_quartz_job_log
-- ----------------------------
INSERT INTO `tb_quartz_job_log` VALUES (49, 2, 1, NULL, '2020-05-06 06:25:18', '2020-05-06 06:25:18');
INSERT INTO `tb_quartz_job_log` VALUES (50, 2, 1, NULL, '2020-05-06 06:25:19', '2020-05-06 06:25:19');
INSERT INTO `tb_quartz_job_log` VALUES (51, 2, 1, NULL, '2020-05-06 06:25:20', '2020-05-06 06:25:20');
INSERT INTO `tb_quartz_job_log` VALUES (52, 2, 1, NULL, '2020-05-06 06:25:21', '2020-05-06 06:25:21');
INSERT INTO `tb_quartz_job_log` VALUES (53, 2, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-05-06 07:19:42', '2020-05-06 07:19:42');
INSERT INTO `tb_quartz_job_log` VALUES (54, 2, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-05-06 07:19:42', '2020-05-06 07:19:42');
INSERT INTO `tb_quartz_job_log` VALUES (55, 2, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-05-06 07:19:43', '2020-05-06 07:19:43');
INSERT INTO `tb_quartz_job_log` VALUES (56, 2, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-05-06 07:19:44', '2020-05-06 07:19:44');
INSERT INTO `tb_quartz_job_log` VALUES (57, 2, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-05-06 07:19:45', '2020-05-06 07:19:45');
INSERT INTO `tb_quartz_job_log` VALUES (82, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 06:59:00', '2020-07-15 06:59:00');
INSERT INTO `tb_quartz_job_log` VALUES (83, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 06:59:03', '2020-07-15 06:59:03');
INSERT INTO `tb_quartz_job_log` VALUES (84, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 06:59:06', '2020-07-15 06:59:06');
INSERT INTO `tb_quartz_job_log` VALUES (85, 1, 1, NULL, '2020-07-15 06:59:57', '2020-07-15 06:59:57');
INSERT INTO `tb_quartz_job_log` VALUES (86, 1, 1, NULL, '2020-07-15 07:00:00', '2020-07-15 07:00:00');
INSERT INTO `tb_quartz_job_log` VALUES (87, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 07:02:58', '2020-07-15 07:02:58');
INSERT INTO `tb_quartz_job_log` VALUES (88, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 07:03:00', '2020-07-15 07:03:00');
INSERT INTO `tb_quartz_job_log` VALUES (89, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 07:03:03', '2020-07-15 07:03:03');
INSERT INTO `tb_quartz_job_log` VALUES (90, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 07:03:06', '2020-07-15 07:03:06');
INSERT INTO `tb_quartz_job_log` VALUES (91, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 07:03:09', '2020-07-15 07:03:09');
INSERT INTO `tb_quartz_job_log` VALUES (92, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 07:03:12', '2020-07-15 07:03:12');
INSERT INTO `tb_quartz_job_log` VALUES (93, 1, 0, 'java.io.FileNotFoundException: firstFile not found\r\n	at com.github.vanlla.job.job.FirstJob.executeInternal(FirstJob.java:20)\r\n	at com.github.vanlla.job.job.AbstractJob.execute(AbstractJob.java:25)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-07-15 07:03:15', '2020-07-15 07:03:15');

-- ----------------------------
-- Table structure for tb_qy_wechat_robot
-- ----------------------------
DROP TABLE IF EXISTS `tb_qy_wechat_robot`;
CREATE TABLE `tb_qy_wechat_robot`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机器人名称',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机器人地址',
  `execute_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间',
  `enable` int(2) NULL DEFAULT NULL COMMENT '0：关闭，1：开启',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_qy_wechat_robot
-- ----------------------------
INSERT INTO `tb_qy_wechat_robot` VALUES (1, '考勤机器人', '{\r\n		\"msgtype\": \"text\",\r\n		\"text\": {\r\n			\"content\": \"和飞信考勤，融通公司2.0打卡提醒。\",\r\n			\"mentioned_list\":[\"@all\"]\r\n		}\r\n		}', 'https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=5a9543cc-312b-430b-a0de-14db6b90834d', '2019-12-08 20:52:10', 1);
INSERT INTO `tb_qy_wechat_robot` VALUES (2, 'asdasd3', 'asda3', 'dasdasd', '2019-12-08 11:36:29', 0);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '超级管理员', '超级管理员', 1);
INSERT INTO `tb_role` VALUES ('2', '用户管理员', '用户管理员', 1);

-- ----------------------------
-- Table structure for tb_role_menu_ref
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu_ref`;
CREATE TABLE `tb_role_menu_ref`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_menu_ref
-- ----------------------------
INSERT INTO `tb_role_menu_ref` VALUES ('02b4d8535cd54425e12f639f09652ea9', '2', '329');
INSERT INTO `tb_role_menu_ref` VALUES ('032519433cd66772628addd7b4eb9512', '1', '329');
INSERT INTO `tb_role_menu_ref` VALUES ('03c786e6957daf5fcca194a043388021', '1', '320');
INSERT INTO `tb_role_menu_ref` VALUES ('03d6a3f67e3cd0e26eef865034a0ed2c', '1', '331');
INSERT INTO `tb_role_menu_ref` VALUES ('0867715a08fe51f0b7417676b318dd89', '1', '340');
INSERT INTO `tb_role_menu_ref` VALUES ('0fd52fe0074ed18e63e87a3bab026066', '2', '327');
INSERT INTO `tb_role_menu_ref` VALUES ('15c8810255dd964e7045d85e9c6d3251', '1', '348');
INSERT INTO `tb_role_menu_ref` VALUES ('1842d7cb865577e7c9559e5f9e6e4d6f', '2', '336');
INSERT INTO `tb_role_menu_ref` VALUES ('19d3c1e443c23655900043ebb92bce83', '1', '322');
INSERT INTO `tb_role_menu_ref` VALUES ('1e55a159928c838815f352d19a791046', '1', '335');
INSERT INTO `tb_role_menu_ref` VALUES ('2023f96f79803cb4cb339e3358de2b7b', '1', '330');
INSERT INTO `tb_role_menu_ref` VALUES ('22e098cee0a01ba0997d9e792fc79f29', '1', '341');
INSERT INTO `tb_role_menu_ref` VALUES ('23e7a4aadb430780015e9698f3798f2c', '2', '339');
INSERT INTO `tb_role_menu_ref` VALUES ('256580accac3573d0d9163179f7f959b', '1', '325');
INSERT INTO `tb_role_menu_ref` VALUES ('2e257056b6be4c93299069f4b6fa0086', '2', '325');
INSERT INTO `tb_role_menu_ref` VALUES ('2ea76487aa2ba05e3a7cc054dfdd7c24', '1', '334');
INSERT INTO `tb_role_menu_ref` VALUES ('319911502d1d59250b5fc406f5daad71', '2', '326');
INSERT INTO `tb_role_menu_ref` VALUES ('380270136969d842e3c8ce47a40e9769', '1', '347');
INSERT INTO `tb_role_menu_ref` VALUES ('3ff3fc431c7cebc124837b094431ad33', '2', '332');
INSERT INTO `tb_role_menu_ref` VALUES ('41de3b80b608bd0731632ae7bce5cd65', '1', '324');
INSERT INTO `tb_role_menu_ref` VALUES ('4285283acebd1a8830a33636cd7c5c6a', '2', '323');
INSERT INTO `tb_role_menu_ref` VALUES ('45262aa2a65e8cba99ba00808733d248', '1', '321');
INSERT INTO `tb_role_menu_ref` VALUES ('50db6bf171da89726394f7cd4cb91965', '2', '293');
INSERT INTO `tb_role_menu_ref` VALUES ('52b62f24b5946b3e8cd0655dfe0a6b98', '1', '328');
INSERT INTO `tb_role_menu_ref` VALUES ('5313457f086ff513f71c0adc32b52fed', '1', '319');
INSERT INTO `tb_role_menu_ref` VALUES ('537d785ec866257d5cc4651d3fa0ccd1', '1', '346');
INSERT INTO `tb_role_menu_ref` VALUES ('57926f165e52a70aa524342324b3c67e', '2', '337');
INSERT INTO `tb_role_menu_ref` VALUES ('57ba2beded5ef2e7b78e507378fc8d3f', '1', '327');
INSERT INTO `tb_role_menu_ref` VALUES ('595b1936fc060cb6f7cfdecdd9548900', '2', '334');
INSERT INTO `tb_role_menu_ref` VALUES ('5bea35497e51180d09015812093b6f27', '2', '322');
INSERT INTO `tb_role_menu_ref` VALUES ('6053903111927e8992bd547d69602bae', '1', '290');
INSERT INTO `tb_role_menu_ref` VALUES ('62d326f3df890cd864c9097f358e9bb8', '2', '319');
INSERT INTO `tb_role_menu_ref` VALUES ('662e9cb773dc7ab5885614ea63437b95', '1', '337');
INSERT INTO `tb_role_menu_ref` VALUES ('6ded1deaea63665f485b6099d4492360', '2', '341');
INSERT INTO `tb_role_menu_ref` VALUES ('6f538fbb12d96e7a53058624681e683d', '1', '350');
INSERT INTO `tb_role_menu_ref` VALUES ('71a30807a27ebadf3d103db27c329691', '2', '321');
INSERT INTO `tb_role_menu_ref` VALUES ('7b97c5f8addc65ef350aa0cd858f5af5', '1', '333');
INSERT INTO `tb_role_menu_ref` VALUES ('7cb3d6cdac69779b4537417b89e4b882', '1', '294');
INSERT INTO `tb_role_menu_ref` VALUES ('7ed7cf971809fb63f3bb857cab0154de', '1', '338');
INSERT INTO `tb_role_menu_ref` VALUES ('86f2ffe1c3855ac9ab9fa4d9a584eaf2', '2', '328');
INSERT INTO `tb_role_menu_ref` VALUES ('8934daddbb496c022a916ba953d69cb0', '2', '333');
INSERT INTO `tb_role_menu_ref` VALUES ('9080ece887c746f44c5fbb67df1d8209', '2', '343');
INSERT INTO `tb_role_menu_ref` VALUES ('92617b73c65d4308a59c4ec7796fd5c2', '2', '320');
INSERT INTO `tb_role_menu_ref` VALUES ('962d75d3fad2b8ddf37ac998ae049b9b', '1', '339');
INSERT INTO `tb_role_menu_ref` VALUES ('acf1f211750b04727f30d27ece4130a7', '2', '342');
INSERT INTO `tb_role_menu_ref` VALUES ('ade3bb6dc5751650320ab5435de44fb6', '1', '293');
INSERT INTO `tb_role_menu_ref` VALUES ('b219fc85751f973678eb2dacaf6a4691', '1', '345');
INSERT INTO `tb_role_menu_ref` VALUES ('b2fd001ec6990f8b652a6e85c041b522', '2', '294');
INSERT INTO `tb_role_menu_ref` VALUES ('b4c14640a8594ac85b01f143c77f96ca', '2', '331');
INSERT INTO `tb_role_menu_ref` VALUES ('c3e38de23a2763f8599cda51b8aca910', '1', '336');
INSERT INTO `tb_role_menu_ref` VALUES ('c41711858e1b29451b6b4cedf1c07137', '2', '340');
INSERT INTO `tb_role_menu_ref` VALUES ('c6e90a335bbc3455eb32ffbaec553bdc', '2', '335');
INSERT INTO `tb_role_menu_ref` VALUES ('cb413d84d1e5a18c1af8d0ad8e8410d7', '2', '290');
INSERT INTO `tb_role_menu_ref` VALUES ('cc08e80a61745e52a7151a70bdf30dae', '1', '323');
INSERT INTO `tb_role_menu_ref` VALUES ('cedec5498e3e3eb5cae9630b0b59fdfe', '1', '343');
INSERT INTO `tb_role_menu_ref` VALUES ('d23f8d7f44bb12c7e454206580dbb6f4', '1', '332');
INSERT INTO `tb_role_menu_ref` VALUES ('dbabd28f5994288bd14cc518479d1040', '2', '289');
INSERT INTO `tb_role_menu_ref` VALUES ('de461bf364276f8afc822735cf6c5733', '1', '342');
INSERT INTO `tb_role_menu_ref` VALUES ('e4818dde1e87a699afc9a7159c7eeabf', '1', '289');
INSERT INTO `tb_role_menu_ref` VALUES ('ed624fee2bd523bb8b823b421a600bcf', '2', '330');
INSERT INTO `tb_role_menu_ref` VALUES ('ed703d5bd1b568e11736a4f32e10f7c5', '1', '349');
INSERT INTO `tb_role_menu_ref` VALUES ('f4f6030bbf6c3f2cc3d9a4bd30e19f6d', '2', '338');
INSERT INTO `tb_role_menu_ref` VALUES ('fd63d4d153794ec54e7e98073e5fe818', '2', '324');
INSERT INTO `tb_role_menu_ref` VALUES ('fdb9bc8759beec8c928f0cc4a0e761c5', '1', '326');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `user_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `id_card` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0 禁用，1正常，2离职）',
  `gmt_created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `gmt_author` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值加密',
  `user_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '账号类别（0 : 内部用户，1 : 外部用户）',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `tb_user_idx1`(`user_id`) USING BTREE,
  INDEX `tb_user_username`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('3', 'admin', '465c194afb65670f38322df087f0a9bb225cc257e43eb4ac5a0c98ef5b3173ac', '17773599628', '432828199205202020', '1', '2019-09-08 03:26:56', '2020-05-28 17:43:37', 'default', '', '0', NULL, NULL, 'rain');

-- ----------------------------
-- Table structure for tb_user_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role_ref`;
CREATE TABLE `tb_user_role_ref`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_role_ref
-- ----------------------------
INSERT INTO `tb_user_role_ref` VALUES ('2026c195490bf8595b28b2a11faceb47', '3', '1');
INSERT INTO `tb_user_role_ref` VALUES ('41f0209fec187389a3cdc8843582740f', '3a2e78acdeefd3dfa3fe1be843116e4d', '4c302945df5cd325cf56b4e748816bb6');
INSERT INTO `tb_user_role_ref` VALUES ('5a8c4f6383ac7ea429dcad3c9b97cdd2', 'dc0a48e6107c681399f5bcee1089473d', '4c302945df5cd325cf56b4e748816bb6');
INSERT INTO `tb_user_role_ref` VALUES ('74e07503a83b9e6185d766ea2dc2319c', 'aeb3d319150d4708c42e9eb05c9760cc', '4c302945df5cd325cf56b4e748816bb6');
INSERT INTO `tb_user_role_ref` VALUES ('ab8238e7f9b54c075bf53de9b048eba1', 'b671b0af9715bd13ef65790318adcb83', '4c302945df5cd325cf56b4e748816bb6');
INSERT INTO `tb_user_role_ref` VALUES ('c553382936be0e5d9772e18d7492c04b', '3', '2');
INSERT INTO `tb_user_role_ref` VALUES ('d77f6001ce86bf44e7f6683f2ab1240c', 'a9f57e16f904c8e8244e796ca32f2ba5', '4c302945df5cd325cf56b4e748816bb6');

-- ----------------------------
-- Table structure for tb_user_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_token`;
CREATE TABLE `tb_user_token`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expire_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `type` int(2) NULL DEFAULT NULL COMMENT '0：管理后台，1：work wechat',
  `ext1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_token
-- ----------------------------
INSERT INTO `tb_user_token` VALUES ('3cbadceefb2cb41b945779220e1dfdca', 'b671b0af9715bd13ef65790318adcb83', 'f3efb707f48502355399cb952f0d915c2', '2020-03-25 09:41:44', '2020-03-25 15:41:44', 2, 'ww3ee4a8937d359ed5', NULL);
INSERT INTO `tb_user_token` VALUES ('4bda3cc16ee4900f566a317473d70580', 'a9f57e16f904c8e8244e796ca32f2ba5', '12d0ac51a05ed4d965011ecffdd28a092', '2020-03-25 12:11:29', '2020-03-25 18:05:13', 2, 'ww3ee4a8937d359ed5', NULL);
INSERT INTO `tb_user_token` VALUES ('605228b4954a7cf247dc549818996efa', 'dc0a48e6107c681399f5bcee1089473d', '2db2b47dd651ba470abdaeb19b48afec2', '2020-03-25 09:49:58', '2020-03-25 15:49:58', 2, 'ww3ee4a8937d359ed5', NULL);
INSERT INTO `tb_user_token` VALUES ('6bcbf1f0c7e3639cf7537cf28b870512', '3a2e78acdeefd3dfa3fe1be843116e4d', '41154d77826bffc330d2aef965be3f562', '2020-03-25 09:50:10', '2020-03-25 15:50:10', 2, 'ww3ee4a8937d359ed5', NULL);
INSERT INTO `tb_user_token` VALUES ('a3b8a63c3f4b048e731c4bbbf6abd9b0', 'YuanXianXian@A', 'ddf1428feb2f3ded62b86d3e9eed690d2', '2020-03-25 09:34:31', '2020-03-22 19:47:20', 2, 'ww3ee4a8937d359ed5', NULL);
INSERT INTO `tb_user_token` VALUES ('da7be24c7e706df9f2e0d4e3677bf754', '3', '735978828d7f35da52f3f1671356f0170', '2020-07-15 08:34:31', '2020-03-04 16:37:42', 0, NULL, NULL);
INSERT INTO `tb_user_token` VALUES ('e0a81fe15af75a3354fdf3ee7d9747cf', 'YuanXianXian', 'bb5880f673f63168d8ca12c07eef23d51', '2020-03-04 10:45:25', '2020-03-04 16:21:59', 1, 'ww3ee4a8937d359ed5', NULL);
INSERT INTO `tb_user_token` VALUES ('e7a12b6f969deee07c070d9a711f936e', 'aeb3d319150d4708c42e9eb05c9760cc', 'db63e389ce3767e5c11af865eb279b5c2', '2020-03-25 09:40:49', '2020-03-25 15:40:49', 2, 'ww3ee4a8937d359ed5', NULL);

SET FOREIGN_KEY_CHECKS = 1;
