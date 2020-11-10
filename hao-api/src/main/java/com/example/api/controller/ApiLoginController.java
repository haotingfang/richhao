package com.example.api.controller;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.model.LoginUser;
import com.example.common.core.domain.vo.WxLoginInfo;
import com.example.framework.web.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("微信登录")
@RestController()
@RequestMapping("/auth")
public class ApiLoginController {

    private static Logger logger = LoggerFactory.getLogger(ApiLoginController.class);

    @Autowired
    private SysLoginService loginService;

    @ApiOperation("微信登录-帐号密码登录")
    @PostMapping("/login_by_account")
    public AjaxResult login(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("code") String code) {
        logger.info("请求微信帐号密码登录接口 loginName:[{}] , password:[{}] , code:[{}]", userName, password, code);
        LoginUser loginUser = loginService.login(userName, password, code);
        AjaxResult ajaxResult = AjaxResult.success(loginUser);
        logger.info("请求微信帐号密码登录接口 ajaxResult:[{}] ", ajaxResult);
        return ajaxResult;
    }

    @ApiOperation("微信登录-微信授权登录")
    @PostMapping("/login_by_weixin")
    public AjaxResult login(@RequestBody WxLoginInfo wxLoginInfo) {
        try {
            logger.info("请求微信授权登录接口  wxLoginInfo:[{}] ", wxLoginInfo);
            AjaxResult ajaxResult = loginService.login(wxLoginInfo.getCode(), wxLoginInfo.getUserInfo());
            logger.info("请求微信授权登录接口  ajaxResult:[{}] ", ajaxResult);
            return ajaxResult;
        } catch (WxErrorException e) {
            e.printStackTrace();
            AjaxResult ajaxResult = AjaxResult.error();
            return ajaxResult;
        }

    }


}
