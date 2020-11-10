package com.example.common.core.domain.vo;

import com.example.common.core.domain.entity.UserInfo;
import lombok.Data;

@Data
public class WxLoginInfo {

    private String code;

    private UserInfo userInfo;

}
