package com.example.common.utils;

import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.domain.vo.UserInfoVo;
import com.example.common.enums.UserType;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;

public class ConvertUtils {

    public static UserInfoVo convertUserInfo(UserInfo userInfo){
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        userInfoVo.setUserTypeText(EnumUtil.getByCode(String.valueOf(userInfo.getUserType()), UserType.class));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userInfoVo.setCreateTime(userInfo.getCreateTime());
        return userInfoVo;
    }
}
