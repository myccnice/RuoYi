package com.ruoyi.txs.mapper;

import java.util.List;

import com.ruoyi.txs.domain.TxsOrderProcess;

/**
 * 订单流程DAO
 *
 * @author: wangpeng
 * @date: 2022年2月10日 下午4:18:41
 */
public interface OrderProcessDao {

    List<TxsOrderProcess> selectList(TxsOrderProcess param);

    TxsOrderProcess selectById(Long id);

    int update(TxsOrderProcess param);

    int insert(TxsOrderProcess param);
}
