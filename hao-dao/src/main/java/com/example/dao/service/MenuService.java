package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TreeSelect;
import com.example.common.core.domain.entity.Menu;
import com.example.common.core.domain.vo.RouterVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MenuService {

    public Set<String> getMenuPermission(Long userId);

//    public AjaxResult getMenuListByRoleId(Long roleId);

    public List<Menu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<Menu> menus);

    public List<Menu>  selectMenuList();

    public List<TreeSelect> buildMenuTreeSelect(List<Menu> menus);

    public List<Menu> buildMenuTree(List<Menu> menus);





}
