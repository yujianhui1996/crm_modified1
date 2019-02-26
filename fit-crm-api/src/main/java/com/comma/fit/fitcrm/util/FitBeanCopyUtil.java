package com.comma.fit.fitcrm.util;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: List的元素copy
 * User: magang
 * Date: 2019-01-16
 * Time: 11:11
 */
public class FitBeanCopyUtil {
    /**
     * 由给定List的元素通过beancopy获得新的List
     *
     * @param original
     * @param targetType
     * @return
     * @throws Exception
     */
    public static <T, V> List<T> listBeanCopy(List<V> original,
                                              Class<? extends T> targetType) throws Exception {
        if (original == null) {
            return null;
        }
        int size = original.size();
        List<T> target = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            T t = targetType.newInstance();
            BeanUtil.copyProperties(original.get(i), t);
            target.add(t);
        }
        return target;
    }

    public static <T, V> PageInfo<T> pageInfoCopy(PageInfo<V> pageInfo,
                                                  Class<? extends T> targetType) throws Exception {
        PageInfo<T> copyPageInfo = new PageInfo<>();
        BeanUtil.copyProperties(pageInfo, copyPageInfo);
        List<T> list = listBeanCopy(pageInfo.getList(), targetType);
        copyPageInfo.setList(list);
        return copyPageInfo;
    }

}
