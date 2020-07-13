/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : t_test_system

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2020-06-21 23:21:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_learn_test
-- ----------------------------
DROP TABLE IF EXISTS `t_user_learn_test`;
CREATE TABLE `t_user_learn_test` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL,
  `user_learn_image_name` text COMMENT '学习图片名称字符串',
  `user_learn_image` text COMMENT '学习图片路径字符串',
  `user_test_image_name` varchar(8000) default NULL COMMENT '测试图片名称字符串',
  `user_test_image` varchar(8000) default NULL COMMENT '测试图片路径字符串',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '更新时间',
  `group_number` varchar(11) default NULL COMMENT '分组信息',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
