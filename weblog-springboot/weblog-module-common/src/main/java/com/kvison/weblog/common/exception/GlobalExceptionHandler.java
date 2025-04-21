package com.kvison.weblog.common.exception;

import com.kvison.weblog.common.enums.BizException;
import com.kvison.weblog.common.enums.ResponseEnum;
import com.kvison.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author xueliang
 * Title: GlobalExceptionHandler</p>
 * Description:全局异常处理类（捕捉抛异常的时间来执行不同的方法）
 *     Spring Boot 提供了 @ControllerAdvice 注解，它允许我们定义一个全局的异常处理类。
 *     这个类可以捕获应用中抛出的所有异常，并根据需要进行处理。</p>
 * @date 2025/04/16
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常（满足业务场景而手动设置的异常），只要捕捉到BizException就会执行此方法来处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler({BizException.class})
    @ResponseBody
    public Response<String> handlerBizException(HttpServletRequest request, BizException e){
        log.warn("捕获到业务异常：{} request fail,errorcode:{},errormessage:{}",request.getRequestURI(),e.getErrorCode(),e.getErrorMessage());
        return Response.fail(e);
    }

    /**
     * 其他类型异常（系统运行异常，如除0异常）,只要捕捉到Exception就会执行此方法来处理系统异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class })
    @ResponseBody
    public Response<Object> handleOtherException(HttpServletRequest request, Exception e) {
        log.error("捕捉到系统异常:{} request error,", request.getRequestURI(), e);
        return Response.fail(ResponseEnum.SYSTEM_ERROR);
    }


    /**
     * 捕获参数校验异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Response<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e){
        //获取异常状态码
        String errorCode = ResponseEnum.PARAM_NOT_VALID.getErrorCode();

        //获取BindingResult中的信息
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();

        //获取检验不通过的字段，把错误信息拼接
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(fieldErrors -> {
            fieldErrors.forEach(fieldError -> {
                sb.append(fieldError.getField())
                        .append(" ")
                        .append(fieldError.getDefaultMessage())
                        .append(",当前值：'")
                        .append(fieldError.getRejectedValue())
                        .append("';");
            });
        });

        //错误信息
        String errorMessage = sb.toString();

        log.warn("参数校验异常：{} request fail,errorcode:{},errormessage:{}",request.getRequestURI(),errorCode,errorMessage);

        return  Response.fail(errorCode, errorMessage);
    }

}
