package com.wzliulan.mall.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzliulan.mall.consumer.dao.model.Token;
import com.wzliulan.mall.consumer.dao.model.User;
import com.wzliulan.mall.consumer.domain.properties.AuthProperties;
import com.wzliulan.mall.consumer.service.ITokenService;
import com.wzliulan.mall.consumer.service.IUserService;
import com.wzliulan.mall.domain.ApiResponse;
import com.wzliulan.mall.utils.Md5Util;
import com.wzliulan.mall.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(description = "安全管理 -> 安全服务接口")
@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("账号密码登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录账号", required = true),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true)
    })
    @PostMapping("/login")
    public ApiResponse login(String username, String password, @RequestHeader(name = "User-Agent", required = false) String userAgent) {
        // 1、查询用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        User user = userService.getOne(wrapper);
        if (null == user) {
            return ApiResponse.error("用户不存在");
        }

        // 2、验证密码
        if (!user.getPassword().equals(Md5Util.md5(password))) {
            return ApiResponse.error("账号或密码不匹配");
        }

        // 3、生成token令牌
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());  // 令牌值
        token.setExpired(1000L * 60 * 60);  // 有效期，单位：毫秒
        token.setUserId(user.getId()+""); // 登录用户ID
        token.setCreateTime(new Date());
        token.setDevice(userAgent);
        token.setType(0);
        if (tokenService.create(token) <= 0) {
            return ApiResponse.error("令牌创建失败，登录服务异常！");
        }

        // 4、返回令牌
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("token", token);
        return ApiResponse.ok(result);
    }

    @ApiOperation("手机验证码登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true),
            @ApiImplicitParam(name = "verifyCode", value = "短信验证码", required = true)
    })
    @PostMapping("/login-by-phone")
    public ApiResponse loginByPhone(String phone, String verifyCode, @RequestHeader(name = "User-Agent", required = false) String userAgent) {
        // 1、查询用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        User user = userService.getOne(wrapper);
        if (null == user) {
            return ApiResponse.error("用户不存在");
        }

        // 2、校验验证码
        String vcode = redisUtil.get(phone + "@" + verifyCode);
        if (vcode==null || vcode.isEmpty()) {
            return ApiResponse.error("验证码错误！");
        }

        // 3、生成token令牌
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());  // 令牌值
        token.setExpired(1000L * 60 * 60);  // 有效期，单位：毫秒
        token.setUserId(user.getId()+""); // 登录用户ID
        token.setCreateTime(new Date());
        token.setDevice(userAgent);
        token.setType(0);
        if (tokenService.create(token) <= 0) {
            return ApiResponse.error("令牌创建失败，登录服务异常！");
        }

        // 4、返回令牌
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("token", token);
        return ApiResponse.ok(result);
    }


    @ApiOperation("微信号授权登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wid", value = "微信全局ID", required = true),
            @ApiImplicitParam(name = "accessToken", value = "微信授权令牌", required = true)
    })
    @PostMapping("/login-by-wechat")
    public ApiResponse loginByWechat(String wid, String accessToken, @RequestHeader(name = "User-Agent", required = false) String userAgent) {
        // 1、调用微信SDK

        // 2、绑定手机号码

        // 3、关联已有账号

        // 4、生成token令牌

        // 5、返回令牌
        return ApiResponse.ok();
    }

    @ApiOperation("退出接口")
    @ApiImplicitParam(name = "token", value = "登录令牌", required = true)
    @GetMapping("/logout")
    public ApiResponse logout(String token) {
        try {
            tokenService.remove(token);
            return ApiResponse.ok("系统退出成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("退出失败，系统稍后将自动退出您的账号！");
        }
    }

    @ApiOperation("刷新令牌")
    @PutMapping("/refresh-token")
    public ApiResponse refreshToken(@RequestHeader("token") String token) {
        boolean refresh = tokenService.refresh(token);
        if (refresh) {
            return ApiResponse.ok("刷新成功！");
        }
        return ApiResponse.error("刷新失败！");
    }

    @ApiOperation("获取接口权限列表")
    @GetMapping("/get-menus")
    public ApiResponse getMenuList(@RequestHeader("token") String token) {
        if (tokenService.isValid(token)) {
            Token tk = tokenService.find(token);
            List<Map> permissionList = userService.findPermissionList(Integer.parseInt(tk.getUserId()));
            return ApiResponse.ok(permissionList);
        }
        return ApiResponse.error("令牌无效！");
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiResponse register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phone") String phone, String token) {
        // 检查注册验证码
        if (null==token || token.isEmpty()) {
            return ApiResponse.error("注册验证码已失效！");
        }

        // 保存用户资料
        User user = new User();
        user.setUserName(username);
        user.setPassword(Md5Util.md5(password));
        user.setPhone(phone);
        user.setCreateTime(new Date());

        if (userService.save(user)) {
            return ApiResponse.ok("用户注册成功！");
        }
        return ApiResponse.error("用户注册失败！");
    }
}
