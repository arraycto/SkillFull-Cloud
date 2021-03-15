/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_122.51.135.94
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 122.51.135.94:3306
 Source Schema         : skillfull_storage_service

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 15/03/2021 10:56:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for storage_local_file
-- ----------------------------
DROP TABLE IF EXISTS `storage_local_file`;
CREATE TABLE `storage_local_file` (
  `local_file_id` varchar(32) NOT NULL COMMENT '文件id',
  `file_original_name` varchar(256) DEFAULT NULL COMMENT '原始文件名(不包括扩展名)',
  `file_original_full_name` varchar(256) DEFAULT NULL COMMENT '原始文件名全称(包括扩展名)',
  `file_full_name` varchar(256) DEFAULT NULL COMMENT '文件名全名称(包括扩展名)',
  `file_type` varchar(32) DEFAULT NULL COMMENT '文件类型',
  `file_size` varchar(256) NOT NULL COMMENT '文件大小',
  `file_size_detail` bigint NOT NULL DEFAULT '0' COMMENT '文件详细大小',
  `file_md5` varchar(256) DEFAULT NULL COMMENT '文件md5值',
  `file_disk_relative_path` varchar(512) NOT NULL COMMENT '文件磁盘相对路径(即系统定义文件文件存放磁盘开始路径除外)',
  `file_mapping_path` varchar(512) NOT NULL COMMENT '文件预览相对路径(即加了file的映射路径)',
  `file_dir_prefix` varchar(128) DEFAULT NULL COMMENT '文件存放文件夹名称',
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
  PRIMARY KEY (`local_file_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_file_type` (`file_type`),
  KEY `Index_file_original_full_name` (`file_original_full_name`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='本地文件服务';

-- ----------------------------
-- Table structure for storage_oss_file
-- ----------------------------
DROP TABLE IF EXISTS `storage_oss_file`;
CREATE TABLE `storage_oss_file` (
  `oss_file_id` varchar(32) NOT NULL COMMENT '文件id',
  `file_original_name` varchar(256) DEFAULT NULL COMMENT '原始文件名(不包括扩展名)',
  `file_full_name` varchar(256) DEFAULT NULL COMMENT '文件名全名称(包括扩展名)',
  `file_original_full_name` varchar(256) DEFAULT NULL COMMENT '原始文件名全称(包括扩展名)',
  `file_type` varchar(32) DEFAULT NULL COMMENT '文件类型',
  `file_size` varchar(256) NOT NULL COMMENT '文件大小',
  `file_size_detail` bigint NOT NULL DEFAULT '0' COMMENT '文件详细大小',
  `oss_tag` varchar(128) DEFAULT NULL COMMENT 'oss tag信息',
  `file_dir_prefix` varchar(256) DEFAULT NULL COMMENT '文件存放文件夹名称',
  `file_md5` varchar(256) NOT NULL DEFAULT '0' COMMENT '文件md5值',
  `endpoint` varchar(128) NOT NULL DEFAULT '0' COMMENT 'endpoint',
  `bucket` varchar(512) NOT NULL DEFAULT '0' COMMENT 'bucket名称',
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
  PRIMARY KEY (`oss_file_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_file_type` (`file_type`),
  KEY `Index_file_original_full_name` (`file_original_full_name`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='oss文件';

SET FOREIGN_KEY_CHECKS = 1;
