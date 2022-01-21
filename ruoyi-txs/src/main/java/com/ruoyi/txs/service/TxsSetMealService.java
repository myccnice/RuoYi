package com.ruoyi.txs.service;

import java.util.List;

import com.ruoyi.txs.domain.TxsSetMeal;

/**
 * 套餐管理业务层
 *
 * @author: wangpeng
 * @date: 2022年1月20日 下午1:45:57
 */
public interface TxsSetMealService {

    /**
     * 根据条件分页查询套餐列表
     * 
     * @param param 查询参数
     * @return 套餐列表信息
     */
    List<TxsSetMeal> selectList(TxsSetMeal param);

    TxsSetMeal selectById(Long id);

    int deleteById(Long id);

    int deleteByIds(String ids);

    int update(TxsSetMeal param);

    int insert(TxsSetMeal param);

    /**
     * 校验套餐名称是否唯一
     * 
     * @param param 套餐参数
     * @return 结果
     */
    String checkUnique(TxsSetMeal param);
}
