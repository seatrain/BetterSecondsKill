package com.seatrain.bettersecondskill.web.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import springfox.documentation.RequestHandler;

public class MutipartBasePackageUtil {

  public static Predicate<RequestHandler> basePackage(final String basePackage) {
    return new Predicate<RequestHandler>() {

      @Override
      public boolean apply(RequestHandler input) {
        return declaringClass(input).transform(handlerPackage(basePackage)).or(true);
      }
    };
  }

  private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
    return new Function<Class<?>, Boolean>() {

      @Override
      public Boolean apply(Class<?> input) {
        for (String strPackage : basePackage.split(",")) {
          boolean isMatch = input.getPackage().getName().startsWith(strPackage);
          if (isMatch) {
            return true;
          }
        }
        return false;
      }
    };
  }

  @SuppressWarnings("deprecation")
  private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
    return Optional.fromNullable(input.declaringClass());
  }
}
