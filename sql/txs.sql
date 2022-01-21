INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `is_refresh`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (
1062, '套餐管理', 1, 1, '/txs/setmeal', '', 'C', '0', '1', 'system:setmeal:view', 'fa fa-user-o', 'admin', '2022-01-15 11:15:48', '', NULL, '套餐管理菜单');


-- ----------------------------
-- Table structure for txs_set_meal
-- ----------------------------
DROP TABLE IF EXISTS `txs_set_meal`;
CREATE TABLE `txs_set_meal`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '套餐名称',
  `status` tinyint(1) NOT NULL COMMENT '套餐状态（0正常 1停用）',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '套餐备注说明',
  `createBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐表' ROW_FORMAT = Dynamic;
