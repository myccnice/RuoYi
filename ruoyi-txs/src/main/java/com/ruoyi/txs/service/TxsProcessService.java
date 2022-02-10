package com.ruoyi.txs.service;

import java.util.List;

import com.ruoyi.txs.domain.TxsProcess;

/**
 * 流程管理业务层
 *
 * @author: wangpeng
 * @date: 2022年1月24日 下午12:51:38
 */
public interface TxsProcessService {

    List<TxsProcess> selectList(TxsProcess param);

    TxsProcess selectById(Long id);

    int deleteById(Long id);

    int deleteByIds(String ids);

    int update(TxsProcess param);

    int insert(TxsProcess param);

    String checkUnique(TxsProcess param);

    /**
     * 根据流程名称检查流程是否存在，返回不存在的流程名称
     * @param names 流程名称
     * @return  不存在的流程名称
     */
    String checkProcesses(String names);

    String processNameToId(String names);
}
