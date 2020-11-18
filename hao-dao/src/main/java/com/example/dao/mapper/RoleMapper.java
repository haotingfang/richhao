package com.example.dao.mapper;

import com.example.common.core.domain.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    public List<Role> selectRoleByUserId(Long userId);

    public List<Role> selectRoleList(Role role);

    public Role getRoleInfoById(Long id);

    public Boolean checkRoleNameUnique(@Param("id") Long id, @Param("roleName") String roleName);

    public Boolean checkRoleCodeUnique(@Param("id") Long id, @Param("roleName") String roleName);

    public int insertRole(Role role);
}
