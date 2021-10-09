package com.wzliulan.mall.admin.exception;

import com.wzliulan.mall.domain.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 * @author li.
 */
@Slf4j
@ControllerAdvice
public class AdminException {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class) // Spring的Http请求处理异常
    @ResponseBody
    public Object handleShiroException(Exception ex) {
        log.info("当前请求，Spring处理Http失败：" + ex.getMessage());
        return ApiResponse.error("Spring处理Http失败", ex.getMessage());
    }
}
