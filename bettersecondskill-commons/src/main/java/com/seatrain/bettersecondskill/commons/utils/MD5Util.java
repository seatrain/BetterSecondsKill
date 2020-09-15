package com.seatrain.bettersecondskill.commons.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

  // 固定盐
  public static final String salt = "seatrain";

  /**
   * md5加密方法
   */
  public static String md5(String s) {
    return DigestUtils.md5Hex(s);
  }

  /**
   * 将用户输入的密码和固定的salt进行组装
   *
   * @param formPassword 用户输入的密码
   * @return md5加密之后的字符串
   */
  public static String formPassword2DBPassword(String formPassword) {
    String packedPassword = salt.substring(0, salt.length() / 2) + formPassword + salt.substring(salt.length() / 2 + 1);

    return md5(packedPassword);
  }
}
