package com.seatrain.bettersecondskill.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 秒杀商品信息实体类
 *
 * @author longsb
 * @since 2020/08/14
 */
@ApiModel(value = "秒杀商品信息")
@Data
public class MiaoshaoGoodsVo {

  @ApiModelProperty(value = "id")
  private Long id;

  @ApiModelProperty(value = "商品id")
  private Long goodsId;

  @ApiModelProperty(value = "秒杀价格")
  private BigDecimal miaoshaPrice;

  @ApiModelProperty(value = "秒杀开始时间")
  private LocalDateTime startDate;

  @ApiModelProperty(value = "秒杀结束时间")
  private LocalDateTime endDate;
}
