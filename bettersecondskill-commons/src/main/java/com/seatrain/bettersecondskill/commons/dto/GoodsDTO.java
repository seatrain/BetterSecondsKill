package com.seatrain.bettersecondskill.commons.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@ApiModel(value = "商品创建信息")
public class GoodsDTO {

  @NotEmpty
  @ApiModelProperty(value = "名称", required = true)
  private String name;

  @NotEmpty
  @ApiModelProperty(value = "标题", required = true)
  private String title;

  @NotEmpty
  @ApiModelProperty(value = "描述", required = true)
  private String detail;

  @DecimalMin(value = "0")
  @ApiModelProperty(value = "价格", required = true)
  private BigDecimal price;

  @Min(value = 0)
  @ApiModelProperty(value = "库存", required = true)
  private Integer stock;
}
