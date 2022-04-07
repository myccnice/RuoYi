package com.ruoyi.txs.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.CollectionUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.txs.domain.TxsOrder;
import com.ruoyi.txs.domain.TxsSetMeal;
import com.ruoyi.txs.mapper.SetMealDao;
import com.ruoyi.txs.service.TxsOrderService;
import com.ruoyi.txs.service.TxsSetMealService;

/**
 * 套餐管理业务层实现类
 *
 * @author: wangpeng
 * @date: 2022年1月20日 下午1:56:35
 */
@Service
public class TxsSetMealServiceImpl implements TxsSetMealService {

    @Autowired
    private SetMealDao setMealMapper;
    @Autowired
    private TxsOrderService orderService;

    @Override
    public List<TxsSetMeal> selectList(TxsSetMeal param) {
        return setMealMapper.selectList(param);
    }

    @Override
    public TxsSetMeal selectById(Long id) {
        return setMealMapper.selectById(id);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        checkBeforeDelete(Arrays.asList(id));
        return setMealMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(String ids) {
        Long[] idArr = Convert.toLongArray(ids);
        checkBeforeDelete(Arrays.asList(idArr));
        return setMealMapper.deleteByIds(idArr);
    }

    @Override
    @Transactional
    public int update(TxsSetMeal param) {
        param.setUpdateTime(new Date());
        return setMealMapper.update(param);
    }

    @Override
    @Transactional
    public int insert(TxsSetMeal param) {
        param.setUpdateTime(new Date());
        param.setCreateTime(new Date());
        return setMealMapper.insert(param);
    }

    @Override
    public String checkUnique(TxsSetMeal param) {
        TxsSetMeal info = setMealMapper.checkUnique(param.getName());
        if (StringUtils.isNotNull(info) && !info.getId().equals(param.getId())) {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        return UserConstants.POST_NAME_UNIQUE;
    }

    @Override
    public void wrapForOrder(List<TxsOrder> orders) {
        if (CollectionUtil.isEmpty(orders)) {
            return;
        }
        List<Long> customerIdList = CollectionUtil.getList(orders, TxsOrder::getSetMealId);
        if (CollectionUtil.isEmpty(customerIdList)) {
            return;
        }
        TxsSetMeal param = new TxsSetMeal();
        param.setIdList(customerIdList);
        List<TxsSetMeal> customerList = selectList(param);
        if (CollectionUtil.isEmpty(customerList)) {
            return;
        }
        Map<Long, TxsSetMeal> customerMap = CollectionUtil.toMap(customerList);
        for (TxsOrder order : orders) {
            TxsSetMeal tsm = customerMap.get(order.getCustomerId());
            if (tsm != null) {
                order.setSetMealName(tsm.getName());
            }
        }
    }

    @Override
    public List<TxsSetMeal> getEnabledList(Long id) {
        TxsSetMeal param = new TxsSetMeal();
        param.setStatus("0");
        List<TxsSetMeal> list = selectList(param);
        if (id != null) {
            List<Long> idList = CollectionUtil.getIdList(list);
            if (!idList.contains(id)) {
                TxsSetMeal one = selectById(id);
                if (one != null) {
                    list.add(one);
                }
            }
        }
        return list;
    }

    private void checkBeforeDelete(List<Long> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return;
        }
        TxsOrder param = new TxsOrder();
        param.setSetMealIdList(idList);
        List<TxsOrder> orders = orderService.selectList(param);
        if (CollectionUtil.isEmpty(orders)) {
            return;
        }
        List<Long> orderSmIdList = new ArrayList<>(idList.size());
        for (TxsOrder order : orders) {
            Long setMealId = order.getSetMealId();
            if (!orderSmIdList.contains(setMealId)) {
                orderSmIdList.add(setMealId);
            }
        }
        TxsSetMeal tsm = new TxsSetMeal();
        tsm.setIdList(orderSmIdList);
        if (CollectionUtil.isEmpty(selectList(tsm))) {
            return;
        }
        throw new RuntimeException("有订单关联了套餐不能删除");
    }
}
