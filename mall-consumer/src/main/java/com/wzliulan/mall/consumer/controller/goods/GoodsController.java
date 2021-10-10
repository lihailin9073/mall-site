package com.wzliulan.mall.consumer.controller.goods;

import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "商城服务 -> 商品接口")
@RequestMapping("/goods")
@RestController
public class GoodsController {

    @ApiOperation("首页默认商品列表")
    @PostMapping("/index")
    public Object index() {
        // TODO
        return ApiResponse.ok("测试端点-创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("首页推荐栏目商品列表")
    @PostMapping("/tuijian")
    public Object tuijian() {
        // TODO
        return ApiResponse.ok("测试端点-创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("首页促销栏目商品列表")
    @PostMapping("/cuxiao")
    public Object cuxiao() {
        // TODO
        return ApiResponse.ok("测试端点-创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("购物车默认推荐栏目商品列表")
    @PostMapping("/gouwuche-m")
    public Object gouwucheM() {
        // TODO
        return ApiResponse.ok("测试端点-创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("购物车个性推荐栏目商品列表")
    @PostMapping("/gouwuche-gexing")
    public Object gouwucheG() {
        // TODO
        return ApiResponse.ok("测试端点-创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("商品详情")
    @GetMapping("/detail")
    public Object find() {
        // TODO
        return ApiResponse.ok("测试端点-查找："+UUID.randomUUID().toString());
    }

    @ApiOperation("商品快搜")
    @GetMapping("/search")
    public Object search() {
        // TODO
        return ApiResponse.ok("测试端点-搜索："+UUID.randomUUID().toString());
    }

}
