package com.seatrain.bettersecondskill.function.access;

import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import com.seatrain.bettersecondskill.commons.exception.AccessDeniedException;
import com.seatrain.bettersecondskill.commons.exception.UserNotLoginException;
import com.seatrain.bettersecondskill.function.redisManage.RedisClient;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserCheckAndLimit注解的切面实现
 *
 * @author seatrain
 * @version 1.0
 * @date 2020/08/25 11:30
 */
@Slf4j
@Aspect
@Component
public class UserCheckAndLimitAOP {

  @Autowired
  private RedisClient redisClient;

  @Pointcut("@annotation(com.seatrain.bettersecondskill.function.access.UserCheckAndLimit)")
  public void scope() {

  }

  @Before("scope()")
  public void jointPoint(JoinPoint joinPoint) throws NoSuchMethodException {
    Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
    UserCheckAndLimit annotation = method.getAnnotation(UserCheckAndLimit.class);

    String key = joinPoint.getTarget().getClass().getName() + "_" + method.getName();

    boolean needLogin = annotation.needLogin();
    int maxCount = annotation.maxCount();
    int expireSeconds = annotation.seconds();

    if (needLogin) {
      MiaoShaUser user = UserContext.getUser();
      if (user == null) {
        throw new UserNotLoginException();
      }
      key = key + "_" + user.getName();
    }

    Long count = redisClient.executeLimitScript(key, expireSeconds);
    if (count > maxCount) {
      log.error("{}访问以超过限制，限制次数为{}，时间为{}，当前访问次数为{}", key, maxCount, expireSeconds, count);
      throw new AccessDeniedException("用户访问过于频繁，请稍后访问!");
    }
  }
}
