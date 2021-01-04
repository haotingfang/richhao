package com.example.web.controller.basic;


import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.common.utils.StringUtils;
import com.example.dao.service.RoleService;
import com.example.dao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult list(@PathVariable Long userId)
    {
        logger.info("用户编辑 userId:[{}] ",userId);
        //所有角色信息
        List<Role> roles = roleService.selectAllRole();
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("roles" , roles);
        if(StringUtils.isNotNull(userId)){
            //用户信息
            UserInfo userInfo = userService.selectUserByUserId(userId);

            //已配置的用户ids
            List<Integer> roleIds =  roleService.selectRoleListByUserId(userId);
            ajaxResult.put("data" , userInfo);
            ajaxResult.put("roleIds" , roleIds);
        }
        logger.info("用户编辑 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }


    @Log(title = "用户删除" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("用户删除")
    @PreAuthorize("hasAuthority('system:user:remove')")
    @DeleteMapping("/{userIds}")
    public AjaxResult deleteRole(@PathVariable Long[] userIds) {
        logger.info("用户删除 userIds:[{}] ", userIds);
        int rows = userService.deleteUser(userIds);
        AjaxResult ajaxResult = rows > 0 ? AjaxResult.success() : AjaxResult.error();
        logger.info("用户删除 ajaxResult:[{}] ", ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "用户停用/启用" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("用户停用/启用")
//    @PreAuthorize("hasAuthority('system::edit')")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody UserInfo userInfo)
    {
        logger.info("用户停用/启用 userInfo:[{}] ",userInfo);
        AjaxResult ajaxResult = userService.updateUserStatus(userInfo);
        logger.info("用户停用/启用 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }


    @Log(title = "用户密码重置" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("用户密码重置")
//    @PreAuthorize("hasAuthority('system::edit')")
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody UserInfo userInfo)
    {
        logger.info("用户密码重置 userInfo:[{}] ",userInfo);
        AjaxResult ajaxResult = userService.updateUserPassword(userInfo);
        logger.info("用户密码重置 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }
}

