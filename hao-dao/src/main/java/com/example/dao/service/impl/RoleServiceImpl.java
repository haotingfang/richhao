package com.example.dao.service.impl;

import com.example.common.core.domain.entity.Role;
import com.example.common.utils.StringUtils;
import com.example.dao.mapper.RoleMapper;
import com.example.dao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Set<String> getRolePermission(Long userId) {
        List<Role> perms = roleMapper.selectRoleByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleCode().trim().split(",")));
            }
        }
        return permsSet;
    }
}
