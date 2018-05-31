/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50638
 Source Host           : localhost
 Source Database       : stock

 Target Server Type    : MySQL
 Target Server Version : 50638
 File Encoding         : utf-8

 Date: 05/04/2018 08:59:57 AM
*/

create database `stock` default character set utf8 collate utf8_general_ci;

use stock;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `record`
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `send_time` varchar(255) DEFAULT NULL,
  `aim_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `record`
-- ----------------------------
BEGIN;
INSERT INTO `record` VALUES ('14', '2018-05-03 21:27:19', '02042006@163.com'), ('15', '2018-05-03 21:29:00', '02042006@163.com');
COMMIT;

-- ----------------------------
--  Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `open_price` float DEFAULT NULL,
  `recent_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
