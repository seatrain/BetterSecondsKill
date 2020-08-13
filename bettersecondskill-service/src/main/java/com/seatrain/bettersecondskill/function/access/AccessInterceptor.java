package com.seatrain.bettersecondskill.function.access;

import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import com.seatrain.bettersecondskill.commons.service.MiaoShaUserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

  private ThreadLocal<Long> start;

  @Autowired
  private MiaoShaUserService miaoShaUserService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    initRequestStartTime();

    // 过滤静态资源请求
    if (handler instanceof ResourceHttpRequestHandler) {
      log.info("----------ResourceHttpRequestHandler----------" + handler.toString() + "----------");
    } else if (handler instanceof HandlerMethod) {
      log.info("打印拦截方法handler:{}", handler);
      HandlerMethod hm = (HandlerMethod) handler;
      MiaoShaUser user = getUser(request);
      UserContext.setUser(user);
    }
    return true;
  }


  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    printRequestCost();
    super.afterCompletion(request, response, handler, ex);
    UserContext.removeUser();
    miaoShaUserService.refreshToken(request, response);
  }

  /**
   * 初始化请求开始时间
   */
  private void initRequestStartTime() {
    start = ThreadLocal.withInitial(System::currentTimeMillis);
    start.get();
  }


  /**
   * 打印请求耗时
   */
  private void printRequestCost() {
    long end = System.currentTimeMillis();
    log.info("本次请求耗时:{}s", (end - start.get()) / 1000f);
  }

  /**
   * 从请求参数和请求头中获取当前登录用户的标志，前者优先于后者，并讲用户信息设置在响应中
   */
  private MiaoShaUser getUser(HttpServletRequest request) {
    String token = miaoShaUserService.getToken(request);
    if (token == null) {
      return null;
    }

    return miaoShaUserService.getByToken(token);
  }


}
