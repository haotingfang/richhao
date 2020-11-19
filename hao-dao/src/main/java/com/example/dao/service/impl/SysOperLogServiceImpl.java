package com.example.dao.service.impl;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.SysOperLog;
import com.example.common.utils.TableDataUtils;
import com.example.dao.mapper.SysOperLogMapper;
import com.example.dao.service.SysOperLogService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOperLogServiceImpl implements SysOperLogService {

    private Logger logger = LoggerFactory.getLogger(SysOperLogServiceImpl.class);

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public void insertSysOperLog(SysOperLog sysOperLog) {
        logger.info("----插入日志数据----"+sysOperLog.toString());
        sysOperLogMapper.insertSysOperLog(sysOperLog);
    }

    @Override
    public TableDataInfo selectLogList(SysOperLog sysOperLog, Integer pageSize, Integer pageNum) {
        logger.info("查询日志列表selectLogList  sysOperLog:[{}] , pageSize:[{}] , pageNum:[{}] ",sysOperLog,pageSize,pageNum);
        PageHelper.startPage(pageNum,pageSize);
        List<SysOperLog> list = sysOperLogMapper.selectLogList(sysOperLog);
        TableDataInfo tableDataInfo = TableDataUtils.buildTableDataInfo(list);
        return tableDataInfo;
    }

    @Override
    public AjaxResult showLog(Long sysOperLogId) {
        SysOperLog sysOperLog = sysOperLogMapper.showLog(sysOperLogId);
        return AjaxResult.success(sysOperLog);
    }
}
