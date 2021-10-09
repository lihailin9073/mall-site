package com.wzliulan.mall.supplier.controller;

import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "测试模块 -> 测试端点接口")
@RequestMapping("/open/test")
@RestController
public class TestController {

    @ApiOperation("测试端点-创建接口")
    @PostMapping("/create")
    public Object create() {
        // TODO
        return ApiResponse.ok("测试端点-创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("测试端点-删除接口")
    @DeleteMapping("/remove")
    public Object remove() {
        // TODO
        return ApiResponse.ok("测试端点-删除："+ UUID.randomUUID().toString());
    }

    @ApiOperation("测试端点-批量删除接口")
    @DeleteMapping("/removes")
    public ApiResponse removes(Integer[] ids) {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("测试端点-更新接口")
    @PutMapping("/edit")
    public Object edit() {
        // TODO
        return ApiResponse.ok("测试端点-修改："+ UUID.randomUUID().toString());
    }

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
