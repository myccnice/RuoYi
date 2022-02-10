package com.ruoyi.txs.service;

import com.ruoyi.txs.domain.TxsOrder;

/**
 * 订单管理业务层
 *
 * @author: wangpeng
 * @date: 2022年2月10日 下午5:19:29
 */
public interface TxsOrderService {

    /**
     * 新增订单
     * @param param 订单参数
     * @return  成功数量
     */
    long insert(TxsOrder param);
}
