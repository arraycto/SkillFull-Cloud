/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_122.51.135.94
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 122.51.135.94:3306
 Source Schema         : skillfull_logging_service

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 15/03/2021 10:56:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for logging_data
-- ----------------------------
DROP TABLE IF EXISTS `logging_data`;
CREATE TABLE `logging_data` (
  `data_log_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据日志id',
  `data_table` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表名',
  `log_note` varchar(256) DEFAULT NULL COMMENT '日志注解',
  `data_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据id',
  `data_content` longtext COMMENT '数据内容',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求ip',
  `request_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求参数',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求方法',
  `request_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求路径',
  `file_name` varchar(512) DEFAULT NULL COMMENT '调用文件名',
  `method_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调用方法名',
  `method_params` longtext COMMENT '调用方法参数',
  `method_status` smallint NOT NULL COMMENT '调用方法状态:0-失败,1-成功',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人用户id',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人用户名称',
  `exception_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '异常名',
  `stack_trace` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '堆栈信息',
  `exception_message` longtext COMMENT '异常消息',
  `line_number` int DEFAULT NULL COMMENT '代码行数',
  `data_sources` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据来源',
  `cost_time` bigint DEFAULT NULL COMMENT '耗时',
  `request_start_time` datetime DEFAULT NULL COMMENT '请求开始时间',
  `request_end_time` datetime DEFAULT NULL COMMENT '请求结束时间',
  `data_version` int DEFAULT NULL COMMENT '版本号',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`data_log_id`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_data_table` (`data_table`),
  KEY `Index_log_note` (`log_note`),
  KEY `Index_ip` (`ip`),
  KEY `Index_request_url` (`request_url`),
  KEY `Index_data_sources` (`data_sources`),
  KEY `Index_data_id` (`data_id`),
  KEY `Index_request_start_time` (`request_start_time`),
  KEY `Index_request_end_time` (`request_end_time`),
  KEY `Index_data_version` (`data_version`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据日志';

-- ----------------------------
-- Table structure for logging_operate
-- ----------------------------
DROP TABLE IF EXISTS `logging_operate`;
CREATE TABLE `logging_operate` (
  `sys_log_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统日志id',
  `log_note` varchar(256) DEFAULT NULL COMMENT '日志注解',
  `business_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务编码',
  `operate_type` int DEFAULT NULL COMMENT ' 操作类型（1查询，2添加，3修改，4删除，5其他）',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人用户id',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人用户名称',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求ip',
  `request_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求路径',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求方法',
  `request_param` longtext COMMENT '请求参数',
  `request_result` longtext COMMENT '请求结果',
  `request_status` int DEFAULT NULL COMMENT '请求状态',
  `file_name` varchar(512) DEFAULT NULL COMMENT '调用文件名',
  `method_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调用方法名',
  `method_params` longtext COMMENT '调用方法参数',
  `exception_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '异常名',
  `stack_trace` longtext COMMENT '堆栈信息',
  `exception_message` longtext COMMENT '异常消息',
  `line_number` int DEFAULT NULL COMMENT '代码行数',
  `data_sources` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据来源',
  `cost_time` bigint DEFAULT NULL COMMENT '耗时',
  `request_start_time` datetime DEFAULT NULL COMMENT '请求开始时间',
  `request_end_time` datetime DEFAULT NULL COMMENT '请求结束时间',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`sys_log_id`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_ip` (`ip`),
  KEY `Index_log_note` (`log_note`),
  KEY `Index_request_method` (`request_method`),
  KEY `Index_operate_type` (`operate_type`),
  KEY `Index_request_url` (`request_url`),
  KEY `Index_request_status` (`request_status`),
  KEY `Index_data_sources` (`data_sources`),
  KEY `Index_business_code` (`business_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志';

SET FOREIGN_KEY_CHECKS = 1;
