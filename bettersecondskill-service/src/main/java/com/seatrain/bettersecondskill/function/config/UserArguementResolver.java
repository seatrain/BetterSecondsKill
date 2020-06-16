package com.seatrain.bettersecondskill.function.config;

import com.seatrain.bettersecondskill.commons.constant.LoginConstant;
import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import com.seatrain.bettersecondskill.commons.exception.UserNotLoginException;
import com.seatrain.bettersecondskill.commons.service.MiaoShaUserService;
import com.seatrain.bettersecondskill.function.redisManage.RedisClient;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserArguementResolver implements HandlerMethodArgumentResolver {

  @Autowired
  MiaoShaUserService miaoShaUserService;

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.getParameterType().equals(MiaoShaUser.class);
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

    HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
    Cookie[] cookies = request.getCookies();

    List<String> tokens = Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals(LoginConstant.TOKEN))
        .map(Cookie::getValue)
        .collect(Collectors.toList());
    String token = CollectionUtils.isEmpty(tokens) ? "" : tokens.get(0);

    if (StringUtils.isEmpty(token)) {
      throw new UserNotLoginException();
    }

    MiaoShaUser result = miaoShaUserService.getByToken(token);
    return result;
  }
}
