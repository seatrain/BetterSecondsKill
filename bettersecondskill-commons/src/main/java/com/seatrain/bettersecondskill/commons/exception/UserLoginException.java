package com.seatrain.bettersecondskill.commons.exception;

public class UserLoginException extends AbstractCutomizedExcetpion{

  public UserLoginException(String message) {
    super(message);
    setHttpStatus(401);
  }
}
