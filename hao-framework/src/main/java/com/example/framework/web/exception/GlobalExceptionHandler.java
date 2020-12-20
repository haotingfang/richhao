package com.example.framework.web.exception;

import com.example.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult baseException(RuntimeException e)
    {
        log.error("-------------------------------------异常信息-----------------------------------------------");
        e.printStackTrace();
        return AjaxResult.error(e.getMessage());
    }
}
