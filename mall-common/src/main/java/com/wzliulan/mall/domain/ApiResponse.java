package com.wzliulan.mall.domain;

import com.alibaba.fastjson.JSON;
import com.wzliulan.mall.domain.enums.ApiResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 用于封装接口统一响应结果
 */
@Data
@NoArgsConstructor // 无参构造方法
@AllArgsConstructor // 有参构造方法
@ApiModel(description = "接口响应实体类")
public final class ApiResponse implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ApiResponse.class);

    private static final long serialVersionUID = 1L;

    /**
     * 响应业务状态码
     */
    @ApiModelProperty("状态码")
    private Integer code;

    /**
     * 响应信息
     */
    @ApiModelProperty("描述信息")
    private String message;

    /**
     * 响应中的数据
     */
    @ApiModelProperty("返回的业务数据")
    private Object data;

    public static ApiResponse ok() {
//        String message = "操作失败";
//        return new ApiResponse(ApiResponseEnum.SUCCESS.getCode(), message, null);
        return new ApiResponse(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getDesc(), null);
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getDesc(), data);
    }

    public static ApiResponse ok(String message, Object data) {
        return new ApiResponse(ApiResponseEnum.SUCCESS.getCode(), message, data);
    }

    public static ApiResponse error() {
        String message = "操作失败";
        logger.debug("返回错误：code={}, message={}", ApiResponseEnum.ERROR.getCode(), message);
        return new ApiResponse(ApiResponseEnum.ERROR.getCode(), message, null);
    }

    public static ApiResponse error(String message) {
        logger.debug("返回错误：code={}, message={}", ApiResponseEnum.ERROR.getCode(), message);
        return new ApiResponse(ApiResponseEnum.ERROR.getCode(), message, null);
    }

    public static ApiResponse error(String message, Object data) {
        return new ApiResponse(ApiResponseEnum.ERROR.getCode(), message, data);
    }

    public static ApiResponse build(int code, String message) {
        logger.debug("返回结果：code={}, message={}", code, message);
        return new ApiResponse(code, message, null);
    }

    public static ApiResponse build(ApiResponseEnum apiResponseEnum) {
        logger.debug("返回结果：code={}, message={}", apiResponseEnum.getCode(), apiResponseEnum.getDesc());
        return new ApiResponse(apiResponseEnum.getCode(), apiResponseEnum.getDesc(), null);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}