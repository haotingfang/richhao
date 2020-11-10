package com.example.web.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试模块")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("findById")
    @GetMapping("/find/{id}")
    public String  findById(@PathVariable("id") int id) {
        return String.valueOf(id);
    }
}
