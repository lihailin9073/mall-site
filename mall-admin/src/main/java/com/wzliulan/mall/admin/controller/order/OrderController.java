package com.wzliulan.mall.admin.controller.order;


import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "订单管理 -> 品牌服务接口")
@RestController
@RequestMapping("/order")
public class OrderController {

    @ApiOperation("增加")
    @PostMapping("/add")
    public ApiResponse add() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("删除")
    @DeleteMapping("/remove")
    public ApiResponse remove(Integer id) {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/removes")
    public ApiResponse removes(Integer[] ids) {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("修改")
    @PutMapping("/edit")
    public ApiResponse edit() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("查找")
    @GetMapping("/find")
    public ApiResponse find() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("搜索")
    @PostMapping("/search")
    public ApiResponse search() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }
}
