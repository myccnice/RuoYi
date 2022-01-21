package com.ruoyi.txs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.txs.domain.TxsSetMeal;
import com.ruoyi.txs.mapper.SetMealMapper;
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
    private SetMealMapper setMealMapper;

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
        return setMealMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(String ids) {
        Long[] idArr = Convert.toLongArray(ids);
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
}
