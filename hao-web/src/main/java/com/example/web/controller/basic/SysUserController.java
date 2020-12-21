package com.example.web.controller.basic;


import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.dao.service.RoleService;
import com.example.dao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("用户模块")
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Log(title = "用户列表" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("用户列表")
    @PreAuthorize("hasAuthority('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserInfo userInfo, Integer pageSize , Integer pageNum)
    {
        logger.info("用户列表 userInfo:[{}] ",userInfo);
        TableDataInfo tableDataInfo = userService.selectUserList(userInfo,pageSize,pageNum);
        logger.info("用户列表 tableDataInfo:[{}] ",tableDataInfo.toString());
        return tableDataInfo;
    }

    @Log(title = "用户编辑" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("用户编辑")
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping("/{userId}")
    public AjaxResult list(@PathVariable Long userId)
    {
        logger.info("用户编辑 userId:[{}] ",userId);
        //用户信息
        UserInfo userInfo = userService.selectUserByUserId(userId);
        //所有角色信息
        List<Role> roles = roleService.selectAllRole();
        //已配置的角色ids
        List<Integer> roleIds =  roleService.selectRoleListByUserId(userId);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data" , userInfo);
        ajaxResult.put("roles" , roles);
        ajaxResult.put("roleIds" , roleIds);
        logger.info("用户编辑 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }
}

