package com.example.common.utils;

import com.example.common.core.domain.BaseEntity;

import java.util.Date;

public class BeanUtils {

    public static <T extends BaseEntity> void addBuildBean(T t){
        Date now = new Date();
        t.setCreateTime(now);
        t.setUpdateTime(now);
        t.setCreateBy(SecurityUtils.getUserName());
        t.setUpdateBy(SecurityUtils.getUserName());
    }

    public static <T extends BaseEntity> void editBuildBean(T t){
        t.setUpdateBy(SecurityUtils.getUserName());
    }


}
