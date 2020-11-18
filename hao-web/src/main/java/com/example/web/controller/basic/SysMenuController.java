package com.example.web.controller.basic;

import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.dao.service.MenuService;
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

@Api("菜单模块")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    private static Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private MenuService menuService;

    @Log(title = "菜单" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("菜单")
    @PreAuthorize("hasAuthority('system:role:query')")
    @GetMapping("/{roleId}")
    public AjaxResult list(@PathVariable Long roleId)
    {
        logger.info("菜单 roleId:[{}] ",roleId);
        AjaxResult ajaxResult = menuService.getMenuListByRoleId(roleId);
        logger.info("菜单 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

}
