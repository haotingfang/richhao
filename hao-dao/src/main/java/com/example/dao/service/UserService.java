package com.example.dao.service;

import com.example.common.core.domain.entity.UserInfo;

public interface UserService {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public UserInfo selectUserByUserName(String userName);
}
