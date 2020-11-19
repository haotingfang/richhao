package com.example.dao.mapper;

import com.example.common.core.domain.entity.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {

    public int batchRoleMenu(List<RoleMenu> list);

    public int deleteRoleMenuByRoleId(Long roleId);
}

