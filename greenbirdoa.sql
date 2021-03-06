/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50530
 Source Host           : localhost:3306
 Source Schema         : greenbirdoa

 Target Server Type    : MySQL
 Target Server Version : 50530
 File Encoding         : 65001

 Date: 17/06/2020 01:55:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, 'administrative');
INSERT INTO `authority` VALUES (2, 'personnel');
INSERT INTO `authority` VALUES (3, 'general');

-- ----------------------------
-- Table structure for depart
-- ----------------------------
DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `fk_authority` int(10) NOT NULL COMMENT '权限表外键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of depart
-- ----------------------------
INSERT INTO `depart` VALUES (1, '人事部', 2);
INSERT INTO `depart` VALUES (2, '开发部', 3);
INSERT INTO `depart` VALUES (3, '营销部', 3);
INSERT INTO `depart` VALUES (4, '管理层', 1);

-- ----------------------------
-- Table structure for flowstatus
-- ----------------------------
DROP TABLE IF EXISTS `flowstatus`;
CREATE TABLE `flowstatus`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of flowstatus
-- ----------------------------

-- ----------------------------
-- Table structure for jurisdiction
-- ----------------------------
DROP TABLE IF EXISTS `jurisdiction`;
CREATE TABLE `jurisdiction`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of jurisdiction
-- ----------------------------
INSERT INTO `jurisdiction` VALUES (1, '普通职员');
INSERT INTO `jurisdiction` VALUES (2, '中级职员');
INSERT INTO `jurisdiction` VALUES (3, '高级职员');

-- ----------------------------
-- Table structure for sex
-- ----------------------------
DROP TABLE IF EXISTS `sex`;
CREATE TABLE `sex`  (
  `id` int(10) NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sex
-- ----------------------------
INSERT INTO `sex` VALUES (1, '男性');
INSERT INTO `sex` VALUES (2, '女性');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(99) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `fk_sex` int(10) NOT NULL COMMENT '性别',
  `address` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '地址',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号码',
  `salary` double(10, 0) NOT NULL DEFAULT 0 COMMENT '工资',
  `lastsign` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '没签到' COMMENT '最后一次签到的日期,服务器由此判断今天是否缺勤',
  `absenteeism` int(10) NOT NULL DEFAULT 0 COMMENT '缺勤的次数',
  `fk_jurisdiction` int(10) NOT NULL COMMENT '基本',
  `fk_depart` int(10) NOT NULL COMMENT '部门表外键',
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `fk_sex`(`fk_sex`) USING BTREE,
  INDEX `fk_jurisdiction`(`fk_jurisdiction`) USING BTREE,
  INDEX `fk_depart`(`fk_depart`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('Qiu123456', '123456', '球球', 2, '广州白云', '13266523659', 100000, '2020-03-29', 2, 3, 4);
INSERT INTO `user` VALUES ('Qiu12345611', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu1234561111114', '123456', '球球', 2, '广州白云', '13266523659', 100000, '2020-03-29', 6, 3, 3);
INSERT INTO `user` VALUES ('Qiu1234561111116', '123456', '球球', 2, '广州白云', '13266523659', 100000, '2020-03-29', 6, 3, 3);
INSERT INTO `user` VALUES ('Qiu1234561111117', '123456', '球球', 2, '广州白云', '13266523659', 100000, '2020-03-29', 6, 2, 4);
INSERT INTO `user` VALUES ('Qiu1234561111118', '123456', '球球', 2, '广州白云', '13266523659', 100000, '2020-03-29', 6, 1, 4);
INSERT INTO `user` VALUES ('Qiu1234561111119', '123456', '球球', 2, '广州白云', '13266523659', 100000, '2020-03-29', 6, 3, 4);
INSERT INTO `user` VALUES ('Qiu1234561111122', '123456', '球球', 2, '广州白云', '13266523659', 100000, '2020-03-29', 6, 3, 4);
INSERT INTO `user` VALUES ('Qiu1234561113', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu1234561114', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu1234561115', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu123456113', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 2, 2);
INSERT INTO `user` VALUES ('Qiu123456114', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu123456115', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu123456116', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu123456117', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu123456118', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu123456119', '123456', '秃头', 1, '广州珠江新城', '119', 10000, '未签到', 1, 1, 2);
INSERT INTO `user` VALUES ('Qiu12345616', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3003, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu1234562', '123456', '张4', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 4);
INSERT INTO `user` VALUES ('Qiu12345624', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345625', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 3, 3);
INSERT INTO `user` VALUES ('Qiu12345626', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345627', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345628', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345629', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu1234563', '123456', '李四', 2, '广州天河', '18022352234', 10000, '未签到', 1, 2, 1);
INSERT INTO `user` VALUES ('Qiu12345630', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345631', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345632', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345633', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345634', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345635', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345636', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345637', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345638', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu12345639', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu1234564', '123456', '李5', 2, '广州天河', '18022352234', 10000, '未签到', 1, 2, 2);
INSERT INTO `user` VALUES ('Qiu12345640', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu1234566', 'Qiu123456', '王五', 2, '广州天河1', '1802152234', 10000, '2020-03-30', 4, 3, 3);
INSERT INTO `user` VALUES ('Qiu1234567', '123456', '鲁人甲', 2, '广州天河', '18022352234', 8000, '未签到', 1, 2, 3);
INSERT INTO `user` VALUES ('Qiu1234568', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);
INSERT INTO `user` VALUES ('Qiu1234569', '123456', '螺丝钉', 2, '广州天河', '18022352234', 3000, '未签到', 1, 1, 3);

-- ----------------------------
-- Table structure for workflow
-- ----------------------------
DROP TABLE IF EXISTS `workflow`;
CREATE TABLE `workflow`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '工作流的内容',
  `fk_user_initiator` int(10) NOT NULL COMMENT '发起者的user外键id',
  `fk_user_receiver` int(10) NOT NULL COMMENT '接收者的user外键id',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `fk_flowstatus` int(10) NOT NULL COMMENT '工作流状态',
  `createtime` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '工作流创建的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of workflow
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
