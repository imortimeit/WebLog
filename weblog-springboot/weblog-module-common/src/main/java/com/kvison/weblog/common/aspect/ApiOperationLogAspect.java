package com.kvison.weblog.common.aspect;

import com.kvison.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xueliang
 * Title: ApiOperationLogAspect</p>
 * Description:日志切面类</p>
 * @date 2025/04/15
 */
@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    /**
     * 以自定义注解@ApiOperationLog为切点,凡是添加了@ApiOperationLog注解的方法,都进行切面拦截
     */
    @Pointcut("@annotation(com.kvison.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {
        System.out.println("是否进入");
    }

    /**
     * 环绕通知方法，用于记录API操作日志
     *
     * @param joinPoint 切入点对象，包含被代理方法的信息。通过ProceedingJoinPoint可以控制目标方法的执行：
     *                  1. 调用proceed()方法执行被代理方法
     *                  2. 获取方法参数、方法签名等信息
     *                  3. 修改方法参数（需要谨慎使用）
     * @return 目标方法的执行结果，返回值类型根据具体业务方法决定：
     * 1. 正常执行时返回业务方法的原始返回值
     * 2. 可以通过修改返回值实现响应包装
     * 3. 异常时会直接抛出原始异常
     * @throws Throwable 代理方法执行可能抛出的所有异常，调用者需要处理：
     *                   1. 业务逻辑抛出的检查型异常
     *                   2. 运行时异常
     *                   3. 环绕通知自身可能抛出的异常
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {

            Long startTime = System.currentTimeMillis();
            MDC.put("traceId", UUID.randomUUID().toString());
            //获取被请求的类和方法
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();


            //请求入参
            Object[] args = joinPoint.getArgs();
            //入参转json,将参数转化为Json字符串，并用逗号分隔
            String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));
            //功能描述信息
            String description = getApiOperationLogDescription(joinPoint);

            // 打印请求相关参数(已引入日志注解)
            log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                    description, argsJsonStr, className, methodName);

            // 执行切点方法
            Object result = joinPoint.proceed();

            // 执行耗时
            long executionTime = System.currentTimeMillis() - startTime;

            // 打印出参等相关信息
            log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                    description, executionTime, JsonUtil.toJson(result));

            return result;
        } finally {
            MDC.clear();
        }

    }

    /**
     * 获取注解的描述信息
     *
     * @param joinPoint
     * @return
     */
    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //使用MethodSignature获取当前被注解的method
        Method method = signature.getMethod();

        //从method获取LogExecution注解
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);

        //从注解中获取description
        return apiOperationLog.description();
    }

    private Function<Object, String> toJsonStr() {
        return arg -> JsonUtil.toJson(arg);
    }

}
