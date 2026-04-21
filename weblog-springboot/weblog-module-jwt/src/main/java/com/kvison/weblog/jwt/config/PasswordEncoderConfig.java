package com.kvison.weblog.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xueliang
 * Title: PasswordEncoderConfig</p>
 * Description:密码加密
 * PasswordEncoder 接口是 Spring Security 提供的密码加密接口，它定义了密码加密和密码验证的方法。
 * 通过实现这个接口，您可以将密码加密为不可逆的哈希值，以及在验证密码时对比哈希值。
 * 上述代码中，我们初始化了一个 PasswordEncoder 接口的具体实现类 BCryptPasswordEncoder。BCryptPasswordEncoder 是 Spring Security 提供的密码加密器的一种实现，使用 BCrypt 算法对密码进行加密。
 * BCrypt 是一种安全且适合密码存储的哈希算法，它在进行哈希时会自动加入“盐”，增加密码的安全性。</p>
 * @date 2025/11/25
 */
@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        // BCrypt 是一种安全且适合密码存储的哈希算法，它在进行哈希时会自动加入“盐”，增加密码的安全性。
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bcrypPassword=new BCryptPasswordEncoder();
        System.out.println(bcrypPassword.encode("kvison"));
    }
}
