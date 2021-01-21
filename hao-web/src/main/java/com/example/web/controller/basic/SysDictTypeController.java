package com.example.web.controller.basic;

import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysConfig;
import com.example.common.core.domain.entity.SysDictType;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.dao.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("字典类型")
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController {

    private static final Logger logger = LoggerFactory.getLogger(SysDictTypeController.class);

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Log(title = "字典类型列表" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("字典类型列表")
    @PreAuthorize("hasAuthority('system:dict:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictType sysDictType, Integer pageSize , Integer pageNum)
    {
        logger.info("字典类型列表 sysDictType:[{}] ",sysDictType);
        TableDataInfo tableDataInfo = sysDictTypeService.selectDictTypeList(sysDictType,pageSize,pageNum);
        logger.info("字典类型列表 tableDataInfo:[{}] ",tableDataInfo.toString());
        return tableDataInfo;
    }

    @Log(title = "字典类型删除" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("字典类型删除")
    @PreAuthorize("hasAuthority('system:dict:remove')")
    @DeleteMapping("/{dictTypeIds}")
    public AjaxResult delete(@PathVariable Long[] dictTypeIds)
    {
        logger.info("字典类型删除 dictTypeIds:[{}] ",dictTypeIds);
        int row  = sysDictTypeService.delete(dictTypeIds);
        AjaxResult ajaxResult = row > 0 ? AjaxResult.success() : AjaxResult.error();
        logger.info("字典类型删除 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "字典类型编辑" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("字典类型编辑")
    @PreAuthorize("hasAuthority('system:dict:query')")
    @GetMapping(value = { "/{dictTypeId}" })
    public AjaxResult show(@PathVariable(value = "dictTypeId" , required = false ) Long dictTypeId)
    {
        logger.info("字典类型编辑 dictTypeId:[{}] ",dictTypeId);
        AjaxResult ajaxResult = sysDictTypeService.queryById(dictTypeId);
        logger.info("字典类型编辑 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "字典类型新增" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("字典类型新增")
    @PreAuthorize("hasAuthority('system:dict:add')")
    @PostMapping
    public AjaxResult addDictType(@RequestBody @Validated SysDictType sysDictType)
    {
        logger.info("字典类型新增 sysDictType:[{}] ",sysDictType);
        AjaxResult ajaxResult = sysDictTypeService.addDictType(sysDictType);
        logger.info("字典类型新增 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "字典类型更新" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("字典类型更新")
    @PreAuthorize("hasAuthority('system:dict:edit')")
    @PostMapping
    public AjaxResult editDictType(@RequestBody @Validated SysDictType sysDictType)
    {
        logger.info("字典类型更新 sysDictType:[{}] ",sysDictType);
        AjaxResult ajaxResult = sysDictTypeService.editDictType(sysDictType);
        logger.info("字典类型更新 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "字典类型缓存清除" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("字典类型缓存清除")
    @PreAuthorize("hasAuthority('system:dict:edit')")
    @DeleteMapping ("/clearCache")
    public AjaxResult clearCache()
    {
        logger.info("字典类型缓存清除 start ");
        AjaxResult ajaxResult = sysDictTypeService.clearCache();
        logger.info("字典类型缓存清除 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }



}
