package com.ruoyi.txs.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 客户信息表 txs_customer
 * @author: wangpeng
 * @date: 2022年1月18日 下午3:48:33
 */
@Getter
@Setter
public class TxsCustomer extends BaseEntity {

    private static final long serialVersionUID = -5415050268227398912L;

    /** 客户姓名 */
    @Excel(name = "姓名")
    private String fullName;

    /** 用户性别 */
    @Excel(name = "客户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;
}
