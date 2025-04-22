package com.kvison.weblog.admin.config;

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
 * Title: Knife4jAdminConfig</p>
 * Description:此处遇到一个问题，我无意之间对admin模块使用了gradle，导致无论怎样都无法使用swagger2的相关依赖，取消gradle后报错解决</p>
 * @date 2025/04/22
 */
@Configuration
@EnableSwagger2WebMvc
@Profile("dev") //默认开发环境使用，避免正式系统向外界暴露敏感数据
public class Knife4jAdminConfig {

    @Bean("adminApi")
    public Docket createApiDoc(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .groupName("Admin 后台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kvison.weblog.admin.controller"))
                .paths(PathSelectors.any())
                .build();
    };

    public ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title("Admin 后台接口")
                .description("Weblog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。")
                .termsOfServiceUrl("https://github.com/kvison/weblog")
                .contact(new Contact("kvison", "https://github.com/kvison/weblog", "2102321426@qq.com"))
                .version("1.0")
                .build();
    }

}
