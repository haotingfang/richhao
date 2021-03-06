package com.example.dao.mapper;

import com.example.common.core.domain.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

@Mapper
public interface RoleMapper {

    public List<Role> selectRoleByUserId(Long userId);

    public List<Role> selectRoleList(Role role);

    public Role getRoleInfoById(Long id);

    public Boolean checkRoleNameUnique(@Param("id") Long id, @Param("roleName") String roleName);

    public Boolean checkRoleCodeUnique(@Param("id") Long id, @Param("roleCode") String roleName);

    public int insertRole(Role role);

    public int updateRole(Role role);

    public int deleteRoles(@Param("ids") Long[] ids,@Param("updateBy") String updateBy);

    public List<Integer> selectRoleListByUserId(Long userId);


}
