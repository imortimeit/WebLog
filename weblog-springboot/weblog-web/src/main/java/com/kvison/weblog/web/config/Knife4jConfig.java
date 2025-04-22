package com.kvison.weblog.web.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author xueliang
 * Title: Knife4jConfig</p>
 * Description:Knife4jConfig配置类，用于接口调试</p>
 * @date 2025/04/21
 */
@Configuration
@EnableSwagger2WebMvc
@Profile("dev")
public class Knife4jConfig {

    @Bean("webApi")
    public Docket createApiDoc(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .groupName("Web 前台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kvison.weblog.web.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    };

    /**
     * 构建API信息
     * @return
     */
    public ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title("Web 前台接口")
                .description("Weblog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。")
                .termsOfServiceUrl("https://github.com/kvison/weblog")
                .contact(new Contact("kvison", "https://github.com/kvison/weblog", "2102321426@qq.com"))
                .version("1.0")
                .build();
    };
}
