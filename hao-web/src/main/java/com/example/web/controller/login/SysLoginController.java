package com.example.web.controller.login;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.model.LoginUser;
import com.example.common.core.domain.vo.UserInfoVo;
import com.example.common.utils.ServletUtils;
import com.example.dao.service.MenuService;
import com.example.dao.service.RoleService;
import com.example.framework.web.service.SysLoginService;
import com.example.framework.web.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api("登录模块")
@RestController
public class SysLoginController {

    private static Logger logger = LoggerFactory.getLogger(SysLoginController.class);

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        logger.info("请求登录接口 loginName:[{}] , password:[{}]", userName, password);
        AjaxResult ajaxResult = loginService.login(userName, password);
        return ajaxResult;
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        UserInfoVo userInfoVo = loginUser.getUserInfoVo();
        // 角色集合
        Set<String> roles = roleService.getRolePermission(userInfoVo.getUserId());
        // 权限集合
        Set<String> permissions = menuService.getMenuPermission(userInfoVo.getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userInfoVo);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }
}
