package com.example.framework.manager;

import com.example.common.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*异步任务管理*/
public class AsyncManager {

    private static Logger logger = LoggerFactory.getLogger(AsyncManager.class);


    private final int OPEROPERATE_DELAY_TIME = 10 ;

    private ScheduledExecutorService executorService = SpringUtils.getBean("scheduledExecutorService");

    private AsyncManager(){}

    private static AsyncManager me =new AsyncManager();

    public static AsyncManager me(){
        return me;
    }

    public  void execute(TimerTask timerTask){
         executorService.schedule(timerTask, OPEROPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    public void shutdown(){
        if (executorService != null && executorService.isShutdown()) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(120, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(120, TimeUnit.SECONDS)) {
                        logger.info("executorService did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }


}


