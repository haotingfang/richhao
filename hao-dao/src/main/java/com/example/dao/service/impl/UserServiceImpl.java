package com.example.dao.service.impl;

import com.example.common.core.domain.entity.UserInfo;
import com.example.dao.mapper.UserInfoMapper;
import com.example.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectUserByUserName(String userName) {
        UserInfo userInfo = userInfoMapper.selectUserByUserName(userName);
        return userInfo;
    }

}
