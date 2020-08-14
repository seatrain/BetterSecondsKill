package com.seatrain.bettersecondskill.commons.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 秒杀商品创建参数
 *
 * @author longsb
 * @since 2020/08/14
 */
@ApiModel(value = "秒杀商品创建参数")
@Data
public class MiaoshaGoodsDTO {

  @ApiModelProperty(value = "商品id", required = true)
  private Long goodsId;

  @ApiModelProperty(value = "秒杀开始时间", required = true)
  private LocalDateTime startDate;

  @ApiModelProperty(value = "秒杀结束时间", required = true)
  private LocalDateTime endDate;

  @ApiModelProperty(value = "秒杀价格", required = true)
  private BigDecimal miaoshaPrice;
}
