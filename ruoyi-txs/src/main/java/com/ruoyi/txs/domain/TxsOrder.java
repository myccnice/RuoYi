package com.ruoyi.txs.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.common.annotation.Excel;
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
     * 订单编号
     */
    @Excel(name = "订单编号")
    private String orderNo;

    /**
     * 客户编号
     */
    private Long customerId;

    @Excel(name = "客户姓名")
    private String customerName;

    /**
     * 付款日期
     */
    @Excel(name = "付款日期", dateFormat="yyyy-MM-dd")
    private Date payTime;

    /**
     * 支付方式
     */
    @Excel(name = "支付方式", readConverterExp = "1=微信,2=支付宝,3=其他")
    private String payType;

    /**
     * 收款人
     */
    private Long payeeId;

    @Excel(name = "收款人")
    private String payeeName;

    /**
     * 套餐编号
     */
    private Long setMealId;

    @Excel(name = "套餐")
    private String setMealName;

    /**
     * 拍摄日期
     */
    @Excel(name = "拍摄日期", dateFormat="yyyy-MM-dd")
    private Date photographTime;

    /**
     * 是否选片：1、是；2、否
     */
    @Excel(name = "是否选片", readConverterExp = "1=否,2=是")
    private String choosePhoto;

    /**
     * 后期消费
     */
    @Excel(name = "后期消费")
    private String laterConsumption;

    /**
     * 是否领取成品
     */
    @Excel(name = "是否领取成品", readConverterExp = "1=否,2=是")
    private String receiveFinishedProduct;

    /**
     * 订单金额
     */
    @Excel(name = "订单金额")
    private BigDecimal payAmount;
}
