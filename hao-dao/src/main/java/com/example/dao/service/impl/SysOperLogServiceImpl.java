package com.example.dao.service.impl;

import com.example.common.core.domain.entity.SysOperLog;
import com.example.dao.mapper.SysOperLogMapper;
import com.example.dao.service.SysOperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
