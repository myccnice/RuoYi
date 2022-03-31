package com.ruoyi.txs.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
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

    @Excel(name = "序号", cellType=ColumnType.NUMERIC, width = 4)
    private Long serialNumber;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号", width = 13)
    private String orderNo;

    /**
     * 客户编号
     */
    private Long customerId;

    @Excel(name = "客户姓名", width = 4)
    private String customerName;

    @Excel(name = "客户电话", width = 12)
    private String customerPhone;

    /**
     * 付款日期
     */
    @Excel(name = "付款日期", dateFormat="yyyy-MM-dd")
    private Date payTime;

    /**
     * 支付方式
     */
    @Excel(name = "支付方式", dictType="sys_user_payment")
    private String payType;

    /**
     * 收款人
     */
    @Excel(name = "收款人", width = 4)
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
    @Excel(name = "是否选片", dictType="sys_yes_no")
    private String choosePhoto;

    /**
     * 后期消费
     */
    @Excel(name = "后期消费")
    private String laterConsumption;

    /**
     * 是否领取成品
     */
    @Excel(name = "是否领取成品", readConverterExp = "1=否,2=是", dictType="sys_yes_no")
    private String receiveFinishedProduct;

    /**
     * 订单金额
     */
    @Excel(name = "订单金额", cellType=ColumnType.NUMERIC)
    private BigDecimal payAmount;
}
