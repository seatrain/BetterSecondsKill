package com.seatrain.bettersecondskill.function.redisManage.keysBean;

import com.seatrain.bettersecondskill.function.redisManage.BasePrefix;

public class CodeKey extends BasePrefix {

  private static final String VERIFICATION_CODE_PREFIX = "rc";
  private static final int VERIFICATION_CODE_EXPIRES = 300;

  private static final String MIAO_SHA_PATH_PREFIX = "rc";
  private static final int MIAO_SHA_PATH_EXPIRES = 60;

  private static final CodeKey VERIFICATION_CODE = new CodeKey(VERIFICATION_CODE_PREFIX, VERIFICATION_CODE_EXPIRES);

  private static final CodeKey MIAO_SHA_PATH = new CodeKey(MIAO_SHA_PATH_PREFIX, MIAO_SHA_PATH_EXPIRES);

  public CodeKey(String prefix, int expireSeconds) {
    super(prefix, expireSeconds);
  }

  public static CodeKey verificationCode() {
    return VERIFICATION_CODE;
  }

  public static CodeKey miaoShaPath() {
    return MIAO_SHA_PATH;
  }
}
