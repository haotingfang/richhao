package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.UserInfo;

public interface UserService {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public UserInfo selectUserByUserName(String userName);

    /**
     * 通过用户id查询用户
     *
     * @param userId 用户id
     * @return 用户对象信息
     */
    public UserInfo selectUserByUserId(Long userId);


    public TableDataInfo selectUserList(UserInfo userInfo,Integer pageSize , Integer pageNum);

    public int deleteUser(Long[] userIds);


    public AjaxResult updateUserStatus(UserInfo userInfo);

    public AjaxResult updateUserPassword(UserInfo userInfo);

}
