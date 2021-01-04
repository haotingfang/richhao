package com.example.dao.service.impl;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.utils.SecurityUtils;
import com.example.common.utils.TableDataUtils;
import com.example.dao.mapper.UserInfoMapper;
import com.example.dao.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectUserByUserName(String userName) {
        UserInfo userInfo = userInfoMapper.selectUserByUserName(userName);
        return userInfo;
    }

    @Override
    public UserInfo selectUserByUserId(Long userId) {
        UserInfo userInfo = userInfoMapper.selectUserByUserId(userId);
        return userInfo;
    }

    @Override
    public TableDataInfo selectUserList(UserInfo userInfo, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> list = userInfoMapper.selectUserList(userInfo);
        TableDataInfo tableDataInfo = TableDataUtils.buildTableDataInfo(list);
        return tableDataInfo;
    }

    @Override
    @Transactional
    public int deleteUser(Long[] userIds) {
        String userName = SecurityUtils.getUserName();
        return userInfoMapper.deleteUsers(userIds,userName);
    }

    @Override
    @Transactional
    public AjaxResult updateUserStatus(UserInfo userInfo) {
        String userName = SecurityUtils.getUserName();
        userInfo.setUpdateBy(userName);
        userInfoMapper.updateUserById(userInfo);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult updateUserPassword(UserInfo userInfo) {
        String userName = SecurityUtils.getUserName();
        userInfo.setUpdateBy(userName);
        userInfoMapper.updateUserById(userInfo);
        return AjaxResult.success();
    }

}
