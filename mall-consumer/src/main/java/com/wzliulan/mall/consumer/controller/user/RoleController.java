package com.wzliulan.mall.consumer.controller.user;


import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <p>
 * 消费者端角色表，即部门 前端控制器
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
@Api(description = "会员服务 -> 角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @ApiOperation("测试端点-查找接口")
    @GetMapping("/find")
    public Object find() {
        // TODO
        return ApiResponse.ok("测试端点-查找："+ UUID.randomUUID().toString());
    }
}
