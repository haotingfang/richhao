package com.example.web.controller.test;

import com.example.common.annotation.Log;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api("测试模块")
@RestController
@RequestMapping("/test")
public class TestController {

    @Log(title = "测试findById" ,businessType = BusinessType.OTHER ,operatorType = OperatorType.MANAGE)
    @ApiOperation("findById")
    @GetMapping("/find/{id}")
    @PreAuthorize("hasAuthority('test.findById')")
    public String  findById(@PathVariable("id") int id) {
        return String.valueOf(id);
    }


    @Log(title = "测试getById" ,businessType = BusinessType.OTHER ,operatorType = OperatorType.MANAGE)
    @ApiOperation("findById")
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('test.getById')")
    public String  getById(@RequestParam("id") int id) {
        return String.valueOf(id);
    }

    @Log(title = "测试save" ,businessType = BusinessType.OTHER ,operatorType = OperatorType.MANAGE)
    @ApiOperation("save")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('test.jj')")
    public String  save(@RequestBody String str) {
        return str;
    }

    @Log(title = "测试角色权限" ,businessType = BusinessType.OTHER ,operatorType = OperatorType.MANAGE)
    @ApiOperation("testRole")
    @PostMapping("/testRole")
    @PreAuthorize("hasRole('test')")
    public String  testRole(@RequestBody String str) {
        return str;
    }

    @Log(title = "测试admin角色权限" ,businessType = BusinessType.OTHER ,operatorType = OperatorType.MANAGE)
    @ApiOperation("testAdminRole")
    @PostMapping("/testAdminRole")
    @PreAuthorize("hasRole('admin')")
    public String  testAdminRole(@RequestBody String str) {
        return str;
    }
}
