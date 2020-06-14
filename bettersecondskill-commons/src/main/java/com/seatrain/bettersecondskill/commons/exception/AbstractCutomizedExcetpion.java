package com.seatrain.bettersecondskill.commons.exception;

public class AbstractCutomizedExcetpion extends RuntimeException {

  static final long serialVersionUID = -3392484140015020871L;

  protected int status;

  public AbstractCutomizedExcetpion(String message) {
    super(message);
  }

  public AbstractCutomizedExcetpion(String message, Exception e) {
    super(message, e);
  }

  protected void setHttpStatus(int httpStatus) {
    this.status = httpStatus;
  }

  public int getStatus() {
    return this.status;
  }
}
