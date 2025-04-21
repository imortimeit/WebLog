package com.kvison.weblog.common.enums;

import com.kvison.weblog.common.exception.BaseExceptionInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xueliang
 * Title: BizException</p>
 * Description:业务异常</p>
 * @date 2025/04/16
 */
@Getter
@Setter
public class BizException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface)
    {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
