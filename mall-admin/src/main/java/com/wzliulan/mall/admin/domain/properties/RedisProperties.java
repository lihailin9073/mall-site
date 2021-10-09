package com.wzliulan.mall.admin.domain.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取Redis配置
 */
@Data
@Component
@ConfigurationProperties( prefix = "spring.redis" )
public class RedisProperties {
    private String host = "localhost";
    private String password = "123456";
    private int port = 6379;
}

