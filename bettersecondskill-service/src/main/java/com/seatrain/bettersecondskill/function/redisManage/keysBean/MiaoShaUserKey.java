package com.seatrain.bettersecondskill.function.redisManage.keysBean;

import com.seatrain.bettersecondskill.function.redisManage.BasePrefix;

public class MiaoShaUserKey extends BasePrefix {

  private static final String TOKEN = "token";

  private static final int TOKEN_EXPIRES = 60 * 10;

  public MiaoShaUserKey(String prefix, int expireSeconds) {
    super(prefix, expireSeconds);
  }

  public MiaoShaUserKey(String prefix) {
    super(prefix);
  }

  public static MiaoShaUserKey getToken() {
    return new MiaoShaUserKey(TOKEN, TOKEN_EXPIRES);
  }
}
