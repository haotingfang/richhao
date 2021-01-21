package com.example.web.controller.basic;

import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysDictData;
import com.example.common.core.domain.entity.SysDictType;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.dao.service.SysDictDataService;
import com.example.dao.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("字典数据模块")
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController {

    private static final Logger logger = LoggerFactory.getLogger(SysDictDataController.class);

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Autowired
    private SysDictDataService sysDictDataService;


    /**
     * 根据字典数据查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        return AjaxResult.success(sysDictTypeService.selectDictDataByType(dictType));
    }

    @Log(title = "字典数据列表", businessType = BusinessType.OTHER, operatorType = OperatorType.MANAGE)
    @ApiOperation("字典数据列表")
    @PreAuthorize("hasAuthority('system:dict:query')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData sysDictData, Integer pageSize, Integer pageNum) {
        logger.info("字典数据列表 sysDictData:[{}] ", sysDictData);
        TableDataInfo tableDataInfo = sysDictDataService.selectDictDataList(sysDictData, pageSize, pageNum);
        logger.info("字典数据列表 tableDataInfo:[{}] ", tableDataInfo.toString());
        return tableDataInfo;
    }

    @Log(title = "字典数据删除", businessType = BusinessType.OTHER, operatorType = OperatorType.MANAGE)
    @ApiOperation("字典数据删除")
    @PreAuthorize("hasAuthority('system:dict:remove')")
    @DeleteMapping("/{dictDataIds}")
    public AjaxResult delete(@PathVariable Long[] dictDataIds) {
        logger.info("字典数据删除 dictDataIds:[{}] ", dictDataIds);
        int row = sysDictDataService.delete(dictDataIds);
        AjaxResult ajaxResult = row > 0 ? AjaxResult.success() : AjaxResult.error();
        logger.info("字典数据删除 ajaxResult:[{}] ", ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "字典数据编辑", businessType = BusinessType.OTHER, operatorType = OperatorType.MANAGE)
    @ApiOperation("字典数据编辑")
    @PreAuthorize("hasAuthority('system:dict:query')")
    @GetMapping(value = {"/{dictDataId}"})
    public AjaxResult show(@PathVariable(value = "dictTypeId", required = false) Long dictDataId) {
        logger.info("字典数据编辑 dictDataId:[{}] ", dictDataId);
        AjaxResult ajaxResult = sysDictDataService.queryById(dictDataId);
        logger.info("字典数据编辑 ajaxResult:[{}] ", ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "字典数据新增", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @ApiOperation("字典数据新增")
    @PreAuthorize("hasAuthority('system:dict:add')")
    @PostMapping
    public AjaxResult addDictType(@RequestBody @Validated SysDictData sysDictData) {
        logger.info("字典数据新增 sysDictData:[{}] ", sysDictData);
        AjaxResult ajaxResult = sysDictDataService.addDictData(sysDictData);
        logger.info("字典数据新增 ajaxResult:[{}] ", ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "字典数据更新", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @ApiOperation("字典数据更新")
    @PreAuthorize("hasAuthority('system:dict:edit')")
    @PostMapping
    public AjaxResult editDictType(@RequestBody @Validated SysDictData sysDictData) {
        logger.info("字典数据更新 sysDictData:[{}] ", sysDictData);
        AjaxResult ajaxResult = sysDictDataService.editDictData(sysDictData);
        logger.info("字典数据更新 ajaxResult:[{}] ", ajaxResult.toString());
        return ajaxResult;
    }
}
