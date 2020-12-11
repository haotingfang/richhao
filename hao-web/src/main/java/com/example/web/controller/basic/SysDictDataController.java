package com.example.web.controller.basic;

import com.example.common.core.domain.AjaxResult;
import com.example.dao.service.DictTypeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("字典模块")
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController {

    @Autowired
    private DictTypeService dictTypeService;


    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {

        return AjaxResult.success(dictTypeService.selectDictDataByType(dictType));
    }
}
