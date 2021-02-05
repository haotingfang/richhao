package com.example.common.enums;

public enum Status implements BasicEnum {

    EXIST("0", "正常"), DELETE("2", "停用");

    private final String code;

    private final String info;

    Status(String code, String info) {
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
