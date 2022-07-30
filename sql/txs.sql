-- 初始化系统左侧导航菜单
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `is_refresh`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (
1062, '套餐管理', 3, 1, '/txs/setmeal', '', 'C', '0', '1', 'system:setmeal:view', 'fa fa-user-o', 'admin', '2022-01-15 11:15:48', '', NULL, '套餐管理菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `is_refresh`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (
1063, '客户管理', 3, 2, '/txs/customer', '', 'C', '0', '1', 'system:customer:view', 'fa fa-user-o', 'admin', '2022-01-15 11:15:48', '', NULL, '客户管理菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `is_refresh`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (
1064, '流程管理', 3, 2, '/txs/process', '', 'C', '0', '1', 'system:process:view', 'fa fa-user-o', 'admin', '2022-01-15 11:15:48', '', NULL, '流程管理菜单');

-- ----------------------------
-- 订单套餐表
-- ----------------------------
DROP TABLE IF EXISTS `txs_set_meal`;
CREATE TABLE `txs_set_meal`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '套餐名称',
  `status` tinyint(1) NOT NULL COMMENT '套餐状态（0正常 1停用）',
  `price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL COMMENT '套餐价格',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '套餐备注说明',
  `createBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 客户信息表
-- ----------------------------
DROP TABLE IF EXISTS `txs_customer`;
CREATE TABLE `txs_customer`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fullName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` tinyint(1) NOT NULL COMMENT '性别',
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `createBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最近修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 订单信息表
-- ----------------------------
CREATE TABLE `txs_order`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `orderNo` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `customerId` bigint(20) UNSIGNED NOT NULL COMMENT '客户编号',
  `payTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '付款时间',
  `payType` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '支付方式',
  `payeeName` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '收款人',
  `setMealId` bigint(20) NOT NULL COMMENT '套餐编号',
  `photographTime` timestamp NULL DEFAULT NULL COMMENT '拍摄时间',
  `choosePhoto` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否选片',
  `laterConsumption` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '后期消费',
  `receiveFinishedProduct` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否领取成品',
  `payAmount` decimal(10, 3) UNSIGNED NOT NULL COMMENT '订单金额',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '套餐备注说明',
  `createBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 2 COMMENT '是否删除：1是；2否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_orderNo`(`orderNo`) USING BTREE COMMENT '订单编号索引'
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 订单流程表
-- ----------------------------
DROP TABLE IF EXISTS `txs_process`;
CREATE TABLE `txs_process`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '流程名称',
  `days` int UNSIGNED NULL DEFAULT 0 COMMENT '预约天数',
  `deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0正常 1删除）是',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '套餐备注说明',
  `createBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单流程表' ROW_FORMAT = DYNAMIC;

