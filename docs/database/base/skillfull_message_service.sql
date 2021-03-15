/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_122.51.135.94
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 122.51.135.94:3306
 Source Schema         : skillfull_message_service

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 15/03/2021 10:56:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for msg_manage_announcement
-- ----------------------------
DROP TABLE IF EXISTS `msg_manage_announcement`;
CREATE TABLE `msg_manage_announcement` (
  `annt_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `msg_abstract` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '摘要',
  `msg_content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `sender_user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发布人姓名',
  `sender_user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发布人',
  `receive_type` smallint DEFAULT NULL COMMENT '接收对象类型：1-指定用户,2-指定区域,3-指定组织,4-指定角色,5-所有',
  `receive_infos` json DEFAULT NULL COMMENT '接收对象',
  `send_type` smallint DEFAULT NULL COMMENT '发布方式：0-手动,1-自动',
  `finaly_send_time` datetime DEFAULT NULL COMMENT '最终发布时间',
  `send_time` datetime DEFAULT NULL COMMENT '发布时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '撤销时间',
  `send_status` smallint NOT NULL DEFAULT '0' COMMENT '发布状态：0未发布，1已发布，2已撤销，默认0',
  `page_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '页面url',
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
  PRIMARY KEY (`annt_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_send_type` (`send_type`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统通告管理';

-- ----------------------------
-- Table structure for msg_manage_send_record
-- ----------------------------
DROP TABLE IF EXISTS `msg_manage_send_record`;
CREATE TABLE `msg_manage_send_record` (
  `es_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息id',
  `es_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息标题',
  `es_type` smallint DEFAULT NULL COMMENT '推送方式：1短信 2邮件 3微信',
  `es_receiver` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接收人',
  `es_param` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '推送所需参数Json格式',
  `es_content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '推送内容',
  `es_send_time` datetime DEFAULT NULL COMMENT '推送时间',
  `es_send_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '推送状态 0未推送 1推送成功 2推送失败',
  `es_send_num` int DEFAULT NULL COMMENT '发送次数 超过5次不再发送',
  `es_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '推送失败原因',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`es_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`),
  KEY `Index_create_system_code` (`create_system_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息发送记录表';

-- ----------------------------
-- Table structure for msg_manage_template
-- ----------------------------
DROP TABLE IF EXISTS `msg_manage_template`;
CREATE TABLE `msg_manage_template` (
  `template_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板id',
  `template_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模板名称',
  `template_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模板code',
  `template_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模板类型',
  `template_content` json DEFAULT NULL COMMENT '模板内容',
  `template_test_json` json DEFAULT NULL COMMENT '模板测试json',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`template_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息模板';

-- ----------------------------
-- Table structure for msg_message_user_record
-- ----------------------------
DROP TABLE IF EXISTS `msg_message_user_record`;
CREATE TABLE `msg_message_user_record` (
  `msg_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息记录id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `msg_abstract` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '摘要',
  `msg_content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `busi_state` smallint DEFAULT NULL COMMENT '三方业务状态',
  `busi_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '三方业务id',
  `icon_type` smallint DEFAULT NULL COMMENT '图标类型：0-系统自带,1-自定义',
  `icon` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容',
  `sender_user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人姓名',
  `sender_user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人',
  `other_info` json DEFAULT NULL COMMENT '三方业务其他信息',
  `send_time` datetime DEFAULT NULL COMMENT '发布时间',
  `read_status` smallint NOT NULL DEFAULT '0' COMMENT '阅读状态:0-未阅读,1-阅读。默认0',
  `user_id` varchar(32) NOT NULL COMMENT '消息用户id',
  `msg_type` smallint DEFAULT NULL COMMENT '消息类型:0-系统公告,1-代办事项',
  `page_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '页面url',
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
  PRIMARY KEY (`msg_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_read_status` (`read_status`),
  KEY `Index_user_id` (`user_id`),
  KEY `Index_msg_type` (`msg_type`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户消息记录';

SET FOREIGN_KEY_CHECKS = 1;
