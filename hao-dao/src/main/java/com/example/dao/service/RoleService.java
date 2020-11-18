package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.UserInfo;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Set;

public interface RoleService {

    public Set<String> getRolePermission(Long  userId);

    public TableDataInfo selectRoleList(Role role, Integer pageSize, Integer pageNum);

    public AjaxResult getRoleInfo(Long roleId);

    public AjaxResult addRole(Role role);



}
