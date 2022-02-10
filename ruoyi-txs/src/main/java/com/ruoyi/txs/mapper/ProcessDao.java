package com.ruoyi.txs.mapper;

import java.util.List;

import com.ruoyi.txs.domain.TxsProcess;

/**
 * 客户DAO
 *
 * @author: wangpeng
 * @date: 2022年1月20日 下午1:09:01
 */
public interface ProcessDao {
    /**
     * 根据条件分页查询套餐列表
     * 
     * @param param 查询参数
     * @return 套餐列表信息
     */
    List<TxsProcess> selectList(TxsProcess param);

    TxsProcess selectById(Long id);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int update(TxsProcess param);

    int insert(TxsProcess param);

    /**
     * 校验流程名称是否唯一
     * 
     * @param name 流程名称
     * @return 结果
     */
    TxsProcess checkUnique(String name);
}
