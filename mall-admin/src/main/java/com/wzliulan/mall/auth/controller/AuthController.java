package com.wzliulan.mall.auth.controller;

import com.wzliulan.mall.admin.dao.model.Admin;
import com.wzliulan.mall.domain.ApiResponse;
import com.wzliulan.mall.admin.service.IAdminService;
import com.wzliulan.mall.admin.service.IMenuService;
import com.wzliulan.mall.auth.utils.AuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(description = "安全管理 -> 安全服务接口")
@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IAdminService adminService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 获取认证主题 Subject
        Subject subject = SecurityUtils.getSubject();
        // 构造认证令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            // 执行身份认证
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return ApiResponse.error("未知账号");
        } catch (IncorrectCredentialsException ice) {
            return ApiResponse.error("密码不正确");
        } catch (LockedAccountException lae) {
            return ApiResponse.error("账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            return ApiResponse.error("用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            return ApiResponse.error("用户名或密码不正确");
        } catch (Exception e) {
            return ApiResponse.error("认证异常");
        }

        if (subject.isAuthenticated()) { // 认证通过
            Map<String, Object> tokenInfo = new HashMap<>();
            tokenInfo.put("token", subject.getSession().getId());
            tokenInfo.put("expired", subject.getSession().getTimeout());

            return ApiResponse.ok("登录成功", tokenInfo);
        } else {
            token.clear();
            return ApiResponse.error("登录失败");
        }
    }

    // 退出接口：注意请求头必须携带 token，因为它会被CustomSessionManager组件读取并托管至Shiro
    @ApiOperation("退出接口")
    @GetMapping("/logout")
    public Object logout(@RequestHeader("token") String token) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Admin admin = (Admin)subject.getPrincipal();
            subject.logout(); // 退出登录
            return ApiResponse.ok("用户 " + admin.getUsername() + " 已退出登录");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    // 刷新令牌：注意请求头必须携带 token，因为它会被CustomSessionManager组件读取并托管至Shiro
    @ApiOperation("刷新令牌")
    @PutMapping("/refresh-token")
    public Object refreshToken(@RequestHeader("token") String token) {
        /**
         * 说明：由于当前的token令牌实际上就是Shiro中管理的sessionId，因此只要访问当前系统任意一个接口，该token令牌就会自动被续期；
         * 这里的刷新令牌接口，只是为了方便Swagger文档展示而显式的写出来的，实际上想刷新令牌，你只需要在sessionId有效期内访问一下系统中的任意一个接口即可。
         */
        try {
            Subject subject = SecurityUtils.getSubject();
            Map<String, Object> tokenInfo = new HashMap<>();
            tokenInfo.put("token", subject.getSession().getId());
            tokenInfo.put("expired", subject.getSession().getTimeout());
            Admin admin = (Admin)subject.getPrincipal();

            return ApiResponse.ok("用户 "+ admin.getUsername() +" 令牌刷新成功", tokenInfo);
        } catch (InvalidSessionException e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @ApiOperation("获取菜单")
    @GetMapping("/get-menus")
    public Object getMenuList(@RequestHeader("token") String token) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Admin admin = (Admin)subject.getPrincipal();
            return menuService.findUserMenuTree(admin.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Object register(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            String encryptPassword = AuthUtils.encryptPassword(username, password);
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(encryptPassword);
            admin.setCreateDate(new Date());

            boolean save = adminService.save(admin);

            return ApiResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @ApiOperation("未登录的统一响应接口")
    @GetMapping("/un-login")
    public Object unLogin() {
        return ApiResponse.error("您未登录，请登录后再访问！");
    }

    @ApiOperation("无权限的统一响应接口")
    @GetMapping("/un-perms")
    public Object unPermission() {
        return ApiResponse.error("您无权限访问，请联系系统管理员处理！");
    }

}
