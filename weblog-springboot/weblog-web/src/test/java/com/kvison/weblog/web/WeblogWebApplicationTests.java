package com.kvison.weblog.web;

import com.kvison.weblog.common.domain.dos.UserDO;
import com.kvison.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest
class WeblogWebApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testlog() {
        log.info("info 日志");
        log.warn("warn 日志");
        log.error("error 日志");

        String str = "kvison";
        log.info("str = {}", str);
    }

    @Test
    void testUserMapper() {
        UserDO kvison = UserDO.builder()
                .username("kvison")
                .password("123456")
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();
        userMapper.insert(kvison);
    }

}
