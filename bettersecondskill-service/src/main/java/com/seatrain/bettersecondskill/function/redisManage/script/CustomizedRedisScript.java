package com.seatrain.bettersecondskill.function.redisManage.script;

import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * <R>自定义的RedisScript，主要还是基于SpringBoot提供的DefaultRedisScript。<R/>
 * <R>目标时为了可以自定义设置sha1值，便于执行evalsha1方法，提高效率<R/>
 *
 * @author seatrain
 * @version 1.0
 * @date 2020/08/28 16:04
 */
public class CustomizedRedisScript<T> extends DefaultRedisScript<T> {

  // 将脚本加载到redis之后返回的值
  private String sha1;

  @Override
  public String getSha1() {
    return sha1;
  }

  public void setSha1(String sha1) {
    this.sha1 = sha1;
  }
}
