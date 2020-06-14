package com.seatrain.bettersecondskill.web.http;

import org.springframework.http.HttpStatus;

public class CustomizedResponseEntity<T> {

  /**
   * 状态码
   */
  private int status;

  /**
   * 描述
   */
  private String message;

  /**
   * 数据
   */
  private T data;

  public CustomizedResponseEntity(int status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public static <T> CustomizedResponseEntity<T> ok(T data) {
    return new CustomizedResponseEntity<T>(HttpStatus.OK.value(), "成功", data);
  }

  public static <T> CustomizedResponseEntity<T> ok() {
    return new CustomizedResponseEntity<T>(HttpStatus.OK.value(), "成功", null);
  }
}
