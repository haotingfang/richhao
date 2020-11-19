package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysOperLog;

public interface SysOperLogService {
    /*
    * 新增日志
    * */
    public void insertSysOperLog(SysOperLog sysOperLog);

    /*
     * 查询日志列表
     * */
    public TableDataInfo selectLogList(SysOperLog sysOperLog, Integer pageSize, Integer pageNum);


    /*
     * 日志详情
     * */
    public AjaxResult showLog(Long sysOperLogId);
}
