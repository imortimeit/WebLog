package com.kvison.weblog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xueliang
 * Title: JsonUtil</p>
 * Description:Json工具类</p>
 * @date 2025/04/15
 */
@Slf4j
public class JsonUtil {
    //定义一个静态ObjectMapper对象,用来处理传入的参数
    private static final ObjectMapper instance= new ObjectMapper();

    public static String toJson(Object object){
        try {
            return instance.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return object.toString();
        }
    }
}
