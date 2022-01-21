package com.ruoyi.txs.mapper;


import java.util.List;

import com.ruoyi.txs.domain.TxsSetMeal;

/**
 * 套餐DAO
 *
 * @author: wangpeng
 * @date: 2022年1月20日 下午1:09:01
 */
public interface SetMealMapper {
    /**
     * 根据条件分页查询套餐列表
     * 
     * @param param 查询参数
     * @return 套餐列表信息
     */
    List<TxsSetMeal> selectList(TxsSetMeal param);

    TxsSetMeal selectById(Long id);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int update(TxsSetMeal param);

    int insert(TxsSetMeal param);

    /**
     * 校验套餐名称是否唯一
     * 
     * @param name 套餐名称
     * @return 结果
     */
    TxsSetMeal checkUnique(String name);
}
