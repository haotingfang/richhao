package com.example.dao.mapper;

import com.example.common.core.domain.entity.SysOperLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SysOperLogMapper {

    /*
    * 插入日志信息
    * param  SysOperLog: 日志数据
    * */
   public void insertSysOperLog(SysOperLog sysOperLog);


    /*
     * 查询日志列表
     * param  SysOperLog: 日志数据
     * */
    public List<SysOperLog> selectLogList(SysOperLog sysOperLog);


    /*
     * 日志详情
     * param  SysOperLog: 日志数据
     * */
    public SysOperLog showLog(Long id);
}
