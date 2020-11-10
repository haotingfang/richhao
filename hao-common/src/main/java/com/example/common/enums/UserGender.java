package com.example.common.enums;

public enum UserGender implements BasicEnum{
    MAN("1", "男"), WOMAN("2", "女");

    private final String code;

    private final String info;

    UserGender(String code, String info) {
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
