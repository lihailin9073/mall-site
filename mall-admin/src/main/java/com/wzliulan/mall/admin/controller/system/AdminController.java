package com.wzliulan.mall.admin.controller.system;


import com.wzliulan.mall.domain.ApiResponse;
import com.wzliulan.mall.admin.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author li.
 * @since 2021-07-23
 */
@Api(description = "系统管理 -> 系统用户服务接口")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    // 1、定义业务控制器，并为每个控制器都定义增删改查、搜索5类接口，同时增加权限注解
    @ApiOperation("测试端点-01")
    @GetMapping("/t1")
    public ApiResponse test1() {
        // TODO
        Map<String, Object> data = new HashMap<>();
        data.put("uuid", UUID.randomUUID().toString());
        data.put("userInfo", adminService.findByName("admin"));
        data.put("userPermissions", adminService.getPermissionsById("1"));

        return ApiResponse.ok(data);
    }

    @ApiOperation("测试端点-02")
    @PostMapping("/t2")
    public ApiResponse test2() {
        // TODO
        return ApiResponse.ok(UUID.randomUUID().toString());
    }

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
