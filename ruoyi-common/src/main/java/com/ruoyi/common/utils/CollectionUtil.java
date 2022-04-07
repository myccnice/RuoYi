package com.ruoyi.common.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 集合工具
 *
 * @author: wangpeng
 * @date: 2022年3月27日 下午5:59:57
 */
public class CollectionUtil {

    public static <E> boolean isEmpty(List<E> list) {
        if (list == null) {
            return true;
        }
        return list.isEmpty();
    }

    public static <E extends BaseEntity> List<Long> getIdList(List<E> list) {
        return getList(list, E::getId);
    }

    public static <E extends BaseEntity, R> List<R> getList(List<E> list, Function<E, R> mapper) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    public static <E extends BaseEntity> Map<Long, E> toMap(List<E> list) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.toMap(E::getId, Function.identity()));
    }
}
