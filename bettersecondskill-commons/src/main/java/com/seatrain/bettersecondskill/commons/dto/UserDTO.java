package com.seatrain.bettersecondskill.commons.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDTO {

  @ApiModelProperty(value = "用户姓名", required = true)
  @NotEmpty
  private String name;

  @ApiModelProperty(value = "密码", required = true)
  @NotEmpty
  private String password;

  @ApiModelProperty(value = "头像url")
  private String headUrl;
}
