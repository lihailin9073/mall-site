package com.wzliulan.mall.consumer.controller.user;

import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(description = "会员服务 -> 用户服务接口")
@RequestMapping("/user")
@RestController
public class UserController {

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Object register() {
        // TODO
        return ApiResponse.ok("测试端点-创建："+ UUID.randomUUID().toString());
    }
}
