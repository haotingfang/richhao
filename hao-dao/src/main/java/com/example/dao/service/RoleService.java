package com.example.dao.service;

import com.example.common.core.domain.entity.UserInfo;

import java.util.Set;

public interface RoleService {

    public Set<String> getRolePermission(Long  userId);


}
