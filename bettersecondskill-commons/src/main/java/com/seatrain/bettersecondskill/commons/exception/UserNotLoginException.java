package com.seatrain.bettersecondskill.commons.exception;

public class UserNotLoginException extends AbstractCutomizedExcetpion {

  public UserNotLoginException() {
    super("当前用户未登录或登录失效");
    setHttpStatus(97);
  }

}
