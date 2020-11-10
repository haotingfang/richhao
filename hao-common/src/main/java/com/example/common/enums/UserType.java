package com.example.common.enums;

import com.example.common.core.domain.entity.UserInfo;
import lombok.Getter;

public enum UserType implements BasicEnum  {
   /* 0：管理员  1：店长 ；2：教练 ；3：普通会员 4：VIP会员*/
    ADMIN("0", "管理员"),
    STORE_MANAGER("1","店长"),
    TEACHER("2", "教练"),
    ORDINARY_MEMBER("3", "普通会员"),
    VIP_MEMBER("4","VIP会员");

    private final String code;

    private final String info;

    UserType(String code, String info) {
        this.code = code;
        this.info = info;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getInfo() {
        return info;
    }
}
