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
 * 总后台菜单权限表
 * </p>
 *
 * @author li.
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_menu")
@ApiModel(value="Menu对象", description="总后台菜单权限表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单或权限父ID: 0为顶层菜单")
    private Integer pid;

    @ApiModelProperty(value = "权限URI，比如：/user/edit")
    private String action;

    @ApiModelProperty(value = "权限（菜单）名称")
    private String name;

    @ApiModelProperty(value = "菜单的图标css类如: fa-home")
    private String icon;

    @ApiModelProperty(value = "权限类型：1目录，2菜单，3=按钮（即权限码）")
    private Integer type;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否显示：0=隐藏，1=显示")
    private String isShow;


}
