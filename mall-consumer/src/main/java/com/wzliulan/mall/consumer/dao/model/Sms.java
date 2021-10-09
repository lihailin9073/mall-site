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
 * 短信表
 * </p>
 *
 * @author li.
 * @since 2021-10-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_sms")
@ApiModel(value="Sms对象", description="短信表")
public class Sms implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "短信内容")
    private String content;

    @ApiModelProperty(value = "短信类型：0=登录验证码，1=注册验证码，3=支付验证码")
    private Integer type;

    private Date createTime;


}
