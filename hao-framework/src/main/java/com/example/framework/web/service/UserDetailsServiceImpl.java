package com.example.framework.web.service;

import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.domain.model.LoginUser;
import com.example.common.core.domain.vo.UserInfoVo;
import com.example.common.enums.CommonStatus;
import com.example.common.utils.StringUtils;
import com.example.dao.service.MenuService;
import com.example.dao.service.RoleService;
import com.example.dao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user)) {
            logger.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }  else if (CommonStatus.DISABLE.getCode().equals(user.getStatus())) {
            logger.info("登录用户：{} 已被停用.", username);
            throw new RuntimeException("对不起，您的账号：" + username + " 已停用");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(UserInfo userInfo) {
        // 角色集合
        Set<String> roles = roleService.getRolePermission(userInfo.getUserId());
        // 权限集合
        Set<String> permissions = menuService.getMenuPermission(userInfo.getUserId());
        return new LoginUser((UserInfoVo) userInfo, roles, permissions);
    }
}
