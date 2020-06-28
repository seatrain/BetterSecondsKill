package com.seatrain.bettersecondskill.function.access;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserCheckAndLimit {

  int seconds();

  int maxCount();

  /**
   * 是否需要登录
   */
  boolean needLogin() default true;
}
