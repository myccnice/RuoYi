package com.ruoyi.txs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.txs.domain.TxsProcess;
import com.ruoyi.txs.mapper.ProcessDao;
import com.ruoyi.txs.service.TxsProcessService;

/**
 * 流程管理业务层实现类
 *
 * @author: wangpeng
 * @date: 2022年1月24日 下午12:52:37
 */
@Service
public class TxsProcessServiceImpl implements TxsProcessService {

    private static final String SPLIT_REGEX = ",|，|\n|\r\n";

    @Autowired
    private ProcessDao dao;

    @Override
    public List<TxsProcess> selectList(TxsProcess param) {
        return dao.selectList(param);
    }

    @Override
    public TxsProcess selectById(Long id) {
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
    public int update(TxsProcess param) {
        param.setUpdateTime(new Date());
        return dao.update(param);
    }

    @Override
    @Transactional
    public int insert(TxsProcess param) {
        param.setUpdateTime(new Date());
        param.setCreateTime(new Date());
        return dao.insert(param);
    }

    @Override
    public String checkUnique(TxsProcess param) {
        TxsProcess info = dao.checkUnique(param.getName());
        if (StringUtils.isNotNull(info) && !info.getId().equals(param.getId())) {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        return UserConstants.POST_NAME_UNIQUE;
    }

    @Override
    public String checkProcesses(String names) {
        List<String> nameList = StringUtils.str2List(names, SPLIT_REGEX, true, true);
        if (nameList == null || nameList.isEmpty()) {
            return UserConstants.POST_NAME_UNIQUE;
        }
        List<TxsProcess> processList = dao.selectList(new TxsProcess());
        if (processList == null || processList.isEmpty()) {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        List<String> processNameList = processList.stream()
                .map(TxsProcess::getName).collect(Collectors.toList());
        nameList.removeAll(processNameList);
        if (nameList != null && !nameList.isEmpty()) {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        return UserConstants.POST_NAME_UNIQUE;
    }

    @Override
    public String processNameToId(String names) {
        List<String> nameList = StringUtils.str2List(names, SPLIT_REGEX, true, true);
        if (nameList == null || nameList.isEmpty()) {
            return "";
        }
        List<TxsProcess> processList = dao.selectList(new TxsProcess());
        if (processList == null || processList.isEmpty()) {
            return "";
        }
        Map<String, Long> map = processList.stream()
                .collect(Collectors.toMap(TxsProcess::getName, TxsProcess::getId));
        StringBuilder sb = new StringBuilder();
        for (String name : nameList) {
            Long id = map.get(name);
            if (id != null) {
                sb.append(id).append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
