package com.wzliulan.mall.consumer.controller.goods;

import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "商城服务 -> 品牌街接口")
@RequestMapping("/brand")
@RestController
public class BrandController {

    @ApiOperation("甄选品牌")
    @PostMapping("/zxpp")
    public Object zhenxuan() {
        // TODO
        return ApiResponse.ok("甄选品牌："+ UUID.randomUUID().toString());
    }

    @ApiOperation("热榜品牌")
    @DeleteMapping("/rbpp")
    public Object rebang() {
        // TODO
        return ApiResponse.ok("热榜品牌："+ UUID.randomUUID().toString());
    }

}
