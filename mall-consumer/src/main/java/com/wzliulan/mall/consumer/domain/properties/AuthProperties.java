package com.wzliulan.mall.consumer.domain.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 安全管理白名单
 */
@Data
@Component
@ConfigurationProperties( prefix = "mall.auth" )
public class AuthProperties {
    private ArrayList<String> allowList; // 放行白名单
}