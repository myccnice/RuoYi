package com.ruoyi.txs.service;

import java.util.List;

import com.ruoyi.txs.domain.TxsCustomer;
import com.ruoyi.txs.domain.TxsOrder;

/**
 * 客户管理业务层
 *
 * @author: wangpeng
 * @date: 2022年1月20日 下午1:45:57
 */
public interface TxsCustomerService {

    /**
     * 根据条件分页查询客户列表
     * 
     * @param param 查询参数
     * @return 客户列表信息
     */
    List<TxsCustomer> selectList(TxsCustomer param);

    TxsCustomer selectById(Long id);

    int deleteById(Long id);

    int deleteByIds(String ids);

    int update(TxsCustomer param);

    int insert(TxsCustomer param);

    /**
     * 校验客户名称是否唯一
     * 
     * @param param 客户参数
     * @return 结果
     */
    String checkUnique(TxsCustomer param);

    void wrapForOrder(List<TxsOrder> orders);
}
