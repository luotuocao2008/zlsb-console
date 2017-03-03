/*
Navicat MySQL Data Transfer

Source Server         : MYSQL连接
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : zslb

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-06-15 13:38:45
*/

SET FOREIGN_KEY_CHECKS=0;



-- ----------------------------
-- Records of zslb_post
-- ----------------------------

-- ----------------------------
-- Table structure for zslb_role
-- ----------------------------
DROP TABLE IF EXISTS `zslb_role`;
CREATE TABLE `zslb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `discard` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zslb_role
-- ----------------------------
INSERT INTO `zslb_role` VALUES ('1', '超级管理员', '0');
INSERT INTO `zslb_role` VALUES ('2', '管理员', '0');
INSERT INTO `zslb_role` VALUES ('3', '会员', '0');



-- ----------------------------
-- Table structure for zslb_user
-- ----------------------------
DROP TABLE IF EXISTS `zslb_user`;
CREATE TABLE `zslb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `photo` varchar(255) NOT NULL DEFAULT 'default.png',
  `role_id` int(11) NOT NULL DEFAULT '3',
  `ban` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_role_fk` (`role_id`),
  CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `zslb_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zslb_user
-- ----------------------------
INSERT INTO `zslb_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '123@qq.com', '123-', 'default.png', '1', '0');
