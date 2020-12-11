package com.example.dao.service.impl;

import com.example.common.constant.UserConstants;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.entity.Menu;
import com.example.common.core.domain.vo.MetaVo;
import com.example.common.core.domain.vo.RouterVo;
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

    @Override
    public List<Menu> selectMenuTreeByUserId(Long userId) {
        List<Menu> menus =  menuMapper.selectMenuTreeByUserId(userId);

        return getChildPerms(menus, 0);
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<Menu> menus)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (Menu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<Menu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<Menu> getChildPerms(List<Menu> list, int parentId)
    {
        List<Menu> returnList = new ArrayList<Menu>();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();)
        {
            Menu t = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }



    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(Menu menu)
    {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
        {
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }


    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(Menu menu)
    {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()))
        {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<Menu> list, Menu t)
    {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Menu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<Menu> getChildList(List<Menu> list, Menu t)
    {
        List<Menu> tlist = new ArrayList<Menu>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext())
        {
            Menu n = (Menu) it.next();
            if (n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Menu> list, Menu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    private List<Map> buildMenuTree(List<Map<String,Object>> parentMenus, List<Map<String,Object>> subMenus, List<Long> checkMenus) {
        Map<String,List> subMap = new HashMap();
        for(Map<String,Object> subMenu : subMenus){
            String parentId = String.valueOf(subMenu.get("parent_id"));
            List<Map> list = subMap.get(parentId);
            if(list==null){
                list = new ArrayList<>();
            }
            Long id = (Long) subMenu.get("id");
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
