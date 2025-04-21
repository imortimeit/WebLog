package com.kvison.weblog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class WeblogWebApplicationTests {

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

}
