package com.wzliulan.mall.consumer.controller.notice;


import com.wzliulan.mall.consumer.service.ISmsService;
import com.wzliulan.mall.consumer.service.impl.SmsServiceImpl;
import com.wzliulan.mall.domain.ApiResponse;
import com.wzliulan.mall.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>
 * 短信表 前端控制器
 * </p>
 *
 * @author li.
 * @since 2021-10-03
 */
@Api(description = "通知管理 -> 短信接口")
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private ISmsService smsService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("获取注册验证码")
    @PostMapping("/get-register-code")
    public ApiResponse registerVerifyCode(@RequestParam("phone") String phone) {
        String vcode = SmsServiceImpl.generateCode(6);
        String content = "您的注册验证码是：" + vcode;
        smsService.send(phone, content, 0);
        redisUtil.set(phone+"@"+vcode, vcode, 180); // 注册验证码3分钟有效
        return ApiResponse.ok(content);
    }

    @ApiOperation("获取登录验证码")
    @PostMapping("/get-login-code")
    public ApiResponse loginVerifyCode(@RequestParam("phone") String phone) {
        String vcode = SmsServiceImpl.generateCode();
        String content = "您的登录验证码是：" + vcode;
        smsService.send(phone, content, 0);
        redisUtil.set(phone+"@"+vcode, vcode, 60); // 登录验证码1分钟有效
        return ApiResponse.ok(content);
    }
}
