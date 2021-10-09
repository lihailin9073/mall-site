package com.wzliulan.mall.admin.controller.open;

import com.wzliulan.mall.admin.domain.properties.DatabaseProperties;
import com.wzliulan.mall.admin.domain.properties.RedisProperties;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 获取配置的功能测试接口
 *
 */
@Api(description = "测试模块 -> 配置获取接口")
@RequestMapping("/open")
@RestController
public class PropertiesController {
    /** @Value方式获取配置 */
    @Value("${mall.brand.type}")
    private String brandType;

    /** @ConfigurationProperties方式获取配置 */
    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private DatabaseProperties databaseProperties;

    // 读取自定义配置
    @GetMapping("/get-vue")
    public Object getByValue() {
        return brandType;
    }

    // 读取Redis的配置
    @GetMapping("/get-redis")
    public Object getRedisInfo() {
        return redisProperties;
    }

    // 读取Db的配置
    @GetMapping("/get-db")
    public Object getDbInfo() {
        return databaseProperties;
    }

}
