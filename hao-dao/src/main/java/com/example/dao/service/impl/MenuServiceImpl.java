package com.example.dao.service.impl;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.entity.Menu;
import com.example.common.utils.ConvertUtils;
import com.example.common.utils.StringUtils;
import com.example.dao.mapper.MenuMapper;
import com.example.dao.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Set<String> getMenuPermission(Long userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    public AjaxResult getMenuListByRoleId(Long roleId) {
//        获取选中所有菜单id
        List<Long> checkMenus = menuMapper.getMenuListByRoleId(roleId);
//        获取所有一级菜单
        List<Map<String,Object>> parentMenus = menuMapper.getParentMenuList();
//        获取所有菜单
        List<Map<String,Object>> subMenus = menuMapper.getSubMenuList();
//        build菜单层级结构,并组装选中数据
        List<Map> retList = buildMenuTree(parentMenus, subMenus, checkMenus);
        return  AjaxResult.success(retList);
    }

    private List<Map> buildMenuTree(List<Map<String,Object>> parentMenus, List<Map<String,Object>> subMenus, List<Long> checkMenus) {
        Map<String,List> subMap = new HashMap();
        for(Map<String,Object> subMenu : subMenus){
            String parentId = String.valueOf(subMenu.get("parent_id"));
            List<Map> list = subMap.get(parentId);
            if(list==null){
                list = new ArrayList<>();
            }
            String id = String.valueOf(subMenu.get("id"));
            String checkFlag = "false" ;
            if(checkMenus.contains(id)){
                checkFlag = "true";
            }
            subMenu.put("checked",checkFlag);
            list.add(subMenu);
            subMap.put(parentId,list);
        }
        List<Map> retList = new ArrayList<>();
        for(Map<String,Object> parentMenu : parentMenus){
            String parentId = String.valueOf(parentMenu.get("id"));
            List<Map> subList = subMap.get(parentId);
            parentMenu.put("sub",subList);
            retList.add(parentMenu);
        }
        return retList;
    }
}
