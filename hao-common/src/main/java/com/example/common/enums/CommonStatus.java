package com.example.common.enums;

public enum CommonStatus implements BasicEnum{

    OK("0", "正常"), DISABLE("1", "停用");

    private final String code;

    private final String info;

    CommonStatus(String code, String info) {
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
