/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_122.51.135.94
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 122.51.135.94:3306
 Source Schema         : skillfull_system_service

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 15/03/2021 11:06:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_common_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_common_area`;
CREATE TABLE `sys_common_area` (
  `area_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '本区域id',
  `province_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属省级id',
  `simple_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '中文简称',
  `area_level` int DEFAULT NULL COMMENT '区域级别:1为省级，2为市级，3为县级',
  `area_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区域名称',
  `area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区号',
  `city_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属城市id',
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上级区域id',
  `lon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '本区域经度',
  `lat` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '本区域纬度',
  `zip_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮编',
  `whole_name` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '完整名称',
  `pre_pin_yin` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区域名称拼音的第一个字母',
  `pin_yin` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称全拼',
  `simple_py` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '首字母简拼',
  `county_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区县id',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `auto_bind` tinyint(1) NOT NULL DEFAULT '0' COMMENT '绑定方式:0-手动,1-自动。默认0',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识:0-未删除,1-删除。默认0',
  PRIMARY KEY (`area_id`) USING BTREE,
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`),
  KEY `Index_parent_id` (`parent_id`),
  KEY `Index_area_name` (`area_name`),
  KEY `Index_area_level` (`area_level`),
  KEY `Index_province_id` (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';

-- ----------------------------
-- Records of sys_common_area
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_common_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_common_category`;
CREATE TABLE `sys_common_category` (
  `category_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类id',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级id',
  `category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `category_common_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类统一编码',
  `category_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类编码',
  `is_parent` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否父节:0-不是，1-时，默认0',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `unique_category_code` (`category_code`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_category_common_code` (`category_common_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类字典表';

-- ----------------------------
-- Records of sys_common_category
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_common_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_common_dict`;
CREATE TABLE `sys_common_dict` (
  `dict_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典id',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `dict_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '字典状态：1启用，0禁用，默认0',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '字典类型：0-字符串,1-数字。默认0',
  `unique_help` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除改值未主键',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `unique_dict` (`dict_code`,`unique_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_common_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_common_dict` VALUES ('1360262487929831424', '水电费水电费', '水电费水电费', 1, '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-02-13 00:20:24', '1355167397507022848', 'zxiaozhou', '2021-02-13 00:22:32', 0);
INSERT INTO `sys_common_dict` VALUES ('sdfsdf', 'sdfsdf', 'sdfsdf', 1, '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-02-12 15:48:36', '1355167397507022848', 'zxiaozhou', '2021-02-13 00:22:33', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_common_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_common_dict_item`;
CREATE TABLE `sys_common_dict_item` (
  `item_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典项id',
  `dict_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典id',
  `item_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项名称',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项值',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序,越小越靠前,默认0',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '字典项状态：1启用，0禁用，默认0',
  `index_help` varchar(32) NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除改值未主键',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `unique_dict_item` (`dict_id`,`item_value`,`index_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_dict_code` (`dict_code`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典配置项表';

-- ----------------------------
-- Records of sys_common_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_common_dict_item` VALUES ('1360272759264428032', '1360262487929831424', 'sdfsdf', 'sdfsdf', '水电费水电费', 1, 1, '1', NULL, NULL, NULL, NULL, '1355167397507022848', NULL, 'zxiaozhou', '2021-02-13 01:01:13', '1355167397507022848', 'zxiaozhou', '2021-02-13 01:10:06', 'sdfsdf', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_common_system
-- ----------------------------
DROP TABLE IF EXISTS `sys_common_system`;
CREATE TABLE `sys_common_system` (
  `system_id` varchar(32) NOT NULL COMMENT '系统id',
  `system_name` varchar(64) NOT NULL COMMENT '系统名称',
  `system_code` varchar(64) NOT NULL COMMENT '系统编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`system_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统表';

-- ----------------------------
-- Records of sys_common_system
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_manage_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_manage_route`;
CREATE TABLE `sys_manage_route` (
  `route_id` varchar(32) NOT NULL COMMENT '路由id',
  `route_code` varchar(256) NOT NULL COMMENT '路由编码(唯一)',
  `service_id` varchar(32) NOT NULL COMMENT '服务id',
  `route_name` varchar(256) NOT NULL COMMENT '路由名称',
  `url` varchar(256) DEFAULT NULL COMMENT '路由url地址,当选择非负载均衡器时必填',
  `is_load_balancer` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否负载均衡器:0-不是,1-时，默认0。选择均衡器时服务名必填，url不填，与服务对应',
  `load_balancer_type` tinyint(1) DEFAULT NULL COMMENT '负载均衡器类型:0-普通,1-ws,2-wss',
  `metadata_json` json DEFAULT NULL COMMENT '路由元数据,数据库json存储,入库前转为字符串',
  `service_name` varchar(255) DEFAULT NULL COMMENT '服务名,当选择负载均衡器时使用必填',
  `route_order` int NOT NULL DEFAULT '0' COMMENT '路由排序,越小越靠前，默认0',
  `route_state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '路由状态:0-禁用,1-启用。默认0',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `unique_help` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`route_id`),
  UNIQUE KEY `unique_route_code` (`route_code`,`unique_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由';

-- ----------------------------
-- Records of sys_manage_route
-- ----------------------------
BEGIN;
INSERT INTO `sys_manage_route` VALUES ('1305011446095593472', 'system-service', '1305230675075973120', '系统服务', NULL, 1, 0, '{\"test1\": \"test1\", \"test2\": \"test2\"}', 'system-service', 0, 1, '系统服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-13 13:12:29', NULL, NULL, '2020-09-13 13:56:36', 0);
INSERT INTO `sys_manage_route` VALUES ('1305012085303328768', 'logging-service', '1305231062931652608', '日志服务', NULL, 1, 0, '{\"test1\": \"test1\", \"test2\": \"test2\"}', 'logging-service', 0, 1, '日志服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-13 13:15:02', NULL, NULL, '2020-09-13 14:01:38', 0);
INSERT INTO `sys_manage_route` VALUES ('1305234957393248256', 'system-swagger-route', '1305230675075973120', '系统服务swagger路由', NULL, 1, 0, '{\"test1\": \"test1\", \"test2\": \"test2\"}', 'system-service', 0, 1, 'swagger路由', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-14 04:00:39', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route` VALUES ('1305235098456080384', 'logging-swagger-route', '1305231062931652608', '日志服务swagger路由', NULL, 1, 0, '{\"test1\": \"test1\", \"test2\": \"test2\"}', 'logging-service', 0, 1, 'swagger路由', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-14 04:01:12', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route` VALUES ('1309309115202846720', 'auth-service', '1309310180635754496', '授权服务', NULL, 1, 0, 'null', 'auth-service', 0, 1, '授权服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-25 09:49:54', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route` VALUES ('1309309496498634752', 'auth-swagger-route', '1309310180635754496', '授权服务swagger路由', NULL, 1, 0, 'null', 'auth-service', 1, 1, '授权服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-25 09:51:24', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route` VALUES ('1316443939063513088', 'process-swagger-route', '1316397089367310336', '流程服务swagger路由', NULL, 1, 0, 'null', 'process-service', 1, 1, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-15 02:21:08', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route` VALUES ('1316444537498419200', 'process--route', '1316397089367310336', '流程服务', NULL, 1, 0, 'null', 'process-service', 1, 1, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-15 02:23:31', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route` VALUES ('1320005665474265088', 'storage-service', '1319603294453678080', '存储服务', NULL, 1, 0, 'null', 'storage-service', 0, 1, '存储服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-24 22:14:10', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route` VALUES ('1320006411649335296', 'storage-swagger-route', '1319603294453678080', '存储服务swagger路由', NULL, 1, 0, 'null', 'storage-service', 1, 1, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-24 22:17:08', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route` VALUES ('1353955733914828800', 'message-swagger-route', '1353954548415774720', '消息服务swagger路由', NULL, 1, 0, '{\"fsdfsdf\": \"sdfs111d1111111111f11112211122\", \"sdfsdff\": \"sdfsdfsdf\"}', 'message-service', 0, 1, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:39:37', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:39:13', 0);
INSERT INTO `sys_manage_route` VALUES ('1353956680351137792', 'message-service', '1353954548415774720', '消息服务路由', NULL, 1, 0, '{\"sdfsdf\": \"sdfsdfsdf\"}', 'message-service', 1, 1, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:43:23', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:39:28', 0);
INSERT INTO `sys_manage_route` VALUES ('1353956964917886976', 'message-websockett-service', '1353954548415774720', '消息服务websocket路由', NULL, 1, 1, '{\"111111\": \"11111111\"}', 'message-service', 0, 1, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:44:30', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:39:36', 0);
INSERT INTO `sys_manage_route` VALUES ('1370258431303794688', 'monitor-service', '1370225222797017088', '监控服务路由', NULL, 1, 0, 'null', 'monitor-service', 0, 1, NULL, '1', NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-03-12 14:20:43', '1355167397507022848', 'zxiaozhou', '2021-03-12 14:26:45', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_manage_route_filter
-- ----------------------------
DROP TABLE IF EXISTS `sys_manage_route_filter`;
CREATE TABLE `sys_manage_route_filter` (
  `filter_id` varchar(32) NOT NULL COMMENT '过滤器id',
  `service_id` varchar(32) NOT NULL COMMENT '服务id',
  `route_id` varchar(32) NOT NULL COMMENT '路由id',
  `filter_type` varchar(256) NOT NULL COMMENT '过滤器类型',
  `rules` json NOT NULL COMMENT '过滤器规则:[{ruleName:规则名称,ruleValue:规则值}]',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`filter_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_route_id` (`route_id`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由过滤器';

-- ----------------------------
-- Records of sys_manage_route_filter
-- ----------------------------
BEGIN;
INSERT INTO `sys_manage_route_filter` VALUES ('1305022545654235136', '1305230675075973120', '1305011446095593472', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', '路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-13 13:56:36', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_filter` VALUES ('1305023815798538240', '1305231062931652608', '1305012085303328768', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', '路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-13 14:01:39', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_filter` VALUES ('1309309115488059392', '1309310180635754496', '1309309115202846720', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-25 09:49:54', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_filter` VALUES ('1316444537670385664', '1316397089367310336', '1316444537498419200', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-15 02:23:31', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_filter` VALUES ('1320005667382673408', '1319603294453678080', '1320005665474265088', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', '路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-24 22:14:10', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_filter` VALUES ('1353956680581824512', '1353954548415774720', '1353956680351137792', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', '路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:43:23', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1353956965043716096', '1353954548415774720', '1353956964917886976', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', '路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:44:30', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1353960086117597184', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/message/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:56:55', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1353960784280469504', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:59:41', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1353961516886966272', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/(?<segment>.*)\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 15:02:36', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1353962647079927808', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 15:07:05', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356156542421352448', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 16:24:51', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356156915567607808', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 16:26:20', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356157295911288832', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 16:27:50', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356157343386615808', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 16:28:02', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356169630059118592', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:16:51', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356170966112714752', '1353954548415774720', '1353956964917886976', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', '路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:22:09', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356171624232566784', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:24:46', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356171947420467200', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:26:03', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356172834423488512', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:29:35', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356172904644526080', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:29:52', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356173138103681024', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:30:47', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356173251844816896', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:31:14', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356173609585393664', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:32:40', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356173719291609088', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:33:06', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356173929078112256', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:33:56', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356174988295061504', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:38:08', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356175238699204608', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:39:08', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_filter` VALUES ('1356175261344251904', '1353954548415774720', '1353955733914828800', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/v3/api-docs\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/message/v3/api-docs\"}]', 'api docs路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:39:13', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_filter` VALUES ('1356175322128105472', '1353954548415774720', '1353956680351137792', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', '路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:39:28', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_filter` VALUES ('1356175354965311488', '1353954548415774720', '1353956964917886976', 'RewritePath', '[{\"ruleName\": \"_genkey_0\", \"ruleValue\": \"/${segment}\"}, {\"ruleName\": \"_genkey_1\", \"ruleValue\": \"/api/(?<segment>.*)\"}]', '路由重写', NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', NULL, 'zxiaozhou', '2021-02-01 17:39:36', NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_manage_route_predicate
-- ----------------------------
DROP TABLE IF EXISTS `sys_manage_route_predicate`;
CREATE TABLE `sys_manage_route_predicate` (
  `predicate_id` varchar(32) NOT NULL COMMENT '断言id',
  `service_id` varchar(32) NOT NULL COMMENT '服务id',
  `route_id` varchar(32) NOT NULL COMMENT '路由id',
  `predicate_type` varchar(64) NOT NULL COMMENT '断言类型',
  `rules` json NOT NULL COMMENT '断言规则:[{ruleName:规则名称,ruleValue:规则值}]',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`predicate_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_route_id` (`route_id`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由断言';

-- ----------------------------
-- Records of sys_manage_route_predicate
-- ----------------------------
BEGIN;
INSERT INTO `sys_manage_route_predicate` VALUES ('1305022545624875008', '1305230675075973120', '1305011446095593472', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/system/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-13 13:56:36', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1305023815744012288', '1305231062931652608', '1305012085303328768', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/logging/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-13 14:01:39', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1305234958534098944', '1305230675075973120', '1305234957393248256', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/system/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-14 04:00:39', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1305235098539966464', '1305231062931652608', '1305235098456080384', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/logging/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-14 04:01:12', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1309309115408367616', '1309310180635754496', '1309309115202846720', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/auth/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-25 09:49:54', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1309309496569937920', '1309310180635754496', '1309309496498634752', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/auth/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-25 09:51:24', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1316443939755573248', '1316397089367310336', '1316443939063513088', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/process/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-15 02:21:08', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1316444537557139456', '1316397089367310336', '1316444537498419200', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/process/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-15 02:23:31', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1320005667068100608', '1319603294453678080', '1320005665474265088', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/storage/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-24 22:14:10', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1320006411699666944', '1319603294453678080', '1320006411649335296', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/storage/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-24 22:17:08', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1353955735714185216', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:39:37', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1353956680451801088', '1353954548415774720', '1353956680351137792', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:43:23', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1353956964980801536', '1353954548415774720', '1353956964917886976', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/message/websocket/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:44:30', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1353960086067265536', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:56:55', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1353960784217554944', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:59:41', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1353961516840828928', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 15:02:36', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1353962647029596160', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 15:07:05', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356156542249385984', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 16:24:50', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356156915416612864', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 16:26:19', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356157295835791360', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 16:27:50', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356157343302729728', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 16:28:01', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356169629950066688', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:16:51', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356170966007857152', '1353954548415774720', '1353956964917886976', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/message/websocket/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:22:09', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356171624136097792', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:24:46', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356171947311415296', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:26:03', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356172834310242304', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:29:35', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356172904564834304', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:29:52', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356173137977851904', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:30:47', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356173251765125120', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:31:14', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356173609488924672', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:32:40', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356173719199334400', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:33:06', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356173928994226176', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:33:56', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356174988227952640', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:38:08', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356175238623707136', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:39:08', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356175261260365824', '1353954548415774720', '1353955733914828800', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:39:13', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356175322044219392', '1353954548415774720', '1353956680351137792', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/message/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:39:28', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1356175354894008320', '1353954548415774720', '1353956964917886976', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/message/websocket/**\"}]', '基于路径', NULL, NULL, NULL, NULL, NULL, '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-02-01 17:39:36', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_route_predicate` VALUES ('1370258432515948544', '1370225222797017088', '1370258431303794688', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/monitor/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-03-12 14:20:43', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1370259856641540096', '1370225222797017088', '1370258431303794688', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/monitor/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-03-12 14:26:23', NULL, NULL, NULL, 1);
INSERT INTO `sys_manage_route_predicate` VALUES ('1370259949843169280', '1370225222797017088', '1370258431303794688', 'Path', '[{\"ruleName\": \"path\", \"ruleValue\": \"/api/monitor/**\"}]', NULL, NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-03-12 14:26:45', NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_manage_router_special_url
-- ----------------------------
DROP TABLE IF EXISTS `sys_manage_router_special_url`;
CREATE TABLE `sys_manage_router_special_url` (
  `route_special_id` varchar(32) NOT NULL COMMENT '特殊路由id',
  `route_id` varchar(32) NOT NULL COMMENT '路由id',
  `filter_id` varchar(32) NOT NULL COMMENT '处理过滤器类型',
  `service_url` varchar(256) NOT NULL COMMENT '后端服务地址',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`route_special_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_route_id` (`route_id`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由特殊地址';

-- ----------------------------
-- Records of sys_manage_router_special_url
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_manage_service
-- ----------------------------
DROP TABLE IF EXISTS `sys_manage_service`;
CREATE TABLE `sys_manage_service` (
  `service_id` varchar(32) NOT NULL COMMENT '服务id',
  `swagger_tag` varchar(128) DEFAULT NULL COMMENT 'swagger tag显示名称',
  `swagger_doc_uri` varchar(128) DEFAULT NULL COMMENT 'swagger doc uri,即凭借上网关ip信息就能访问的uri',
  `service_name` varchar(128) NOT NULL COMMENT '服务名,注册中心名称',
  `is_load_balancer` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否负载均衡器:0-不是,1-是，默认0。选择均衡器时监听信息才可以使用,同时该字段与路由对应',
  `subscribe_change` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否监听服务变化:0-不订阅,1-订阅,默认0',
  `notice_change` tinyint(1) DEFAULT '0' COMMENT '是否发送变化通知:0-不通知,1-通知。默认0',
  `notice_type` smallint DEFAULT NULL COMMENT '通知类型:0-邮件,1-短信,2-微信消息，当选择监听服务变化并且通知时必填',
  `service_state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '服务状态:0-禁用,1-启用。默认0',
  `notice_template_id` varchar(32) DEFAULT '0' COMMENT '通知模板id，当选择监听服务变化并且通知时必填',
  `head_user_name` varchar(32) DEFAULT NULL COMMENT '负责人姓名，当选择监听服务变化并且通知时必填',
  `head_user_id` varchar(32) DEFAULT NULL COMMENT '负责人用户id，当选择监听服务变化并且通知时必填',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `unique_help` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `unique_service_name` (`unique_help`,`service_name`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务管理';

-- ----------------------------
-- Records of sys_manage_service
-- ----------------------------
BEGIN;
INSERT INTO `sys_manage_service` VALUES ('1305230675075973120', 'system-service(系统服务)', '/system/v3/api-docs', 'system-service', 1, 0, 0, NULL, 1, '0', NULL, NULL, '系统服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-14 03:43:38', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_service` VALUES ('1305231062931652608', 'logging-service(日志服务)', '/logging/v3/api-docs', 'logging-service', 1, 0, 0, NULL, 1, '0', NULL, NULL, '日志服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-14 03:45:10', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_service` VALUES ('1309310180635754496', 'auth-service(授权服务)', '/auth/v3/api-docs', 'auth-service', 1, 0, 0, NULL, 1, '0', NULL, NULL, '授权服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-25 09:54:08', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_service` VALUES ('1316397089367310336', 'process-service(流程服务)', '/process/v3/api-docs', 'process-service', 1, 0, 0, NULL, 1, '0', NULL, NULL, '流程服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-14 23:14:58', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_service` VALUES ('1319603294453678080', 'storage-service(存储服务)', '/storage/v3/api-docs', 'storage-service', 1, 0, 0, NULL, 1, '0', NULL, NULL, '存储服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-23 19:35:17', NULL, NULL, NULL, 0);
INSERT INTO `sys_manage_service` VALUES ('1353954548415774720', 'message-service(消息服务)', '/message/v3/api-docs', 'message-service', 1, 0, 0, NULL, 1, '0', NULL, NULL, '消息中心服务', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 14:34:54', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-01-28 23:18:50', 0);
INSERT INTO `sys_manage_service` VALUES ('1370225222797017088', NULL, NULL, 'monitor-service', 1, 0, 0, NULL, 1, '0', NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-03-12 12:08:45', '1355167397507022848', 'zxiaozhou', '2021-03-12 12:08:49', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_manage_source
-- ----------------------------
DROP TABLE IF EXISTS `sys_manage_source`;
CREATE TABLE `sys_manage_source` (
  `data_source_id` varchar(36) NOT NULL COMMENT '数据源id',
  `db_code` varchar(100) DEFAULT NULL COMMENT '数据源编码',
  `db_source_name` varchar(100) DEFAULT NULL COMMENT '数据源名称',
  `db_type` varchar(10) DEFAULT NULL COMMENT '数据库类型',
  `db_driver` varchar(100) DEFAULT NULL COMMENT '驱动类',
  `db_url` varchar(500) DEFAULT NULL COMMENT '数据源地址',
  `db_name` varchar(100) DEFAULT NULL COMMENT '数据库名称',
  `db_username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `db_password` varchar(100) DEFAULT NULL COMMENT '密码',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`data_source_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据源表';

-- ----------------------------
-- Records of sys_manage_source
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
