package com.wzliulan.mall.admin.domain.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取Database配置
 */
@Data
@Component
@ConfigurationProperties( prefix = "spring.datasource.druid" )
public class DatabaseProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize = 5;
}