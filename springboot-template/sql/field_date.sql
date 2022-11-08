/*
 Navicat Premium Data Transfer

 Source Server         : 服务器
 Source Server Type    : MySQL
 Source Server Version : 50650

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 25/02/2022 19:57:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for field_date
-- ----------------------------
DROP TABLE IF EXISTS `field_date`;
CREATE TABLE `field_date`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `template_id` int(11)      DEFAULT NULL COMMENT '字段模板id',
    `field_data`  text COMMENT '模板字段数据',
    `remark`      varchar(255) DEFAULT NULL COMMENT '备注',
    `obj_id`      varchar(64)  DEFAULT NULL COMMENT '具体数据id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
