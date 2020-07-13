/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : t_test_system

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2020-06-21 23:20:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_learn_image_time
-- ----------------------------
DROP TABLE IF EXISTS `t_user_learn_image_time`;
CREATE TABLE `t_user_learn_image_time` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL,
  `image_url` varchar(255) default NULL COMMENT '图片Url',
  `learn_time` varchar(255) default NULL COMMENT '学习时长单位毫秒',
  `type` tinyint(1) default NULL COMMENT '1:学习阶段；2:测试阶段',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
