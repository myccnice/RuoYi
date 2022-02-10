package com.ruoyi.txs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.txs.domain.TxsOrder;
import com.ruoyi.txs.domain.TxsOrderProcess;
import com.ruoyi.txs.domain.TxsProcess;
import com.ruoyi.txs.mapper.OrderDao;
import com.ruoyi.txs.mapper.OrderProcessDao;
import com.ruoyi.txs.service.TxsOrderService;
import com.ruoyi.txs.service.TxsProcessService;

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
    private OrderProcessDao orderProcessDao;
    @Autowired
    private TxsProcessService processService;

    @Override
    @Transactional
    public long insert(TxsOrder param) {
        param.setOrderNo(bulidOrderNo());
        param.setCurrentProcess(buildOrderProcess(param));
        param.setStatus(1);
        return orderDao.insert(param);
    }

    private String bulidOrderNo() {
        String date = DateUtils.getDate();
        int countToday = orderDao.countToday(date);
        int sequenceNum = countToday + 1;
        return date + "_" + String.format("%03d", sequenceNum); 
    }
    
    private Long buildOrderProcess(TxsOrder order) {
        TxsOrderProcess top = new TxsOrderProcess();
        top.setOrderNo(order.getOrderNo());
        top.setProcessId(order.getCurrentProcess());
        TxsProcess process = processService.selectById(order.getCurrentProcess());
        if (process != null && process.getDays() > 0) {
            top.setAppointDays(process.getDays());
        }
        top.setStatus(1);
        return orderProcessDao.insert(top);
    }
}
