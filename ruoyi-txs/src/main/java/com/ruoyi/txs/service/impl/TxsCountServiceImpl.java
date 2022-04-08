package com.ruoyi.txs.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.dic.Whether;
import com.ruoyi.common.utils.CollectionUtil;
import com.ruoyi.txs.domain.TxsOrder;
import com.ruoyi.txs.service.TxsCountService;
import com.ruoyi.txs.service.TxsCustomerService;
import com.ruoyi.txs.service.TxsOrderService;
import com.ruoyi.txs.service.TxsSetMealService;

/**
 * 统计服务实现类
 *
 * @author: wangpeng
 * @date: 2022年4月8日 下午3:15:15
 */
@Service
public class TxsCountServiceImpl implements TxsCountService {

    @Autowired
    private TxsOrderService orderService;
    @Autowired
    private TxsCustomerService customerService;
    @Autowired
    private TxsSetMealService setMealService;

    @Override
    public List<TxsOrder> notPhotographOrderList() {
        List<TxsOrder> orders = orderService.queryNotPhotograph();
        if (CollectionUtil.isEmpty(orders)) {
            return Collections.emptyList();
        }
        // 设置客户姓名
        customerService.wrapForOrder(orders);
        // 设置套餐名称
        setMealService.wrapForOrder(orders);
        // 设置序号
        for (int i = 0; i < orders.size(); i++) {
            TxsOrder txsOrder = orders.get(i);
            txsOrder.setSerialNumber(i + 1L);
        }
        return orders;
    }

    @Override
    public List<TxsOrder> notChoosePhotoOrderList() {
        List<TxsOrder> orders = orderService.queryNotChoosePhoto();
        if (CollectionUtil.isEmpty(orders)) {
            return Collections.emptyList();
        }
        // 设置客户姓名
        customerService.wrapForOrder(orders);
        // 设置套餐名称
        setMealService.wrapForOrder(orders);
        // 设置序号
        for (int i = 0; i < orders.size(); i++) {
            TxsOrder txsOrder = orders.get(i);
            txsOrder.setSerialNumber(i + 1L);
        }
        return orders;
    }

    @Override
    public List<TxsOrder> notFinishedOrderList() {
        TxsOrder param = new TxsOrder();
        param.setChoosePhoto(Whether.YES);
        param.setReceiveFinishedProduct(Whether.NO);
        return orderService.selectList(param);
    }

}
