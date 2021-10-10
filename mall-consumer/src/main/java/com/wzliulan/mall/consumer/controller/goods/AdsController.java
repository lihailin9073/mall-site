package com.wzliulan.mall.consumer.controller.goods;

import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(description = "商城服务 -> 广告接口")
@RequestMapping("/ads")
@RestController
public class AdsController {

    @ApiOperation("PC首页顶部Banner广告")
    @GetMapping("/pc-index-top-banner")
    public Object pcIndexTopBanner() {
        // TODO
        return ApiResponse.ok("测试端点-查找："+UUID.randomUUID().toString());
    }

    @ApiOperation("PC首页幻灯片轮播广告")
    @GetMapping("/pc-index-slide-banner")
    public Object pcIndexSlideBanner() {
        // TODO
        return ApiResponse.ok("测试端点-搜索："+UUID.randomUUID().toString());
    }
}
