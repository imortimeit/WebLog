package com.kvison.weblog.jwt.handler;

import com.kvison.weblog.common.enums.ResponseEnum;
import com.kvison.weblog.common.utils.Response;
import com.kvison.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xueliang
 * Title: RestAuthenticationEntryPoint</p>
 * Description:用户未登录访问受保护的资源</p>
 * @date 2026/04/21
 */
@Slf4j
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("用户未登录访问受保护的资源: ", authException);
        if (authException instanceof InsufficientAuthenticationException) {
            ResultUtil.fail(response, HttpStatus.UNAUTHORIZED.value(), Response.fail(ResponseEnum.UNAUTHORIZED));
            return;
        }

        ResultUtil.fail(response, HttpStatus.UNAUTHORIZED.value(), Response.fail(authException.getMessage()));
    }
}
