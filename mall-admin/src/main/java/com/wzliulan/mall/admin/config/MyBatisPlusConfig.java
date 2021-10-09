package com.wzliulan.mall.admin.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis-Plus 配置类
 */
@MapperScan({"com.wzliulan.mall.admin.dao"})
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {
    /**
     * 分页插件：分页拦截器
     * @return 返回分页拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
