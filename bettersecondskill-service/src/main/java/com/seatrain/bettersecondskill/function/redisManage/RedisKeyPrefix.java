package com.seatrain.bettersecondskill.function.redisManage;

public interface RedisKeyPrefix {

  String getPrefix();

  int getExpireSeconds();
}
