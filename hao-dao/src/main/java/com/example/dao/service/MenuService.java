package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.entity.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService {

    public Set<String> getMenuPermission(Long userId);

    public AjaxResult getMenuListByRoleId(Long roleId);


}
