package com.ruoyi.txs.service;

import java.util.List;

import com.ruoyi.txs.domain.TxsOrder;

/**
 * 统计服务接口
 *
 * @author: wangpeng
 * @date: 2022年4月8日 下午3:13:50
 */
public interface TxsCountService {

    /**
     * 统计未拍摄订单列表
     */
    List<TxsOrder> notPhotographOrderList();

    /**
     * 统计未选片订单列表
     */
    List<TxsOrder> notChoosePhotoOrderList();

    /**
     * 统计未领取成品订单列表
     */
    List<TxsOrder> notFinishedOrderList();
}
