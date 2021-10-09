package com.wzliulan.mall.admin.controller.member;

import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "会员管理 -> 会员用户服务接口")
@RequestMapping("/user")
@RestController
public class UserController {

    @ApiOperation("用户创建接口")
    @PostMapping("/create")
    @RequiresPermissions("user:add")
    //@RequiresRoles("user/vip/svip")
    public Object create() {
        // TODO
        return ApiResponse.ok("用户创建："+ UUID.randomUUID().toString());
    }

    @ApiOperation("用户删除接口")
    @DeleteMapping("/remove")
    @RequiresPermissions("user:delete")
    public Object remove() {
        // TODO
        return ApiResponse.ok("用户删除："+ UUID.randomUUID().toString());
    }

    @ApiOperation("用户更新接口")
    @PutMapping("/edit")
    @RequiresPermissions("user:update")
    public Object edit() {
        // TODO
        return ApiResponse.ok("用户修改："+ UUID.randomUUID().toString());
    }

    @ApiOperation("用户查找接口")
    @GetMapping("/find")
    @RequiresPermissions("user:find")
    public Object find() {
        // TODO
        return ApiResponse.ok("用户查找："+UUID.randomUUID().toString());
    }

    @ApiOperation("用户搜索接口")
    @GetMapping("/search")
    @RequiresPermissions("user:search1")
    public Object search() {
        // TODO
        return ApiResponse.ok("用户搜索："+UUID.randomUUID().toString());
    }

}
