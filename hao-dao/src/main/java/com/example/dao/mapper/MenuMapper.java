package com.example.dao.mapper;

import com.example.common.core.domain.entity.Menu;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {

    public List<String> selectMenuPermsByUserId(Long userId);

    public List<Long> getMenuListByRoleId(Long roleId);

    public List<Map<String,Object>> getParentMenuList();

    public List<Map<String,Object>> getSubMenuList();

    public List<Menu> selectMenuTreeByUserId(Long userId);
}
