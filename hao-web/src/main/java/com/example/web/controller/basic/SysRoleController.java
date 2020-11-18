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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("角色模块")
@RestController
@RequestMapping("/role")
public class SysRoleController {

    private Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private RoleService roleService;


    @Log(title = "角色列表-role" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("角色列表-role")
    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(Role role, Integer pageSize , Integer pageNum)
    {
        logger.info("角色列表 role:[{}] ",role);
        TableDataInfo tableDataInfo = roleService.selectRoleList(role,pageSize,pageNum);
        logger.info("角色列表 ajaxResult:[{}] ",tableDataInfo.toString());
        return tableDataInfo;
    }



}
