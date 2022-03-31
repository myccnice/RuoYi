package com.ruoyi.txs.mapper;

import java.util.List;

import com.ruoyi.txs.domain.TxsCustomer;

/**
 * 客户DAO
 *
 * @author: wangpeng
 * @date: 2022年1月20日 下午1:09:01
 */
public interface CustomerDao {
    /**
     * 根据条件分页查询套餐列表
     * 
     * @param param 查询参数
     * @return 套餐列表信息
     */
    List<TxsCustomer> selectList(TxsCustomer param);

    TxsCustomer selectById(Long id);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int update(TxsCustomer param);

    Long insert(TxsCustomer param);

    /**
     * 校验电话号码是否唯一
     * 
     * @param phoneNumber 电话号码
     * @return 结果
     */
    TxsCustomer checkUnique(String phoneNumber);
}
