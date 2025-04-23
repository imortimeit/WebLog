package com.kvison.weblog.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import javafx.scene.input.DataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * @author xueliang
 * Title: JacksonConfig</p>
 * Description:自定义jackson</p>
 * @date 2025/04/22
 */
@Configuration
public class JacksonConfig {

    /**
     * 1,定义一个Spring Bean
     * 在JacksonConfig类中，objectMapper()方法上添加了@Bean注解，表示这个方法的返回值（ObjectMapper对象）会被注册到Spring的应用上下文中，成为一个可以被依赖注入的Bean。
     * 2.支持依赖注入
     * 通过@Bean注解，其他组件可以通过依赖注入的方式获取到这个ObjectMapper实例，而不需要手动创建。
     * 2.配置全局对象
     * 在这里，ObjectMapper是一个全局的对象，用于处理JSON序列化和反序列化。通过@Bean注解，确保整个应用中使用的是同一个配置好的ObjectMapper实例。
     * &#064;Bean注解在这里的作用是将objectMapper()方法返回的ObjectMapper对象注册为Spring容器中的一个Bean，使得它可以被其他组件依赖注入并复用，同时确保全局统一的JSON处理配置。
     *
     * 此配置类在项目启动的时候执行。
     * 在项目启动的时候，会自动执行这个方法，然后返回一个ObjectMapper对象，这个对象会被注册到Spring容器中，可以被其他组件依赖注入并复用。
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //忽略未知字段，前端如果传入后端未定义的字段，则忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //定义一个JavaTimeModule指定序列化和反序列化的规则
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        //支出LocalDateTime、LocalDate、LocalTime等时间类型
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        objectMapper.registerModule(javaTimeModule);

        //设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        //设置凡是返参值为null的字段，都忽略，根据实际情况是否开启
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper;
    }
}


