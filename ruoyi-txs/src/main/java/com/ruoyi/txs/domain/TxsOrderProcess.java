package com.ruoyi.txs.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单流程表 txs_order_process
 *
 * @author: wangpeng
 * @date: 2022年1月24日 上午10:25:09
 */
@Getter
@Setter
public class TxsOrderProcess extends BaseEntity {

    private static final long serialVersionUID = -2791639219352076906L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 流程ID
     */
    private Long processId;

    /**
     * 预约时间(单位：天)
     * 上一个节点完成后，预约的下一个流程的天数
     */
    private int appointDays;

    /**
     * 流程状态：0-等待通知；1-已通知客户；2-进行中；3-已完成
     */
    private int status;
}
