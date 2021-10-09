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
 * 消费者端菜单权限表 前端控制器
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
@Api(description = "会员服务 -> 菜单服务接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @ApiOperation("测试端点-查找接口")
    @GetMapping("/find")
    public Object find() {
        // TODO
        return ApiResponse.ok("测试端点-查找："+ UUID.randomUUID().toString());
    }
}
