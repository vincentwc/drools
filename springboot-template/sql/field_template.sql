/*
 Navicat Premium Data Transfer

 Source Server         : 服务器
 Source Server Type    : MySQL
 Source Server Version : 50650

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 25/02/2022 19:57:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for field_template
-- ----------------------------
DROP TABLE IF EXISTS `field_template`;
CREATE TABLE `field_template`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `type`     int(2)     NOT NULL COMMENT '自定义字段关联表类型（1：客户，2：跟进任务）',
    `template` longtext COMMENT 'json自定义字段模板',
    `remark`   varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
