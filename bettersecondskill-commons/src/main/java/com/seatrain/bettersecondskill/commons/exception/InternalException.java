package com.seatrain.bettersecondskill.commons.exception;

public class InternalException extends AbstractCutomizedExcetpion {

  public InternalException(String message) {
    super(message);
    setHttpStatus(500);
  }
}
