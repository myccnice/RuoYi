package com.ruoyi.txs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.txs.domain.TxsOrder;
import com.ruoyi.txs.mapper.OrderDao;
import com.ruoyi.txs.service.TxsCustomerService;
import com.ruoyi.txs.service.TxsOrderService;
import com.ruoyi.txs.service.TxsSetMealService;

/**
 * 订单管理业务层实现类
 *
 * @author: wangpeng
 * @date: 2022年2月10日 下午5:21:23
 */
@Service
public class TxsOrderServiceImpl implements TxsOrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TxsCustomerService txsCustomerService;
    @Autowired
    private TxsSetMealService txsSetMealService;

    @Override
    @Transactional
    public int insert(TxsOrder param) {
        param.setOrderNo(bulidOrderNo());
        return orderDao.insert(param);
    }

    private String bulidOrderNo() {
        String date = DateUtils.getDate();
        int countToday = orderDao.countToday(date);
        int sequenceNum = countToday + 1;
        return date + "_" + String.format("%03d", sequenceNum); 
    }

    @Override
    public List<TxsOrder> selectList(TxsOrder param) {
        List<TxsOrder> orders = orderDao.selectList(param);
        // 设置客户姓名
        txsCustomerService.wrapForOrder(orders);
        // 设置套餐名称
        txsSetMealService.wrapForOrder(orders);
        return orders;
    }

    @Override
    public TxsOrder selectById(Long id) {
        return orderDao.selectById(id);
    }

    @Override
    public int update(TxsOrder param) {
        return orderDao.update(param);
    }
}
