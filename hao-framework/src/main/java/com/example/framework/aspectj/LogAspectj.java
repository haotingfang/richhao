package com.example.framework.aspectj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.annotation.Log;
import com.example.common.core.domain.entity.SysOperLog;
import com.example.common.core.domain.model.LoginUser;
import com.example.common.enums.BusinessStatus;
import com.example.common.utils.ServletUtils;
import com.example.common.utils.SpringUtils;
import com.example.common.utils.StringUtils;
import com.example.framework.manager.AsyncManager;
import com.example.framework.manager.factory.AsyncFactory;
import com.example.framework.web.service.TokenService;
import net.bytebuddy.asm.Advice;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspectj {

    private static Logger logger = LoggerFactory.getLogger(LogAspectj.class);

    @Autowired
    private TokenService tokenService;

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
        if (log == null) {
            return;
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());


        SysOperLog sysOperLog = new SysOperLog();
        if (loginUser != null) {
            sysOperLog.setOperName(loginUser.getUsername());
        }
        sysOperLog.setStatus(BusinessStatus.SUCCESS.ordinal());

        sysOperLog.setJsonResult(JSON.toJSONString(jsonResult));
        sysOperLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
        if (e != null) {
            sysOperLog.setStatus(BusinessStatus.FAIL.ordinal());
            sysOperLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
        }

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        sysOperLog.setMethod(className + "." + methodName);

        sysOperLog.setRequestMethod(ServletUtils.getRequest().getMethod());
        //set 注解上描述信息
        getControllerMethodDescription(joinPoint, log ,sysOperLog);
        //开启线程保存日志信息
        AsyncManager.me().execute(AsyncFactory.recordOper(sysOperLog));

    }

    /*获取注解上的描述信息*/
    private void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog sysOperLog) {
        sysOperLog.setTitle(log.title());
        sysOperLog.setBusinessType(log.businessType().ordinal());
        sysOperLog.setOperatorType(log.operatorType().ordinal());
        if (log.isSaveRequestData()) {
            //保存参数信息
            setRequestValue(joinPoint, sysOperLog);
        }
    }

    /*获取请求的参数 保存到日志中*/
    private void setRequestValue(JoinPoint joinPoint, SysOperLog sysOperLog) {
        String methodType = sysOperLog.getRequestMethod();
        if (HttpMethod.GET.name().equals(methodType) || HttpMethod.POST.name().equals(methodType)) {
            Object[] args = joinPoint.getArgs(); // 参数值
            String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames(); // 参数名
            String params = argsArrayToString(args, argNames);
            sysOperLog.setOperParam(StringUtils.substring(params.toString(), 0, 5000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            sysOperLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 5000));
        }
    }

    private String argsArrayToString(Object[] paramArray, String[] paramNameArray) {
//        StringBuffer params = new StringBuffer();
        Map map = new HashMap();
        if (paramArray != null && paramArray.length > 0) {
            for(int i = 0 ;i<paramArray.length;i++){
                if(!isFilterObject(paramArray[i])){
                    /*Object object= JSON.toJSON(paramArray[i]);
                    params.append(object).append(" ");*/
                    map.put(paramNameArray[i],paramArray[i]);
                }
            }
        }
        return map.toString();
    }

    private Boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }

    private Log getAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
