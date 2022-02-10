package com.ruoyi.txs.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 订单编号
     */
    private String orderNo;

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
     * 流程状态：1-等待通知；2-已通知客户；3-已完成
     */
    private int status;

    /**
     * 通知人编号
     */
    private Long noticeEmpId;

    /**
     * 通知时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeTime;
}
