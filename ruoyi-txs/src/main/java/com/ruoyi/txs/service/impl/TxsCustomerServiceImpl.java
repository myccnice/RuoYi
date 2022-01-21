package com.ruoyi.txs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.txs.domain.TxsCustomer;
import com.ruoyi.txs.mapper.CustomerDao;
import com.ruoyi.txs.service.TxsCustomerService;

/**
 * 套餐管理业务层实现类
 *
 * @author: wangpeng
 * @date: 2022年1月21日 下午1:47:28
 */
@Service
public class TxsCustomerServiceImpl implements TxsCustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    public List<TxsCustomer> selectList(TxsCustomer param) {
        return dao.selectList(param);
    }

    @Override
    public TxsCustomer selectById(Long id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(String ids) {
        Long[] idArr = Convert.toLongArray(ids);
        return dao.deleteByIds(idArr);
    }

    @Override
    @Transactional
    public int update(TxsCustomer param) {
        param.setUpdateTime(new Date());
        return dao.update(param);
    }

    @Override
    @Transactional
    public int insert(TxsCustomer param) {
        param.setUpdateTime(new Date());
        param.setCreateTime(new Date());
        return dao.insert(param);
    }

    @Override
    public String checkUnique(TxsCustomer param) {
        TxsCustomer info = dao.checkUnique(param.getPhoneNumber());
        if (StringUtils.isNotNull(info) && !info.getId().equals(param.getId())) {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        return UserConstants.POST_NAME_UNIQUE;
    }
}
