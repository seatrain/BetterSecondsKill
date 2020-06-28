package com.seatrain.bettersecondskill.function.access;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

  private ThreadLocal<Long> start;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    initRequestStartTime();
    if (handler instanceof HandlerMethod) {

    }
    return true;
  }


  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    printRequestCost();
    super.afterCompletion(request, response, handler, ex);
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

}
