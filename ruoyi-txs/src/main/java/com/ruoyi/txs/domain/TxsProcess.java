package com.ruoyi.txs.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 流程管理 txs_process
 *
 * @author: wangpeng
 * @date: 2022年1月24日 上午9:37:25
 */
@Getter
@Setter
public class TxsProcess extends BaseEntity {

    private static final long serialVersionUID = 7500171455667520218L;

    /**
     * 流程名称
     */
    @Excel(name = "流程名称")
    private String name;

    /**
     * 预约时间(单位：天)
     * 上一个节点完成后，预约的下一个流程的天数
     */
    @Excel(name = "预约天数", cellType = ColumnType.NUMERIC)
    private int days;
}
