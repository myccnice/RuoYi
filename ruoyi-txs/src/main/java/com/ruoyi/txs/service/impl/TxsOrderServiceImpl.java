package com.ruoyi.txs.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.constant.dic.Whether;
import com.ruoyi.txs.domain.TxsCustomer;
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

    private static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd");

    @Override
    @Transactional
    public int insert(TxsOrder param) {
        param.setOrderNo(bulidOrderNo());
        wrapCustomer(param);
        setDefaultValue(param);
        return orderDao.insert(param);
    }

    private String bulidOrderNo() {
        String date = YYYY_MM_DD.format(new Date());
        int countToday = orderDao.countToday(date);
        int sequenceNum = countToday + 1;
        return date + "_" + String.format("%03d", sequenceNum); 
    }

    private void wrapCustomer(TxsOrder order) {
        TxsCustomer param = new TxsCustomer();
        param.setPhoneNumber(order.getCustomerPhone().trim());
        param.setFullName(order.getCustomerName().trim());
        List<TxsCustomer> customers = txsCustomerService.selectList(param);
        if (customers == null || customers.isEmpty()) {
            param.setFullName(order.getCustomerName().trim());
            param.setPhoneNumber(order.getCustomerPhone().trim());
            param.setSex("2");
            param.setCreateBy(order.getCreateBy());
            param.setCreateTime(new Date());
            param.setUpdateBy(order.getCreateBy());
            param.setUpdateTime(new Date());
            txsCustomerService.insert(param);
            order.setCustomerId(param.getId());
        } else {
            TxsCustomer txsCustomer = customers.get(0);
            order.setCustomerId(txsCustomer.getId());
        }
    }

    private void setDefaultValue(TxsOrder order) {
        order.setChoosePhoto(Whether.NO);
        order.setReceiveFinishedProduct(Whether.NO);
    }

    @Override
    public List<TxsOrder> selectList(TxsOrder param) {
        List<TxsOrder> orders = orderDao.selectList(param);
        // 设置客户姓名
        txsCustomerService.wrapForOrder(orders);
        // 设置套餐名称
        txsSetMealService.wrapForOrder(orders);
        // 设置序号
        for (int i = 0; i < orders.size(); i++) {
            TxsOrder txsOrder = orders.get(i);
            txsOrder.setSerialNumber(i + 1L);
        }
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
