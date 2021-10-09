package com.wzliulan.mall.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResultResDto响应枚举类
 */
@Getter
@AllArgsConstructor
public enum ApiResponseEnum {

    SUCCESS(200, "操作成功"),

    ERROR(-200, "操作失败"),

    UNAUTHENTICATED(401, "请先通过身份认证"), AUTH_FAIL(1400, "认证失败"),

    // token异常
    TOKEN_PAST(1401, "身份过期，请求重新登录！"), TOKEN_ERROR(1402, "令牌错误"),

    HEADEA_ERROR(1403, "请求头错误"),

    AUTH_USERNAME_NONE(1405, "用户名不能为空"), AUTH_PASSWORD_NONE(1406, "密码不能为空"),

    MENU_NO(306, "没此权限，请联系管理员！");

    private Integer code;
    private String desc;
}
