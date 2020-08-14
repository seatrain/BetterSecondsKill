package com.seatrain.bettersecondskill.function.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 秒杀商品表
 * </p>
 *
 * @author longshibin
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("miaosha_goods")
@ApiModel(value = "MiaoshaGoods对象", description = "秒杀商品表")
public class MiaoshaGoods implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long goodsId;

  private BigDecimal miaoshaPrice;

  @ApiModelProperty(value = "秒杀开始时间")
  private LocalDateTime startDate;

  @ApiModelProperty(value = "秒杀结束时间")
  private LocalDateTime endDate;
}
