package com.example.common.utils;

import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.domain.vo.UserInfoVo;
import com.example.common.enums.UserType;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ConvertUtils {

    public static UserInfoVo convertUserInfo(UserInfo userInfo){
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        userInfoVo.setUserTypeText(EnumUtil.getByCode(String.valueOf(userInfo.getUserType()), UserType.class));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userInfoVo.setCreateTime(userInfo.getCreateTime());
        return userInfoVo;
    }

    /*bean 转 map*/
    /**
     * bean转Map
     * @param <T>
     * @return
     */
    public static <T> Map<String,Object> beanToMap(T bean){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            if (bean != null){
                BeanMap beanMap = BeanMap.create(bean);
                map.putAll(beanMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
