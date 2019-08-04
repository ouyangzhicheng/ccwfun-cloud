/*
 Navicat Premium Data Transfer

 Source Server         : ccw
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : ccwfun-account

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 03/08/2019 23:38:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_register_id` bigint(20) DEFAULT NULL COMMENT '注册Id',
  `ture_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '别名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像地址',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `phone` bigint(12) DEFAULT NULL COMMENT '绑定的手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '绑定的邮箱',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_info_id`) USING BTREE,
  UNIQUE INDEX `INDEX_USER_REGISTER_ID`(`user_register_id`) USING BTREE COMMENT '关联注册ID'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 1, '欧阳志成', '小成成', 25, 1, 'userIcon/9495e432-a0db-4937-9c19-64baa1842677.jpg', '1993-04-01', 15915789343, '296729445@qq.com', 1, '2018-04-26 23:13:34');
INSERT INTO `user_info` VALUES (2, 2, '丁嘉雯', '小雯雯', 22, 1, 'userIcon/9495e432-a0db-4937-9c19-64baa1842677.jpg', '1995-03-20', 18826248598, '1026148869@qq.com', 1, '2018-04-26 23:13:49');
INSERT INTO `user_info` VALUES (3, 3, NULL, 'ccwfun', NULL, NULL, NULL, NULL, NULL, NULL, 0, '2019-08-03 22:25:41');

-- ----------------------------
-- Table structure for user_register
-- ----------------------------
DROP TABLE IF EXISTS `user_register`;
CREATE TABLE `user_register`  (
  `user_register_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '注册主键Id',
  `account_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户账号ID',
  `pass_word` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
  `user_number` bigint(20) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `register_time` datetime(0) DEFAULT NULL COMMENT '注册时间',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码盐',
  PRIMARY KEY (`user_register_id`) USING BTREE,
  UNIQUE INDEX `INDEX_ACCOUNT_ID`(`account_id`) USING BTREE COMMENT '账号唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_register
-- ----------------------------
INSERT INTO `user_register` VALUES (1, '15915789343', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, '2018-07-10 22:50:21', NULL);
INSERT INTO `user_register` VALUES (2, '18826248598', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, '2018-07-10 23:04:57', NULL);
INSERT INTO `user_register` VALUES (3, '296729445@qq.com', '2c701d54f7d836202c4e0ae499c55b36', NULL, NULL, '2019-08-03 22:25:21', '018effcfb3cc42628d50b26ccd1e45c6');

SET FOREIGN_KEY_CHECKS = 1;
