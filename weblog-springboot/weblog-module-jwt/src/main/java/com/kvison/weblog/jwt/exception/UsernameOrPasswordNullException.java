package com.kvison.weblog.jwt.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author xueliang
 * Title: UsernameOrPasswordNullException</p>
 * Description:用户名或者密码为空异常,只抛AuthenticationException异常才能手动捕捉</p>
 * @date 2025/11/25
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
