package com.wzliulan.mall.admin.controller.goods;


import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "商品管理 -> 品牌服务接口")
@RestController
@RequestMapping("/brand")
public class BrandController {

    @ApiOperation("品牌创建接口")
    @PostMapping("/create")
    @RequiresPermissions("brand:add")
    //@RequiresRoles("user/vip/svip")
    public Object create() {
        // TODO
        return ApiResponse.ok("品牌创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("品牌删除接口")
    @DeleteMapping("/remove")
    @RequiresPermissions("brand:delete")
    public Object remove() {
        // TODO
        return ApiResponse.ok("品牌删除："+ UUID.randomUUID().toString());
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/removes")
    public ApiResponse removes(Integer[] ids) {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

    @ApiOperation("品牌更新接口")
    @PutMapping("/edit")
    @RequiresPermissions("brand:update")
    public Object edit() {
        // TODO
        return ApiResponse.ok("品牌修改："+ UUID.randomUUID().toString());
    }

    @ApiOperation("品牌查找接口")
    @GetMapping("/find")
    @RequiresPermissions("brand:find")
    public Object find() {
        // TODO
        return ApiResponse.ok("品牌查找："+UUID.randomUUID().toString());
    }

    @ApiOperation("品牌搜索接口")
    @GetMapping("/search")
    @RequiresPermissions("brand:search1")
    public Object search() {
        // TODO
        return ApiResponse.ok("品牌搜索："+UUID.randomUUID().toString());
    }

}
