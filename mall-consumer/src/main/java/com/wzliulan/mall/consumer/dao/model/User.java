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
@TableName("m_user")
@ApiModel(value="User对象", description="系统管理员表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登录账号")
    private String userName;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "用户头像")
    private String logo;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "是否删除：0=未删除，1=已删除")
    private String isDelete;

    private Date createTime;

    private Date updateTime;


}
