package com.example.common.enums;

public enum DelFlag implements BasicEnum {

    EXIST("0", "存在"), DELETE("2", "删除");

    private final String code;

    private final String info;

    DelFlag(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
