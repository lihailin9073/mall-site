package com.wzliulan.mall.consumer.controller.notice;


import com.wzliulan.mall.consumer.service.impl.SmsServiceImpl;
import com.wzliulan.mall.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 邮件表 前端控制器
 * </p>
 *
 * @author li.
 * @since 2021-10-03
 */
@Api(description = "通知管理 -> 邮件接口")
@RestController
@RequestMapping("/email")
public class EmailController {

    @ApiOperation("获取注册验证码")
    @PostMapping("/get-register-code")
    public ApiResponse registerVerifyCode(@RequestParam("email") String email) {
        // TODO 发送邮件方式的注册验证码
        return ApiResponse.ok("您的注册验证码是："+ SmsServiceImpl.generateCode());
    }

    @ApiOperation("获取登录验证码")
    @PostMapping("/get-login-code")
    public ApiResponse loginVerifyCode(@RequestParam("email") String email) {
        // TODO 发送邮件方式的登录验证码
        return ApiResponse.ok("您的登录验证码是："+ SmsServiceImpl.generateCode());
    }
}
