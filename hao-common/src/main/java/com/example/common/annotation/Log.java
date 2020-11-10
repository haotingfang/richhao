package com.example.common.annotation;

import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /*标题*/
    public String title() default "";

    /*功能*/
    public BusinessType businessType() default BusinessType.OTHER;

    /*操作人类别 区分手机端还是后台端*/
    public OperatorType operatorType() default OperatorType.OTHER;

    /*是否保持请求数据*/
    public boolean isSaveRequestData() default true;


}
