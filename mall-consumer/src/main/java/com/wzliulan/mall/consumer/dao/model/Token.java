package com.wzliulan.mall.consumer.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统管理员表
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_token")
@ApiModel(value="Token对象", description="访问令牌表")
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    @ApiModelProperty(value = "登录令牌")
    private String token;

    @ApiModelProperty(value = "有效期（毫秒）")
    private Long expired;

    @ApiModelProperty(value = "真实姓名")
    private String device;

    @ApiModelProperty(value = "token类型：0=管理后台，1=门户网站，2=手机APP")
    private Integer type;

    private Date createTime;


}
