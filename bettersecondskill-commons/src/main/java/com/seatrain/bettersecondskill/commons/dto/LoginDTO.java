package com.seatrain.bettersecondskill.commons.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {

  @NotEmpty
  @ApiModelProperty(value = "用户姓名", required = true)
  private String userName;

  @NotEmpty
  @ApiModelProperty(value = "密码", required = true)
  private String password;
}
