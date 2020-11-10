package com.example.api.controller;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.model.LoginUser;
import com.example.common.core.domain.vo.WxLoginInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Api("微信-订单")
@RestController()
@RequestMapping("/order")
public class ApiOrderController {

    private static Logger logger = LoggerFactory.getLogger(ApiOrderController.class);

    @ApiOperation("微信-查看全部订单")
    @GetMapping("/list")
    public AjaxResult list(@RequestParam("showType") String showType, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        logger.info("请求查看全部订单接口  ");
        AjaxResult ajaxResult = AjaxResult.success();
        logger.info("请求查看全部订单接口  ajaxResult:[{}] ", ajaxResult);
        return ajaxResult;
    }
}
