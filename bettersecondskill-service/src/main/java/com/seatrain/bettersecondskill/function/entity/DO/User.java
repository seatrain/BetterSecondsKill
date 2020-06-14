package com.seatrain.bettersecondskill.function.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author longshibin
 * @since 2020-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String name;
  
  private String salt;

  private String password;

  @ApiModelProperty(value = "头像url")
  private String headUrl;

  @ApiModelProperty(value = "注册日期")
  private LocalDateTime registerDate;

  @ApiModelProperty(value = "最近一次登录时间")
  private LocalDateTime lastLoginDate;

  @ApiModelProperty(value = "最近一次登录的ip")
  private Integer lastLoginIp;


}
