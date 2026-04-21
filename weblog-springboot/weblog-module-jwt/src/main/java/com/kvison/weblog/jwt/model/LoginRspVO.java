package com.kvison.weblog.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xueliang
 * Title: LoginRspVO</p>
 * Description:</p>
 * @date 2026/04/13
 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class LoginRspVO {

        /**
         * Token 值
         */
        private String token;

    }
