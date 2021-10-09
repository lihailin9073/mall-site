package com.wzliulan.mall.consumer.controller.goods;

import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "店铺服务 -> 店铺服务接口")
@RequestMapping("/shop")
@RestController
public class ShopController {

    @ApiOperation("测试端点-查找接口")
    @GetMapping("/find")
    public Object find() {
        // TODO
        return ApiResponse.ok("测试端点-查找："+UUID.randomUUID().toString());
    }

    @ApiOperation("测试端点-搜索接口")
    @GetMapping("/search")
    public Object search() {
        // TODO
        return ApiResponse.ok("测试端点-搜索："+UUID.randomUUID().toString());
    }
}
