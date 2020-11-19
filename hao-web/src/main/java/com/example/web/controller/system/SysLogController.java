package com.example.web.controller.system;

import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.SysOperLog;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.dao.service.SysOperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("日志模块")
@RestController
@RequestMapping("/log")
public class SysLogController {

    private static Logger logger = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    private SysOperLogService sysOperLogService;

    @Log(title = "日志列表", businessType = BusinessType.OTHER, operatorType = OperatorType.MANAGE)
    @ApiOperation("日志列表")
    @PreAuthorize("hasAuthority('system:log')")
    @GetMapping("/list")
    public TableDataInfo list(Integer pageSize, Integer pageNum, SysOperLog sysOperLog) {
        logger.info("日志列表 sysOperLog:[{}] ", sysOperLog);
        TableDataInfo tableDataInfo = sysOperLogService.selectLogList(sysOperLog, pageSize, pageNum);
        logger.info("角色列表 tableDataInfo:[{}] ", tableDataInfo.toString());
        return tableDataInfo;
    }

    @Log(title = "日志详情", businessType = BusinessType.OTHER, operatorType = OperatorType.MANAGE)
    @ApiOperation("日志详情")
    @PreAuthorize("hasAuthority('system:log')")
    @GetMapping("/show")
    public AjaxResult show(Long sysOperLogId) {
        logger.info("日志详情 sysOperLogId:[{}] ", sysOperLogId);
        AjaxResult ajaxResult = sysOperLogService.showLog(sysOperLogId);
        logger.info("日志详情 ajaxResult:[{}] ", ajaxResult.toString());
        return ajaxResult;
    }
}
