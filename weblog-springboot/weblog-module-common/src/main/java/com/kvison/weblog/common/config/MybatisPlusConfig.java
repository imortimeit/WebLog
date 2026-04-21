package com.kvison.weblog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xueliang
 * Title: MybatisPlusConfig</p>
 * Description:Mybatis Plus 配置文件
 * MapperScan:扫描mp的接口存放位置，数据库相关的代码统一放置在/domain包下
 * dos : 根据阿里的开发规范，统一将数据库对应的实体类命名为 xxxDO 这种形式，统一存放此包下。</p>
 * @date 2025/05/08
 */
@Configuration
@MapperScan("com.kvison.weblog.common.domain.mapper")
public class MybatisPlusConfig {
}
