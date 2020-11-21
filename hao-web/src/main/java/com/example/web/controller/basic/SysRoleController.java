package com.example.web.controller.basic;

import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.dao.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("角色模块")
@RestController
@RequestMapping("/role")
public class SysRoleController {

    private Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private RoleService roleService;


    @Log(title = "角色列表" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("角色列表")
    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(Role role, Integer pageSize , Integer pageNum)
    {
        logger.info("角色列表 role:[{}] ",role);
        TableDataInfo tableDataInfo = roleService.selectRoleList(role,pageSize,pageNum);
        logger.info("角色列表 tableDataInfo:[{}] ",tableDataInfo.toString());
        return tableDataInfo;
    }

    @Log(title = "角色详情查看" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("角色详情查看")
    @PreAuthorize("hasAuthority('system:role:query')")
    @GetMapping("/{roleId}")
    public AjaxResult getRoleInfo(@PathVariable  Long roleId)
    {
        logger.info("角色详情查看 roleId:[{}] ",roleId);
        AjaxResult ajaxResult = roleService.getRoleInfo(roleId);
        logger.info("角色列表 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    /*
    * {"delFlag":"0","menus":[6,7,8],"remark":"测试哦","roleCode":"","roleName":"测试哦","status":"0"}
    * */
    @Log(title = "角色详情新增" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("角色详情新增")
    @PreAuthorize("hasAuthority('system:role:add')")
    @PostMapping("/addRole")
    public AjaxResult addRole(@RequestBody @Validated Role role)
    {
        logger.info("角色详情新增 role:[{}] ",role);
        AjaxResult ajaxResult = roleService.addRole(role);
        logger.info("角色详情新增 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "角色详情编辑" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("角色详情编辑")
    @PreAuthorize("hasAuthority('system:role:edit')")
    @PostMapping("/editRole")
    public AjaxResult editRole(@RequestBody Role role)
    {
        logger.info("角色详情编辑 role:[{}] ",role);
        AjaxResult ajaxResult = roleService.editRole(role);
        logger.info("角色详情编辑 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "角色删除" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("角色删除")
    @PreAuthorize("hasAuthority('system:role:delete')")
    @PutMapping("/deleteRole")
    public AjaxResult deleteRole(Long roleId)
    {
        logger.info("角色删除 role:[{}] ",roleId);
        AjaxResult ajaxResult = roleService.deleteRole(roleId);
        logger.info("角色删除 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }




}
