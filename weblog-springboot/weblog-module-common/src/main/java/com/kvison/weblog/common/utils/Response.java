package com.kvison.weblog.common.utils;

import com.kvison.weblog.common.enums.BizException;
import com.kvison.weblog.common.exception.BaseExceptionInterface;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xueliang
 * Title: Response</p>
 * Description:响应参数工具类</p>
 * @date 2025/04/16
 */
@Data
public class Response<T> implements Serializable {
   //判断响应是否成功
    private boolean success;
    //响应状态码
    private String errorcode;
    //响应消息
    private String message;
    //响应数据
    private T data;

    //----------------------成功响应---------------------
    public static <T> Response<T> success(){
        Response<T> response = new Response<>();
        response.setSuccess(true);
        return response;
    }
    public static <T> Response<T> success(T data){
        Response<T> response= new Response<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
    //----------------------失败响应---------------------
    public static <T> Response<T> fail(){
        Response<T> response = new Response<T>();
        response.setSuccess(false);
        return response;
    }
    public static <T> Response<T> fail(String message){
        Response<T> response = new Response<T> ();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }
    public static <T> Response<T> fail(String errorcode, String message){
        Response<T> response = new Response<T>();
        response.setSuccess(false);
        response.setErrorcode(errorcode);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> fail(BizException bizException) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorcode(bizException.getErrorCode());
        response.setMessage(bizException.getErrorMessage());
        return response;
    }

    /**
     * 此方法的参数是接口，因为存在responseEnum此枚举实现了BaseExceptionInterface接口，因此可以当做参数传递此方法
     * @param baseExceptionInterface
     * @return
     * @param <T>
     */
    public static <T> Response<T> fail(BaseExceptionInterface baseExceptionInterface){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorcode(baseExceptionInterface.getErrorCode());
        response.setMessage(baseExceptionInterface.getErrorMessage());
        return response;
    }
}
