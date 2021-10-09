package com.wzliulan.mall.consumer.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 总后台角色表，即部门
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_role")
@ApiModel(value="Role对象", description="总后台角色表，即部门")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键：角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "会员标志码：user=普通用户，vip=高级用户，svip=贵宾用户")
    private String code;

    @ApiModelProperty(value = "角色描述备注")
    private String remark;

    @ApiModelProperty(value = "是否删除：0=未删除，1=已删除")
    private String isDelete;


}
