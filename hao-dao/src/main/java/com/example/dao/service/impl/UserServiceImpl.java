package com.example.dao.service.impl;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.enums.CommonStatus;
import com.example.common.enums.DelFlag;
import com.example.common.enums.UserType;
import com.example.common.utils.SecurityUtils;
import com.example.common.utils.TableDataUtils;
import com.example.dao.mapper.UserInfoMapper;
import com.example.dao.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${wx.defaultPassword}")
    private String defaultPassword;


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

    @Override
    @Transactional
    public AjaxResult edit(UserInfo userInfo){
        // 校验手机号是否已经存在
        Boolean phone_flag = userInfoMapper.checkPhoneExist(userInfo.getPhoneNumber(),userInfo.getUserId());
        if(phone_flag){
            return AjaxResult.error("修改用户'" + userInfo.getUserName() + "'失败，手机号码已存在");
        }
        //校验邮箱会否已经存在
        Boolean email_flag = userInfoMapper.checkEmailExist(userInfo.getEmail(),userInfo.getUserId());
        if(email_flag){
            return AjaxResult.error("修改用户'" + userInfo.getUserName() + "'失败，邮箱账号已存在");
        }
        //更新用户信息
        userInfoMapper.updateUserById(userInfo);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult add(UserInfo userInfo){
        // 校验手机号是否已经存在
        Boolean phone_flag = userInfoMapper.checkPhoneExist(userInfo.getPhoneNumber(),userInfo.getUserId());
        if(phone_flag){
            return AjaxResult.error("修改用户'" + userInfo.getUserName() + "'失败，手机号码已存在");
        }
        //校验邮箱会否已经存在
        Boolean email_flag = userInfoMapper.checkEmailExist(userInfo.getEmail(),userInfo.getUserId());
        if(email_flag){
            return AjaxResult.error("修改用户'" + userInfo.getUserName() + "'失败，邮箱账号已存在");
        }
        //set 默认值
        userInfo.setPassword(SecurityUtils.encryptPassword(userInfo.getPassword()));
        userInfo.setDelFlag(DelFlag.EXIST.getCode());
        //插入用户信息
        int i = userInfoMapper.addUser(userInfo);
        return AjaxResult.success();
    }

}
