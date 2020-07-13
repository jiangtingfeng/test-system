/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : t_test_system

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2020-06-21 23:21:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_test_result
-- ----------------------------
DROP TABLE IF EXISTS `t_user_test_result`;
CREATE TABLE `t_user_test_result` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL,
  `right_image` varchar(5000) default NULL COMMENT '测试正确的图片字符串',
  `wrong_image` varchar(5000) default NULL COMMENT '回答错误的图片字符串',
  `right_rate` decimal(10,4) default NULL COMMENT '正确率',
  `type` tinyint(1) default NULL COMMENT '1:正确图片记录2：假图片记录',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
