package com.seatrain.bettersecondskill.function.redisManage.keysBean;

import com.seatrain.bettersecondskill.function.redisManage.BasePrefix;

public class CodeKey extends BasePrefix {

  private static final String VERIFICATION_CODE_PREFIX = "rc";
  private static final int VERIFICATION_CODE_EXPIRES = 300;

  public CodeKey(String prefix, int expireSeconds) {
    super(prefix, expireSeconds);
  }

  public static CodeKey verificationCode() {
    return new CodeKey(VERIFICATION_CODE_PREFIX, VERIFICATION_CODE_EXPIRES);
  }

}
