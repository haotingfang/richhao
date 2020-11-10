package com.example.common.utils;

import com.example.common.enums.BasicEnum;

public class EnumUtil {

    public static <T extends BasicEnum> String getByCode(String code, Class<T> t){
        for(T item: t.getEnumConstants()){
            if(item.getCode().equals(code)){
                return item.getInfo();
            }
        }
        return "";
    }
}
