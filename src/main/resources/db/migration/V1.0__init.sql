SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint                                                        NOT NULL COMMENT '主键',
    `username`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `password`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `version`     tinyint                                                       NOT NULL DEFAULT 1 COMMENT '版本号',
    `create_time` datetime                                                      NOT NULL COMMENT '开始时间',
    `update_time` datetime                                                      NOT NULL COMMENT '结束时间',
    `deleted`     tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除(1、逻辑已删除 0、逻辑未删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET
FOREIGN_KEY_CHECKS = 1;
