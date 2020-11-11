package com.example.framework.manager.factory;

import com.example.common.core.domain.entity.SysOperLog;
import com.example.common.utils.SpringUtils;
import com.example.dao.service.SysOperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/*异步工厂：产生任务*/
public class AsyncFactory {

    private Logger logger = LoggerFactory.getLogger(AsyncFactory.class);

    public static TimerTask recordOper(SysOperLog sysOperLog){
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(SysOperLogService.class).insertSysOperLog(sysOperLog);
            }
        };
    }
}
