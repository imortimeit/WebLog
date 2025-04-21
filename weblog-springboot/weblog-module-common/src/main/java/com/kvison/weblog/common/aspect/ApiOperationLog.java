package com.kvison.weblog.common.aspect;

import java.lang.annotation.*;

/**
 * @author xueliang
 * Title: ApiOperationLog</p>
 * Description:自定义注解</p>
 * @date 2025/04/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    String description() default "";
}
