package com.kvison.weblog.common.exception;
/**
 * @author xueliang
 * Title: BaseExceptionInterface</p>
 * Description:通用异常接口</p>
 * @date 2025/04/15
 */
public interface BaseExceptionInterface {
    //获取异常码
    String getErrorCode();

    //获取异常信息
    String getErrorMessage();
}
