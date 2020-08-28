package com.seatrain.bettersecondskill.function.redisManage.script;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 限流脚本
 *
 * @author seatrain
 * @version 1.0
 * @date 2020/08/25 11:22
 */
@Configuration
public class LimitScript {

  private static final String SCRIPT_PATH = "luaScripts/Limit.lua";

  @Bean
  public CustomizedRedisScript<Long> script() {
    Resource scriptSource = new ClassPathResource(SCRIPT_PATH);
    CustomizedRedisScript<Long> redisScript = new CustomizedRedisScript<>();
    redisScript.setResultType(Long.class);
    redisScript.setLocation(scriptSource);
    return redisScript;
  }
}
