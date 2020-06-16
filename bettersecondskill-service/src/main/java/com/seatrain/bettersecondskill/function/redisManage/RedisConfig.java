package com.seatrain.bettersecondskill.function.redisManage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 配置jedis连接池
 */
@Slf4j
@Configuration
public class RedisConfig {

  @Value("${spring.redis.timeout}")
  private int timeout;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.jedis.pool.max-active}")
  private int maxActive;

  @Value("${spring.redis.jedis.pool.max-idle}")
  private int maxIdle;

  @Bean
  public JedisPool configJedisPool() {
    long start = System.currentTimeMillis();
    log.info("jedis pool 开始初始化, host={}, port={}", host, port);
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(maxIdle);
    jedisPoolConfig.setMaxTotal(maxActive);
    jedisPoolConfig.setMaxWaitMillis(1000);

    jedisPoolConfig.setJmxEnabled(true);
    JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);

    long end = System.currentTimeMillis();
    log.info("jedis pool 初始化完成，耗时={}s", (end - start) / 1000f);

    return jedisPool;
  }
}
