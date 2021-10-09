package com.wzliulan.mall.admin.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author li.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="系统用户创建Dto", description="系统用户创建Dto")
public class AdminCreateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("商品ID")
    private String goodsId;

    @ApiModelProperty("标签名称")
    private String tagWords;

}
