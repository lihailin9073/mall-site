package com.wzliulan.mall.admin.controller;

import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口的默认首页
 *   提供这个控制器是为了避免直接访问 api 的 / 地址时，页面显示500内部错误，如果不嫌弃这种错误难看可以删掉这个控制器
 */
@Api(description = "接口服务 -> 接口默认首页")
@RestController
public class IndexController {

    @ApiOperation("接口默认首页")
    @RequestMapping("/")
    public Object index() {
        return ApiResponse.ok("API版本：Version 1.0.0");
    }
}
