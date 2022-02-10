package com.ruoyi.txs.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单信息表 txs_order
 *
 * @author: wangpeng
 * @date: 2022年1月21日 下午3:27:32
 */
@Getter
@Setter
public class TxsOrder extends BaseEntity {

    private static final long serialVersionUID = -1190807358402794154L;

    /**
     * 客户编号
     */
    private Long customerId;

    /**
     * 收款人编号
     */
    private Long payeeId;

    /**
     * 套餐编号
     */
    private Long setMealId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 支付方式
     */
    private int payType;

    /**
     * 支付金额
     */
    private double payAmount;

    /**
     * 当前流程
     */
    private Long currentProcess;
}
