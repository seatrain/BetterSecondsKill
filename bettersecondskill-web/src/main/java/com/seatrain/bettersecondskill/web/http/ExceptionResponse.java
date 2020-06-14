package com.seatrain.bettersecondskill.web.http;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 异常处理实体类
 */
@ApiModel(value = "异常处理实体类")
public class ExceptionResponse {

  @ApiModelProperty(value = "异常信息")
  private String message;

  @ApiModelProperty(value = "异常发生时间")
  private String time;

  @ApiModelProperty(value = "异常名称")
  private String exception;

  @ApiModelProperty(value = "异常状态码")
  private int status;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
