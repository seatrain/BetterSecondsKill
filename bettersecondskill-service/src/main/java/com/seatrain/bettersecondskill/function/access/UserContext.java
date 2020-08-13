package com.seatrain.bettersecondskill.function.access;

import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;

/**
 * 用户容器
 */
public class UserContext {

  private static ThreadLocal<MiaoShaUser> userHolder = new ThreadLocal<>();

  public static void setUser(MiaoShaUser miaoShaUser) {
    userHolder.set(miaoShaUser);
  }

  public static MiaoShaUser getUser() {
    return userHolder.get();
  }

  public static void removeUser() {
    userHolder.remove();
  }

}
