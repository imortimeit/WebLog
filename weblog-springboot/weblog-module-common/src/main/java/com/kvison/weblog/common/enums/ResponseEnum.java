package com.kvison.weblog.common.enums;

import com.kvison.weblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author xueliang
 * Title: ResponseEnum</p>
 * Description:响应异常码</p>
 * @date 2025/04/16
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements BaseExceptionInterface {
    //------------通用异常码-------------
    SYSTEM_ERROR("10000","网络异常正在修复"),
    //------------业务异常状态码-------------
    PRODUCT_NOT_FOND("20000","产品不存在"),
    //------------参数异常状态码-------------
    PARAM_NOT_VALID("10001", "参数错误"),
    ;

    private String errorCode;
    private String errorMessage;
}
