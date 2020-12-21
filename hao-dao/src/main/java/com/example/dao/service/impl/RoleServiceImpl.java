package com.example.dao.service.impl;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.RoleMenu;
import com.example.common.exception.CustomException;
import com.example.common.utils.BeanUtils;
import com.example.common.utils.SecurityUtils;
import com.example.common.utils.StringUtils;
import com.example.common.utils.TableDataUtils;
import com.example.dao.mapper.RelationUserRoleMapper;
import com.example.dao.mapper.RoleMapper;
import com.example.dao.mapper.RoleMenuMapper;
import com.example.dao.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RelationUserRoleMapper relationUserRoleMapper;

    @Override
    public Set<String> getRolePermission(Long userId) {
        List<Role> perms = roleMapper.selectRoleByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                /**/
                permsSet.add("ROLE_"+perm.getRoleCode().trim());
            }
        }
        return permsSet;
    }

    @Override
    public TableDataInfo selectRoleList(Role role, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectRoleList(role);
        TableDataInfo tableDataInfo = TableDataUtils.buildTableDataInfo(list);
        return tableDataInfo;
    }

    public AjaxResult getRoleInfo(Long roleId){
        Role role = roleMapper.getRoleInfoById(roleId);
        return AjaxResult.success(role);
    }

    @Override
    @Transactional
    public AjaxResult addRole(Role role) {
        //校验角色名称 和 角色编号
        if(checkRoleNameUnique(role)){
            return AjaxResult.error("角色名称重复");
        }
        if(checkRoleCodeUnique(role)){
            return AjaxResult.error("角色编号重复");
        }
        BeanUtils.addBuildBean(role);
        //插入角色数据
        roleMapper.insertRole(role);
        //插入菜单关联数据
        insertRoleMenu(role);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult editRole(Role role) {
        //校验角色名称 和 角色编号
        if(checkRoleNameUnique(role)){
            return AjaxResult.error("角色名称重复");
        }
        if(checkRoleCodeUnique(role)){
            return AjaxResult.error("角色编号重复");
        }
        BeanUtils.editBuildBean(role);
        //更新角色数据
        roleMapper.updateRole(role);
        //删除老的菜单关联数据
        roleMenuMapper.deleteRoleMenuByRoleId(role.getId());
        //插入菜单关联数据
        insertRoleMenu(role);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public int deleteRole(Long[] roleIds)  {
//        验证角色是否被引用
        for(Long roleId : roleIds){
            Role role = roleMapper.getRoleInfoById(roleId);
            //查询角色被引用的数据量
            int count = relationUserRoleMapper.countUserRoleByRoleId(roleId);
            if(count>0){
                throw new RuntimeException(role.getRoleName()+"已分配,不能删除");
            }
        }
        String userName = SecurityUtils.getUserName();
        return roleMapper.deleteRoles(roleIds,userName);
    }

    @Override
    @Transactional
    public AjaxResult updateRoleStatus(Role role) {
        String userName = SecurityUtils.getUserName();
        role.setUpdateBy(userName);
        roleMapper.updateRole(role);
        return AjaxResult.success();
    }

    @Override
    public List<Role> selectAllRole() {
        List<Role> list = roleMapper.selectRoleList(null);
        return list;
    }

    @Override
    public List<Integer> selectRoleListByUserId(Long userId) {
        List<Integer> roleIds = roleMapper.selectRoleListByUserId(userId);
        return roleIds;
    }


    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public int insertRoleMenu(Role role)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        if (role.getMenus() != null) {
            for (Long menuId : role.getMenus()) {
                RoleMenu rm = new RoleMenu();
                rm.setRoleId(role.getId());
                rm.setMenuId(menuId);
                list.add(rm);
            }
        }
        if (list.size() > 0) {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }



    /*
    * return true :存在相同角色名称数据
    * */
    private boolean checkRoleNameUnique(Role role){
        String roleName = role.getRoleName();
        Long id = role.getId();
        return roleMapper.checkRoleNameUnique(id, roleName);
    }

    /*
     * return true :存在相同角色名称数据
     * */
    private boolean checkRoleCodeUnique(Role role){
        String roleCode = role.getRoleCode();
        Long id = role.getId();
        return roleMapper.checkRoleCodeUnique(id, roleCode);
    }


}
