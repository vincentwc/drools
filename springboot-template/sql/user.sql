SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `template_user`;
CREATE TABLE `template_user`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
    `pwd`       varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
    `sex`       int(2)                                DEFAULT NULL,
    `age`       int(5)                                DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 181
  DEFAULT CHARSET = utf8
  COLLATE = utf8_croatian_ci;

SET FOREIGN_KEY_CHECKS = 1;
