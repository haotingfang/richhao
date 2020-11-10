package com.example.framework.aspectj;

import com.example.common.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspectj {

    private static Logger logger = LoggerFactory.getLogger(LogAspectj.class);

    @Pointcut("@annotation(com.example.common.annotation.Log)")
    public void logPointCut() {

    }

    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void doAfterReturning(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    private void handleLog(JoinPoint joinPoint, Exception e, Object jsonResult) {

        Log log = getAnnotation(joinPoint);
        if(log==null){
            return;
        }

    }

    private Log getAnnotation(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if(method!=null){
          return  method.getAnnotation(Log.class);
        }
        return null;
    }
}
