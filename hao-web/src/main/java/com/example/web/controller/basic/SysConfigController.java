package com.example.web.controller.basic;

import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.SysConfig;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.common.utils.StringUtils;
import com.example.dao.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("配置模块")
@RestController
@RequestMapping("/system/config")
public class SysConfigController {

    private static Logger logger = LoggerFactory.getLogger(SysConfigController.class);

    @Autowired
    private ConfigService configService;

    @Log(title = "参数设置列表" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("参数设置列表")
    @PreAuthorize("hasAuthority('system:config:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysConfig sysConfig, Integer pageSize , Integer pageNum)
    {
        logger.info("参数设置列表 sysConfig:[{}] ",sysConfig);
        TableDataInfo tableDataInfo = configService.selectConfigList(sysConfig,pageSize,pageNum);
        logger.info("参数设置列表 tableDataInfo:[{}] ",tableDataInfo.toString());
        return tableDataInfo;
    }

    @Log(title = "参数设置删除" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("参数设置删除")
    @PreAuthorize("hasAuthority('system:config:remove')")
    @DeleteMapping("/{configIds}")
    public AjaxResult delete(@PathVariable Long[] configIds)
    {
        logger.info("参数设置删除 configIds:[{}] ",configIds);
        int row  = configService.delete(configIds);
        AjaxResult ajaxResult = row > 0 ? AjaxResult.success() : AjaxResult.error();
        logger.info("参数设置删除 tableDataInfo:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }


    @Log(title = "参数设置编辑" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("参数设置编辑")
    @PreAuthorize("hasAuthority('system:config:query')")
    @GetMapping(value = { "/{configId}" })
    public AjaxResult show(@PathVariable(value = "configId" , required = false ) Long configId)
    {
        logger.info("参数设置编辑 userId:[{}] ",configId);
        //参数设置信息
        SysConfig sysConfig = configService.selectConfigById(configId);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("data" , sysConfig);
        logger.info("参数设置编辑 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }


    @Log(title = "参数设置新增" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("参数设置新增")
    @PreAuthorize("hasAuthority('system:config:add')")
    @PostMapping
    public AjaxResult addConfig(@RequestBody @Validated SysConfig sysConfig)
    {
        logger.info("角色详情新增 sysConfig:[{}] ",sysConfig);
        AjaxResult ajaxResult = configService.addConfig(sysConfig);
        logger.info("角色详情新增 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "参数设置更新" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("参数设置更新")
    @PreAuthorize("hasAuthority('system:config:edit')")
    @PutMapping
    public AjaxResult updateConfig(@RequestBody @Validated SysConfig sysConfig)
    {
        logger.info("参数设置更新 sysConfig:[{}] ",sysConfig);
        AjaxResult ajaxResult = configService.editConfig(sysConfig);
        logger.info("参数设置更新 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }


    @Log(title = "参数设置缓存清除" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("参数设置缓存清除")
    @PreAuthorize("hasAuthority('system:config:edit')")
    @DeleteMapping ("/clearCache")
    public AjaxResult clearCache()
    {
        logger.info("参数设置缓存清除 start ");
        AjaxResult ajaxResult = configService.clearCache();
        logger.info("参数设置缓存清除 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }




    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable String configKey)
    {
        return AjaxResult.success(configService.selectConfigByKey(configKey));
    }

}
