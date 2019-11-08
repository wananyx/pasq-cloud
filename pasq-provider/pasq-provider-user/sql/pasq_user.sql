/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : pasq_user

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 08/11/2019 17:43:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, NULL, 0, '2019-04-09 15:11:30', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', NULL, NULL, NULL, NULL, NULL, '2019-04-09 17:39:29', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', NULL, NULL, NULL, NULL, NULL, '2019-04-09 17:43:21', NULL);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', NULL, NULL, NULL, NULL, NULL, '2019-04-09 17:43:52', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名英文',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `created` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ADMIN', '管理员', '2019-04-09 16:24:34', '2019-04-30 14:35:05');
INSERT INTO `sys_role` VALUES (2, 'AUTHOR', '作家', '2019-04-09 16:24:34', '2019-04-30 14:35:11');
INSERT INTO `sys_role` VALUES (3, 'YUANREN', '程序员', '2019-04-09 16:28:47', '2019-04-30 14:34:57');
INSERT INTO `sys_role` VALUES (4, 'ONLYART', '只有文章的', '2019-04-09 14:44:11', '2019-04-30 14:35:24');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (2, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `img_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别 0男 1女',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1有效,0无效）',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型（暂未用）',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$3uOoX1ps14CxuotogUoDreW8zXJOZB9XeGdrC/xDV36hhaE8Rn9HO', '测试1', '', '', 1, 1, 'APP', '2018-01-17 16:57:01', '2018-01-17 16:57:01');
INSERT INTO `sys_user` VALUES (2, 'superadmin', '$2a$10$.gLkG0j2kM0stWoOvPBvqu0H9uSD0HUlpErI.PTKyZQkSUZIV2wFq', '超级管理员', NULL, NULL, 0, 1, 'BACKEND', '2018-01-19 20:30:11', '2018-01-19 20:30:11');
INSERT INTO `sys_user` VALUES (8, 'ceshi', '$2a$10$sdviAm8C0mq6aRH.JI1D3u/K6afgdUmTsMInQ8wBvhLQ5uOu4MWE2', '测试账号', NULL, NULL, 1, 1, NULL, '2019-05-07 11:44:57', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1);
INSERT INTO `sys_user_role` VALUES (8, 2);

SET FOREIGN_KEY_CHECKS = 1;
