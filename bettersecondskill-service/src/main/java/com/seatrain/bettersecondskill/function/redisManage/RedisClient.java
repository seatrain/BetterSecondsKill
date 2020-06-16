package com.seatrain.bettersecondskill.function.redisManage;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 提供redis操作接口
 */
@Configuration
@Slf4j
public class RedisClient {

  @Autowired
  private JedisPool jedisPool;

  @Autowired
  Gson gson;

  /**
   * 向缓存中设置对象
   *
   * @param key 键
   * @param value 值
   */
  public boolean set(String key, Object value) {
    Jedis resource = null;
    try {

      String realValue = bean2String(value);
      if (realValue == null || realValue.isEmpty()) {
        return false;
      }

      resource = jedisPool.getResource();
      resource.set(key, realValue);
      return true;
    } finally {
      returnResource(resource);
    }
  }

  /**
   * 向缓存中设置对象并设置过期时间（单位为秒）
   *
   * @param key 键
   * @param value 值
   * @param seconds 过期时间
   */
  public boolean set(String key, Object value, int seconds) {
    Jedis jedis = null;
    try {
      String realValue = bean2String(value);

      if (realValue == null || realValue.isEmpty()) {
        return false;
      }
      jedis = jedisPool.getResource();
      jedis.setex(key, seconds, realValue);
      return true;
    } finally {
      returnResource(jedis);
    }
  }

  /**
   * 向缓存中设置对象， 若值为null或为空会返回false
   *
   * @param keyPrefix 键前缀对象
   * @param key 键
   * @param value 值
   * @param <T> 值泛型
   * @return 设置是否成功
   */
  public <T> boolean set(RedisKeyPrefix keyPrefix, String key, T value) {

    Jedis resource = null;
    try {
      String realKey = keyPrefix.getPrefix() + key;
      String realValue = bean2String(value);

      if (realValue == null || realValue.isEmpty()) {
        return false;
      }

      resource = jedisPool.getResource();
      if (keyPrefix.getExpireSeconds() == 0) {
        resource.set(realKey, realValue);
      } else {
        resource.setex(realKey, keyPrefix.getExpireSeconds(), realValue);
      }

      return true;
    } finally {
      returnResource(resource);
    }
  }

  /**
   * 自增
   *
   * @param keyPrefix 键前缀对象
   * @param key 键
   */
  public long incr(RedisKeyPrefix keyPrefix, String key) {
    String realKey = keyPrefix.getPrefix() + key;
    return incr(realKey);
  }

  /**
   * 键自增
   *
   * @param key 键key
   * @return 自增之后的值
   */
  public long incr(String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      Long result = jedis.incr(key);
      return result;
    } finally {
      jedis.close();
    }
  }

  /**
   * 自减
   *
   * @param keyPrefix 键前缀对象
   * @param key 键
   * @return 自减之后的值
   */
  public long decr(RedisKeyPrefix keyPrefix, String key) {
    String realKey = keyPrefix.getPrefix() + key;
    return decr(realKey);
  }

  /**
   * 键自减
   *
   * @param key 键key
   * @return 自减之后的值
   */
  public long decr(String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      Long result = jedis.decr(key);
      return result;
    } finally {
      jedis.close();
    }
  }

  /**
   * 从redis获取对应的对象
   *
   * @param key 键
   * @param clazz 值所属的class
   * @param <T> 值泛型
   * @return 值实例s
   */
  public <T> T get(String key, Class<T> clazz) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      String value = jedis.get(key);
      T t = string2Bean(value, clazz);
      return t;

    } finally {
      returnResource(jedis);
    }
  }

  /**
   * 从redis获取对应的对象
   *
   * @param key 键
   * @param keyPrefix 键前缀对象
   * @param clazz 值所属的class
   * @param <T> 值泛型
   * @return 值实例s
   */
  public <T> T get(RedisKeyPrefix keyPrefix, String key, Class<T> clazz) {
    String realKey = keyPrefix.getPrefix() + key;

    return get(realKey, clazz);
  }

  /**
   * 删除redis中的键
   *
   * @param key 键
   * @return 删除结果
   */
  public boolean delete(String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();

      Long del = jedis.del(key);
      return del > 0;
    } finally {
      returnResource(jedis);
    }
  }

  /**
   * 删除redis中的键
   *
   * @param keyPrefix 键前缀对象
   * @param key 键
   * @return 删除结果
   */
  public boolean delete(RedisKeyPrefix keyPrefix, String key) {
    String realKey = keyPrefix.getPrefix() + key;

    return delete(realKey);
  }

  /**
   * 将对象转换为json字符串，
   *
   * @param value 对象值
   * @param <T> 对象泛型
   * @return 转换之后的字符串
   */
  private <T> String bean2String(T value) {
    if (value == null) {
      return null;
    }

    return gson.toJson(value);
  }

  /**
   * 将字符串转化为clazz对应的实例
   *
   * @param value 值字符串
   * @param clazz 值的class对象
   * @param <T> 值泛型
   * @return 实例s
   */
  private <T> T string2Bean(String value, Class<T> clazz) {
    if (value == null || value.isEmpty() || clazz == null) {
      return null;
    }

    return gson.fromJson(value, clazz);
  }

  /**
   * 归还jedis资源
   */
  private void returnResource(Jedis jedis) {
    if (jedis != null) {
      jedis.close();
    }
  }
}
