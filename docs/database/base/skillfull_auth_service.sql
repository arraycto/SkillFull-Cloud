/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_122.51.135.94
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 122.51.135.94:3306
 Source Schema         : skillfull_auth_service

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 15/03/2021 10:55:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_common_data_map
-- ----------------------------
DROP TABLE IF EXISTS `auth_common_data_map`;
CREATE TABLE `auth_common_data_map` (
  `data_map_id` varchar(32) NOT NULL COMMENT '数据映射id',
  `map_original_type` smallint NOT NULL COMMENT '映射原类型:1-系统数据，2-区域数据，3-组织数据，4-用户组数据，5-个人数据',
  `original_id` varchar(32) NOT NULL COMMENT '映射原类型id',
  `target_map_type` smallint DEFAULT NULL COMMENT '映射目标类型:1-系统数据，2-区域数据，3-组织数据，4-用户组数据，5-个人数据',
  `target_id` varchar(32) DEFAULT NULL COMMENT '映射目标类型id',
  `operation_type` smallint NOT NULL COMMENT '数据操作类型:1.公有话，2.私有化，3.目标映射',
  `permission_data_rule_mutex` smallint NOT NULL COMMENT '权限添置规则互斥：0.取消填制规则,1. 按原按钮填值规则，默认1',
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
  PRIMARY KEY (`data_map_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据映射表';

-- ----------------------------
-- Records of auth_common_data_map
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_common_user_agent
-- ----------------------------
DROP TABLE IF EXISTS `auth_common_user_agent`;
CREATE TABLE `auth_common_user_agent` (
  `agent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代理id',
  `user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名id',
  `agent_user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代理人用户id',
  `agent_start_time` datetime DEFAULT NULL COMMENT '代理开始时间',
  `agent_end_time` datetime DEFAULT NULL COMMENT '代理结束时间',
  `agent_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态：0-无效，1-有效',
  `index_help` varchar(32) NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
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
  PRIMARY KEY (`agent_id`),
  UNIQUE KEY `unique_agent` (`user_id`,`agent_user_id`,`index_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户-代理人表';

-- ----------------------------
-- Records of auth_common_user_agent
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_common_user_identity
-- ----------------------------
DROP TABLE IF EXISTS `auth_common_user_identity`;
CREATE TABLE `auth_common_user_identity` (
  `identity_id` varchar(32) NOT NULL COMMENT '实名信息id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `real_name` varchar(64) NOT NULL COMMENT '真实姓名',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别:0-默认未知,1-男,2-女,默认0',
  `nationality` varchar(32) DEFAULT NULL COMMENT '名族',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证件号码',
  `id_card_issue` varchar(256) DEFAULT NULL COMMENT '身份证件发证机关',
  `id_card_effective` datetime DEFAULT NULL COMMENT '身份证书有效期开始',
  `id_card_effective_end` datetime DEFAULT NULL COMMENT '身份证有效期结束',
  `positive_photo` varchar(256) DEFAULT NULL COMMENT '正面照',
  `back_photo` varchar(256) DEFAULT NULL COMMENT '反面照',
  `handheld_photo` varchar(256) DEFAULT NULL COMMENT '证件手持照',
  `identity_status` smallint NOT NULL DEFAULT '0' COMMENT '实名状态:0-待审核,1-审核中，2-无效(审核失败)，3-有效(审核成功),默认0',
  `audit_start_time` datetime DEFAULT NULL COMMENT '审核开始时间',
  `audit_end_time` datetime DEFAULT NULL COMMENT '审核结束时间',
  `bank_card_positive` varchar(256) DEFAULT NULL COMMENT '银行卡正面',
  `bank_card_back` varchar(256) DEFAULT NULL COMMENT '银行卡反面',
  `bank_card_num` varchar(32) DEFAULT NULL COMMENT '银行卡号',
  `bank_reserve_phone` varchar(32) DEFAULT NULL COMMENT '银行预留手机号码',
  `belong_area` varchar(256) DEFAULT NULL COMMENT '银行卡归属地',
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
  PRIMARY KEY (`identity_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='实名信息表';

-- ----------------------------
-- Records of auth_common_user_identity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_correlate_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_correlate_role`;
CREATE TABLE `auth_rbac_correlate_role` (
  `correlate_role_id` varchar(32) NOT NULL COMMENT '角色关联关系id',
  `correlate_id` varchar(32) NOT NULL COMMENT '关联id',
  `correlate_type` smallint NOT NULL COMMENT '关联类型：1-组织机构,2-职位,3-个人',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  PRIMARY KEY (`correlate_role_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_correlate_role` (`correlate_id`,`correlate_type`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色关联关系表';

-- ----------------------------
-- Records of auth_rbac_correlate_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_correlate_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_correlate_user`;
CREATE TABLE `auth_rbac_correlate_user` (
  `correlate_user_id` varchar(32) NOT NULL COMMENT '用户关联关系id',
  `correlate_id` varchar(32) NOT NULL COMMENT '关联id',
  `correlate_type` smallint NOT NULL COMMENT '关联类型：1-组织机构,2-职位',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
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
  PRIMARY KEY (`correlate_user_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_correlate_role` (`correlate_id`,`correlate_type`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户关联关系表';

-- ----------------------------
-- Records of auth_rbac_correlate_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_org
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_org`;
CREATE TABLE `auth_rbac_org` (
  `org_id` varchar(32) NOT NULL COMMENT '组织id',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级组织id',
  `org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组织名称',
  `org_name_en` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '英文名',
  `org_name_abbr` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '缩写',
  `org_order` int DEFAULT '0' COMMENT '排序',
  `org_type` smallint DEFAULT NULL COMMENT '组织机构类型：1-公司,2-部门',
  `org_code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织编码',
  `org_sys_code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织编码(系统)',
  `org_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '组织状态：0-无效，1-有效，默认0',
  `auto_bind` tinyint(1) NOT NULL DEFAULT '0' COMMENT '绑定方式:0-手动,1-自动。默认0',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `fax` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '传真',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `unique_help` varchar(32) NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
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
  PRIMARY KEY (`org_id`),
  UNIQUE KEY `Unique_code` (`org_sys_code`,`unique_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`),
  KEY `Unique_org_sys_code` (`org_sys_code`,`unique_help`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组织表';

-- ----------------------------
-- Records of auth_rbac_org
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_permission`;
CREATE TABLE `auth_rbac_permission` (
  `permission_id` varchar(32) NOT NULL COMMENT '权限id',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端组件',
  `path_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路由名称',
  `redirect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '重定向地址',
  `permission_type` tinyint NOT NULL COMMENT '权限类型(0:目录; 1:菜单:2:按钮)',
  `permission_sys_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '系统内置编码(系统自动生成)',
  `actions_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按钮权限后端对应uri(相对地址,check_action_request为true时必填)',
  `actions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按钮权限编码，例如：“sys:schedule:list”,多个逗号隔开',
  `is_external` tinyint(1) DEFAULT '0' COMMENT '是否外部链接，1-是，0-不是，默认不是(前端路由为boolean类型)',
  `external_url` varchar(256) DEFAULT '0' COMMENT '外部链接地址',
  `open_type` tinyint(1) DEFAULT '1' COMMENT '外部链接打开方式:1-新页面,2-当前页面,。默认1',
  `button_strategy` tinyint(1) DEFAULT NULL COMMENT '按钮校验策略：1-显示控制(未授权时前端按钮不显示)，2-编辑控制(未授权时前端按钮显示但后端数据不可操作)',
  `check_action_request` tinyint(1) DEFAULT NULL COMMENT '按钮权限是否校验后端，1-校验,0-不校验(前端路由为boolean类型,当校验时actions_uri必填)',
  `sort_no` int NOT NULL DEFAULT '0' COMMENT '排序，值越小越靠前,默认0',
  `icon_type` tinyint(1) DEFAULT '0' COMMENT '图标类型:0-系统自带,1-自定义。默认0',
  `active_menu` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指定侧边栏高亮路由，设置后点击当前路由侧边栏会高亮制定的路由',
  `icon` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `always_show` tinyint(1) DEFAULT '0' COMMENT '显示为根路由:0-否1-是,默认0,设置后会一直以根路由形式显示(前端路由为boolean类型)',
  `hidden` tinyint(1) DEFAULT '0' COMMENT '隐藏路由:0-隐藏,1-不隐藏,默认0,隐藏后侧边栏不显示(前端路由为boolean类型)',
  `permission_status` tinyint(1) DEFAULT '0' COMMENT '权限状态:0-无效，1-有效,默认0',
  `keep_alive` tinyint(1) DEFAULT NULL COMMENT '缓存路由:1-缓存,0不缓存,前端路由为boolean类型)',
  `breadcrumb` tinyint(1) DEFAULT '1' COMMENT '面包屑中显示:0-不显示,1-显示,默认1,(前端路由为boolean类型)',
  `affix` tinyint(1) DEFAULT '0' COMMENT '是否tags中固定:0-不固定,1-固定,默认0,(前端路由为boolean类型)',
  `enable_delete` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可删除:0-不可删除,1-可删除。默认1(用户系统内置数据不可删除)',
  `unique_help` varchar(32) NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
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
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `Unique_permission_sys_code` (`permission_sys_code`,`unique_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_permission_status` (`permission_status`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of auth_rbac_permission
-- ----------------------------
BEGIN;
INSERT INTO `auth_rbac_permission` VALUES ('1350118557196271616', '', '权限管理', '/rbac', 'BasicLayout', 'rbac', '/rbac/permission', 0, 'A002', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, 'el-icon-_vercode', 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350123000109252608', '1350118557196271616', '菜单管理', '/rbac/permission', '/auth/rbac/PermissionList', 'rbac-permission', NULL, 1, 'A002A001', NULL, NULL, 0, '0', 1, 1, 0, 3, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-01-29 01:30:18', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350123560124334080', '', '存储管理', '/storage', 'BasicLayout', 'storage', '/storage/local', 0, 'A009', NULL, NULL, 0, '0', 1, 1, 0, 4, 0, NULL, 'el-icon-folder-opened', 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350124304009314304', '1350123560124334080', '本地存储', '/storage/local', '/storage/local/LocalFileList', 'local', NULL, 1, 'A009A001', NULL, NULL, 0, '0', 1, 1, 0, 1, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350126916741939200', '1350123560124334080', '对象存储', '/storage/oss', '/storage/oss/OssFileList', 'oss', NULL, 1, 'A009A002', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350133275944337408', '1350118557196271616', '角色管理', '/rbac/role', '/auth/rbac/RoleList', 'rbac-role', NULL, 1, 'A002A002', NULL, NULL, 0, '0', 1, 1, 0, 4, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-01-29 01:30:29', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350134241062076416', '', '系统管理', '/sys', 'BasicLayout', 'sys', '/sys/dict', 0, 'A008', NULL, NULL, 0, '0', 1, 1, 0, 3, 0, NULL, 'el-icon-setting', 0, 0, 1, 1, 1, 0, 0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350134884728356864', '1350134241062076416', '数据字典', '/sys/dict', '/system/common/DictList', 'dict', NULL, 1, 'A008A001', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-02-12 21:32:56', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350137977897533440', '1350123000109252608', '添加', '', NULL, NULL, NULL, 2, 'A002A001A001', NULL, 'edit', 0, '0', 1, 1, 0, 1, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350734475513348096', '', '工具文档', '/doc', 'BasicLayout', 'doc', '/doc/swagger', 0, 'A006', NULL, NULL, 0, '0', 1, 1, 0, 10, 0, NULL, 'el-icon-document', 0, 0, 0, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-02-12 21:45:16', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350734726659883008', '1350734475513348096', '接口文档', '/swagger', '/common/iframe/index', 'swagger', NULL, 1, 'A006A001', NULL, NULL, 1, 'http://mydivisu.com/gateway/swagger-ui/index.html', 2, 1, 0, 1, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350735701634236416', '', '指示面板', '', 'BasicLayout', 'home', '/workplace', 0, 'A005', NULL, NULL, 0, '0', 1, 1, 0, 1, 0, NULL, 'el-icon-data-line', 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350736091574484992', '1350735701634236416', '工作台', '/workplace', '/dashboard/workplace', 'workplace', NULL, 1, 'A005A001', NULL, NULL, 0, '0', 1, 1, 0, 1, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350736329991307264', '1350735701634236416', '数据分析', '/analysis', '/dashboard/analysis', 'analysis', NULL, 1, 'A005A002', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1350736580051517440', '1350735701634236416', '大屏监控', '/monitor', '/dashboard/monitor', 'monitor', NULL, 1, 'A005A003', NULL, NULL, 0, '0', 1, 1, 0, 4, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351174223121924096', '', '流程管理', '/process', 'BasicLayout', 'process', '/process/design', 0, 'A003', NULL, NULL, 0, '0', 1, 1, 0, 5, 0, NULL, 'el-icon-_condition', 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-03-02 16:00:18', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351174665662939136', '1351174223121924096', '流程设计', '/process/design', '/process/design/ProcessModelList', 'design-model', NULL, 1, 'A003A001', NULL, NULL, 0, '0', 1, 1, 0, 1, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-03-03 15:37:53', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351174868428177408', '1351174223121924096', '表单管理', '/process/from', '/process/design', 'from', NULL, 1, 'A003A002', NULL, NULL, 0, '0', 1, 1, 0, 6, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-03-03 15:39:09', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351175088528474112', '1351174223121924096', '监听器管理', '/process/listem', '/process/design', 'listen', NULL, 1, 'A003A003', NULL, NULL, 0, '0', 1, 1, 0, 7, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-03-03 15:39:16', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351175283592970240', '1351174223121924096', '变量管理', '/process/variable', '/process/design', 'variable', NULL, 1, 'A003A004', NULL, NULL, 0, '0', 1, 1, 0, 5, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-03-03 15:39:05', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351175732081508352', '1350134241062076416', '区域管理', '/sys/area', '/system/common/AreaList', 'area', NULL, 1, 'A008A002', NULL, NULL, 0, '0', 1, 1, 0, 4, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-02-12 21:35:54', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351176139100962816', '1351180494554316800', '调度管理', '/scheduler', '/sdfsdf', 'scheduler', NULL, 1, 'A004A001', NULL, NULL, 0, '0', 1, 1, 0, 1, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351176363194236928', '1350134241062076416', '系统信息', '/sys/system-info', '/system/common/SystemList', 'system', NULL, 1, 'A008A003', NULL, NULL, 0, '0', 1, 1, 0, 1, 0, NULL, NULL, 0, 0, 0, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-02-12 21:43:20', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351176549551357952', '1350134241062076416', '分类字典', '/sys/category', '/system/common/CategoryList', 'category', NULL, 1, 'A008A004', NULL, NULL, 0, '0', 1, 1, 0, 3, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-02-12 21:35:05', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351177101848920064', '1350118557196271616', '职位管理', '/rbac/position', '/auth/rbac/PositionList', 'rbac-position', NULL, 1, 'A002A003', NULL, NULL, 0, '0', 1, 1, 0, 5, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-01-29 01:30:40', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351177257445015552', '1350118557196271616', '用户管理', '/rbac/user', '/auth/rbac/UserList', 'rbac-user', NULL, 1, 'A002A004', NULL, NULL, 0, '0', 1, 1, 0, 1, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-01-29 01:29:55', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351177380610752512', '1350118557196271616', '机构管理', '/rbac/org', '/auth/rbac/OrgList', 'rbac-org', NULL, 1, 'A002A005', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '000fd73785565de9ceb24a265a1f5885', 'zxiaozhou', '2021-01-29 01:30:58', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351180494554316800', '', '调度中心', '/scheduler', 'BasicLayout', 'scheduler', NULL, 0, 'A004', NULL, NULL, 0, '0', 1, 1, 0, 7, 0, NULL, 'el-icon-_api', 0, 0, 0, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-02-12 21:44:58', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351180820086833152', '1351180494554316800', '调度日志', '/schedule/log', '/schedulelog', 'schedulelog', NULL, 1, 'A004A002', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351181693852631040', '1350734475513348096', '代码生成', '/generation', 'sdfsdf', 'generation', NULL, 1, 'A006A002', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351182973329260544', '', '日志查询', 'logging', 'BasicLayout', 'logging', 'logging', 0, 'A001', NULL, NULL, 0, '0', 1, 1, 0, 9, 0, NULL, 'el-icon-_bug', 0, 0, 0, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-02-12 21:45:14', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351183099116437504', '1351182973329260544', '系统日志', 'syslog', 'sys/log', 'syslog', NULL, 1, 'A001A001', NULL, NULL, 0, '0', 1, 1, 0, 0, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351183167689113600', '1351182973329260544', '操作日志', 'sdfsfd', 'sdf', 'sdfsdfsdf', NULL, 1, 'A001A002', NULL, NULL, 0, '0', 1, 1, 0, 0, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351183324547694592', '', '消息中心', '/message', 'BasicLayout', 'message', NULL, 0, 'A007', NULL, NULL, 0, '0', 1, 1, 0, 8, 0, NULL, 'el-icon-chat-dot-square', 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351183426402172928', '1351183324547694592', '消息模板', '消息模板', '消息模板', '消息模板', NULL, 1, 'A007A001', NULL, NULL, 0, '0', 1, 1, 0, 1, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351183508627308544', '1351183324547694592', '通知公告', 'sdfsfd', 'sdfsf', 'sdfsdfdf', NULL, 1, 'A007A002', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351183637396635648', '1351183324547694592', '消息记录', 'sdfsdf', 'sdfsdf', 'sdfsdf', NULL, 1, 'A007A003', NULL, NULL, 0, '0', 1, 1, 0, 0, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351189432142184448', '1351174223121924096', '流程实例', '/process/instance', 'sdfsf', '/dsfsdf', NULL, 1, 'A003A005', NULL, NULL, 0, '0', 1, 1, 0, 2, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-03-03 15:38:03', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351189578213015552', '1351174223121924096', '历史任务', '/process/history-task', 'dfsf', 'sdfsdf', NULL, 1, 'A003A006', NULL, NULL, 0, '0', 1, 1, 0, 3, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-03-03 15:38:35', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1351189651261014016', '1351174223121924096', '历史流程', '/process/history', 'sdfsf', 'sdfsdf', NULL, 1, 'A003A007', NULL, NULL, 0, '0', 1, 1, 0, 4, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', '1355167397507022848', 'zxiaozhou', '2021-03-03 15:38:48', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1370222461468590080', '1350134241062076416', '服务管理', '/sys/service', '/system/manage/ServiceList', 'service', NULL, 1, 'A008A005', NULL, NULL, 0, '0', 1, 1, 0, 5, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-03-12 11:57:47', '1355167397507022848', 'zxiaozhou', '2021-03-12 11:57:57', 0);
INSERT INTO `auth_rbac_permission` VALUES ('1370223654903267328', '1350134241062076416', 'nacos管理', '', '/common/iframe/index', 'nacos', NULL, 1, 'A008A006', NULL, NULL, 1, 'http://192.168.56.24:8848/nacos', 1, 1, 0, 0, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-03-12 12:02:32', NULL, NULL, NULL, 0);
INSERT INTO `auth_rbac_permission` VALUES ('1370224716976537600', '1350735701634236416', '系统监控', '/system-monitor', '/common/iframe/index', 'system-monitor', NULL, 1, 'A005A004', NULL, NULL, 1, './api/monitor/applications', 2, 1, 0, 5, 0, NULL, NULL, 0, 0, 1, 1, 1, 0, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, '1355167397507022848', 'zxiaozhou', '2021-03-12 12:06:45', '1355167397507022848', 'zxiaozhou', '2021-03-12 14:32:19', 0);
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_permission_data_rule
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_permission_data_rule`;
CREATE TABLE `auth_rbac_permission_data_rule` (
  `permission_data_rule_id` varchar(32) NOT NULL COMMENT '填值规则id',
  `correlation_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联业务id:角色id或按钮权限id',
  `rule_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则名称',
  `rule_column` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字段',
  `rule_conditions` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '条件',
  `rule_value` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则值',
  `rule_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '规则状态:0-无效，1-有效。默认0',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  PRIMARY KEY (`permission_data_rule_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限数据填值规则表';

-- ----------------------------
-- Records of auth_rbac_permission_data_rule
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_position
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_position`;
CREATE TABLE `auth_rbac_position` (
  `position_id` varchar(32) NOT NULL COMMENT '职位id',
  `position_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位编码',
  `position_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位名称',
  `auto_bind` tinyint(1) NOT NULL DEFAULT '0' COMMENT '绑定方式:0-手动,1-自动。默认0',
  `position_rank` int DEFAULT NULL COMMENT '职级',
  `position_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '职位状态：0-无效，1-有效，默认0',
  `unique_help` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
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
  PRIMARY KEY (`position_id`),
  UNIQUE KEY `unique_position` (`position_code`,`unique_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_position_status` (`position_status`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='职位表';

-- ----------------------------
-- Records of auth_rbac_position
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_role`;
CREATE TABLE `auth_rbac_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_sys_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色系统编码(系统自动创建)',
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `parent_role_id` varchar(32) DEFAULT NULL COMMENT '上级角色id',
  `enable_delete` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可删除:0-不可删除,1-可删除。默认1(用户系统内置数据不可删除)',
  `auto_bind` tinyint(1) NOT NULL DEFAULT '0' COMMENT '绑定方式:0-手动,1-自动。默认0',
  `role_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '角色状态:0-禁用,1-启用,默认0',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `unique_help` varchar(32) NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
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
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `Unique_role` (`role_code`,`unique_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_role_status` (`role_status`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Unique_role_sys_code` (`role_sys_code`,`unique_help`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of auth_rbac_role
-- ----------------------------
BEGIN;
INSERT INTO `auth_rbac_role` VALUES ('1314235356968173568', '超级管理员', 'A001', 'SUPER_ROLE', NULL, 0, 0, 1, '系统最高管理员', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 05:10:13', NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_role_permission`;
CREATE TABLE `auth_rbac_role_permission` (
  `role_permission_id` varchar(32) NOT NULL COMMENT '权限角色id',
  `permission_id` varchar(32) NOT NULL COMMENT '权限id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新用户姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  `create_area_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建区域编码',
  `create_position_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建职位编码',
  `create_org_sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建机构系统编码',
  `create_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建系统编码',
  `create_tenant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建租户id',
  PRIMARY KEY (`role_permission_id`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-权限表';

-- ----------------------------
-- Records of auth_rbac_role_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_rbac_user`;
CREATE TABLE `auth_rbac_user` (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `real_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码盐',
  `short_profile` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个人简介',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别:0-默认未知,1-男,2-女,默认0',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件',
  `is_initial_password` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否初始密码:0-不是,1-是,默认1',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话号码',
  `current_org_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当前登录部门id',
  `org_ids` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '负责部门',
  `user_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态:0-未激活,1-正常,2-冻结,默认1',
  `work_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工号，唯一键',
  `telephone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '座机号',
  `login_fail_error_num` int NOT NULL DEFAULT '0' COMMENT '连续登录错误次数',
  `current_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `unique_help` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_user_name` (`user_name`,`unique_help`),
  UNIQUE KEY `unique_phone` (`phone`,`unique_help`),
  KEY `Index_del_flag` (`del_flag`),
  KEY `Index_create_time` (`create_time`),
  KEY `Index_create_user_id` (`create_user_id`),
  KEY `Index_create_area_code` (`create_area_code`),
  KEY `Index_create_position_code` (`create_position_code`),
  KEY `Index_create_org_sys_code` (`create_org_sys_code`),
  KEY `Index_create_system_code` (`create_system_code`),
  KEY `Index_create_tenant_id` (`create_tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of auth_rbac_user
-- ----------------------------
BEGIN;
INSERT INTO `auth_rbac_user` VALUES ('1355167397507022848', 'admin', 'admin', 'zxiaozhou', '$2a$10$jZseDrbZtTYAj3iBmmbkc.xrhw7eKSPENZ9A7A.TtUV4iOq2P2HUi', '372aec7db745c409099262388404ea9c', '一个有想法的农民。。。', '1367569980703948800', '1987-10-26 00:00:00', 0, NULL, 0, NULL, NULL, NULL, 1, NULL, NULL, 0, '2021-03-13 21:08:40', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-19 17:07:09', '1355167397507022848', 'zxiaozhou', '2021-03-13 21:08:40', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
