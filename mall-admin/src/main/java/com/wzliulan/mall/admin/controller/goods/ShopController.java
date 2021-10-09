package com.wzliulan.mall.admin.controller.goods;


import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "店铺管理 -> 店铺服务接口")
@RestController
@RequestMapping("/shop")
public class ShopController {

    @ApiOperation("店铺创建接口")
    @PostMapping("/create")
    @RequiresPermissions("shop:add")
    //@RequiresRoles("user/vip/svip")
    public Object create() {
        // TODO
        return ApiResponse.ok("店铺创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("店铺删除接口")
    @DeleteMapping("/remove")
    @RequiresPermissions("shop:delete")
    public Object remove() {
        // TODO
        return ApiResponse.ok("店铺删除："+ UUID.randomUUID().toString());
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/removes")
    public ApiResponse removes(Integer[] ids) {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("店铺更新接口")
    @PutMapping("/edit")
    @RequiresPermissions("shop:update")
    public Object edit() {
        // TODO
        return ApiResponse.ok("店铺修改："+ UUID.randomUUID().toString());
    }

    @ApiOperation("店铺查找接口")
    @GetMapping("/find")
    @RequiresPermissions("shop:find")
    public Object find() {
        // TODO
        return ApiResponse.ok("店铺查找："+UUID.randomUUID().toString());
    }

    @ApiOperation("店铺搜索接口")
    @GetMapping("/search")
    @RequiresPermissions("shop:search1")
    public Object search() {
        // TODO
        return ApiResponse.ok("店铺搜索："+UUID.randomUUID().toString());
    }

}
