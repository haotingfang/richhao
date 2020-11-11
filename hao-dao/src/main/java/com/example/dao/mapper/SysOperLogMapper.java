package com.example.dao.mapper;

import com.example.common.core.domain.entity.SysOperLog;
import org.mapstruct.Mapper;

@Mapper
public interface SysOperLogMapper {

    /*
    * 插入日志信息
    * param  SysOperLog: 日志数据
    * */
   public void insertSysOperLog(SysOperLog sysOperLog);
}
