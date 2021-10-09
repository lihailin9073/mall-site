package com.wzliulan.mall.auth.exception;

import com.wzliulan.mall.domain.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 安全管理异常处理器
 * @author li.
 */
@Slf4j
@ControllerAdvice
public class AuthException {

    @ExceptionHandler(UnauthorizedException.class) // 权限不足异常
    @ResponseBody
    public Object handleShiroException(Exception ex) {
        log.info("当前请求，无权限访问：" + ex.getMessage());
        return ApiResponse.error("无权限访问", ex.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class) // 鉴权失败异常
    @ResponseBody
    public Object authorizationException(Exception ex) {
        log.info("当前请求，权限鉴别失败：" + ex.getMessage());
        return ApiResponse.error("权限鉴别失败", ex.getMessage());
    }
}
