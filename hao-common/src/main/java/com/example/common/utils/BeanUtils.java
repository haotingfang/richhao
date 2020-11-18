package com.example.common.utils;

import com.example.common.core.domain.BaseEntity;

public class BeanUtils {

    public static <T extends BaseEntity> void addBuildBean(T t){
        t.setCreateBy(SecurityUtils.getUserName());
        t.setUpdateBy(SecurityUtils.getUserName());
    }

    public static <T extends BaseEntity> void editBuildBean(T t){
        t.setUpdateBy(SecurityUtils.getUserName());
    }


}
