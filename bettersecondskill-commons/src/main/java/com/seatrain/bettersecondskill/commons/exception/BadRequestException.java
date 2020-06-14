package com.seatrain.bettersecondskill.commons.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractCutomizedExcetpion {

  public BadRequestException(String message) {
    super(message);
    setHttpStatus(HttpStatus.BAD_REQUEST.value());
  }
}
