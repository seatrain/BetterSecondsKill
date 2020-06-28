package com.seatrain.bettersecondskill.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
@ApiModel(value = "商品详情")
public class GoodsVo {

  private Integer id;

  @ApiModelProperty(value = "商品名称")
  private String name;

  @ApiModelProperty(value = "商品标题")
  private String title;

  @ApiModelProperty(value = "商品描述")
  private String detail;

  @ApiModelProperty(value = "价格")
  private BigDecimal price;

  @ApiModelProperty(value = "库存")
  private Integer stock;
}
