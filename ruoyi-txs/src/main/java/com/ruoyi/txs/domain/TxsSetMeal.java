package com.ruoyi.txs.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 套餐表 txs_set_meal
 * @author: wangpeng
 * @date: 2022年1月20日 上午10:51:40
 */
@Getter
@Setter
public class TxsSetMeal extends BaseEntity {

    private static final long serialVersionUID = -2727156488882617729L;

    /**
     * 套餐名称
     */
    @Excel(name = "套餐名称")
    private String name;

    /**
     * 套餐状态（0正常 1停用）
     */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;
}
