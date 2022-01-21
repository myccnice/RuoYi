package com.ruoyi.common.core.page;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 表格分页数据对象
 *
 * @author: wangpeng
 * @date: 2022年1月20日 下午2:02:32
 */
@Getter
@Setter
public class TableData<T> implements Serializable {

    private static final long serialVersionUID = 9075419349059278265L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<T> rows;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;

    /**
     * 表格数据对象
     */
    public TableData() {

    }

    /**
     * 分页
     * 
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableData(List<T> list, int total) {
        this.rows = list;
        this.total = total;
    }

    public static <T> TableData<T> getInfo(List<T> list) {
        TableData<T> rspData = new TableData<T>();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<T>(list).getTotal());
        return rspData;
    }
}