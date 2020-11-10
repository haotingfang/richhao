package com.example.dao.mapper;

import com.example.common.core.domain.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    public List<Role> selectRoleByUserId(Long userId);
}
