/*
 Navicat Premium Data Transfer

 Source Server         : springmvc-mybatis
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : sptwin

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 04/18/2018 21:26:08 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_phone` varchar(15) DEFAULT '',
  `customer_name` varchar(50) DEFAULT '',
  `customer_password` varchar(100) DEFAULT '',
  `customer_real_name` varchar(50) DEFAULT '',
  `customer_card_id` varchar(100) DEFAULT NULL,
  `regist_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `safe` varchar(100) DEFAULT '',
  `editor` int(11) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_token`
-- ----------------------------
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `token` varchar(100) DEFAULT '',
  `expires_time` varchar(50) DEFAULT '',
  `create_time` varchar(50) DEFAULT '',
  `ip` varchar(50) DEFAULT '',
  `client` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
