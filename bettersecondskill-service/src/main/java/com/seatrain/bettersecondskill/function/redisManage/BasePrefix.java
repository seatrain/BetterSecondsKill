package com.seatrain.bettersecondskill.function.redisManage;

public class BasePrefix implements RedisKeyPrefix {

  private String prefix;

  private int expireSeconds;

  public BasePrefix(String prefix, int expireSeconds) {
    this.prefix = prefix;
    this.expireSeconds = expireSeconds;
  }

  public BasePrefix(String prefix) {
    this.prefix = prefix;
    this.expireSeconds = 0;
  }

  @Override
  public String getPrefix() {
    return this.prefix;
  }

  @Override
  public int getExpireSeconds() {
    return this.expireSeconds;
  }
}
