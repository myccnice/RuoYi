package com.ruoyi.txs.service;

import java.util.List;

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
    int insert(TxsOrder param);

    List<TxsOrder> selectList(TxsOrder param);

    TxsOrder selectById(Long id);

    int update(TxsOrder param);

    List<TxsOrder> queryNotPhotograph();

    List<TxsOrder> queryNotChoosePhoto();

    List<TxsOrder> notFinishedOrderList();
}
