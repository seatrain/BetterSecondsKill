package com.seatrain.bettersecondskill.commons.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends AbstractCutomizedExcetpion {

  public AccessDeniedException() {
    super("用户无权访问或服务器拒绝");
    setHttpStatus(HttpStatus.FORBIDDEN.value());
  }

  public AccessDeniedException(String message) {
    super(message);
    setHttpStatus(HttpStatus.FORBIDDEN.value());
  }
}
