package com.ruoyi.txs.mapper;

import java.util.List;

import com.ruoyi.txs.domain.TxsOrder;

/**
 * 订单DAO
 *
 * @author: wangpeng
 * @date: 2022年1月27日 下午5:46:42
 */
public interface OrderDao {

    List<TxsOrder> selectList(TxsOrder param);

    TxsOrder selectById(Long id);

    int update(TxsOrder param);

    int insert(TxsOrder param);

    /**
     * 统计当天订单数量
     */
    int countToday(String date);

    /**
     * 查询还未拍摄的订单
     */
    List<TxsOrder> queryNotPhotograph();

    /**
     * 查询未选片的订单
     */
    List<TxsOrder> queryNotChoosePhoto();
}
