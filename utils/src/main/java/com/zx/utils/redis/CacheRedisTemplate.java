package com.zx.utils.redis;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author zhouxin
 * @since 2020/8/8
 */
@SuppressWarnings({"unused", "SameParameterValue"})
@Service
public class CacheRedisTemplate {

  private static final Logger LOGGER = LoggerFactory.getLogger(CacheRedisTemplate.class);
  private static final ConversionService CONVERSION_SERVICE = new DefaultConversionService();
  private final StringRedisTemplate redisTemplate;
  private final ObjectMapper objectMapper;

  public CacheRedisTemplate(StringRedisTemplate stringRedisTemplate,
                            ObjectMapper objectMapper) {
    this.redisTemplate = stringRedisTemplate;
    this.objectMapper = objectMapper;
  }

  private static RuntimeException getException(String message) {
    return new RuntimeException(message);
  }

  public <T> T findString(RedisKey redisKey
          , Class<T> returnType
          , Supplier<T> supplier) {
    return findString(redisKey.getKey(), redisKey.getTimeOut(), redisKey.getTimeUnit(), returnType,
            supplier);
  }

  public <T> T findString(RedisKey redisKey
          , String suffix
          , Class<T> returnType
          , Supplier<T> supplier) {
    return findString(redisKey.getKey() + suffix, redisKey.getTimeOut(), redisKey.getTimeUnit(),
            returnType,
            supplier);
  }

  /**
   * @param parameterClasses 泛型数组
   */
  public <T> T findString(RedisKey redisKey
          , Class<T> returnType
          , Supplier<T> supplier
          , Class<?>... parameterClasses) {
    return findString(redisKey.getKey(), redisKey.getTimeOut(), redisKey.getTimeUnit(), returnType,
            supplier, parameterClasses);
  }

  public <T> T findString(String key
          , Long timeOut
          , TimeUnit timeUnit
          , Class<T> returnType
          , Supplier<T> supplier
          , Class<?>... parameterClasses) {
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    String data = valueOperations.get(key);
    if (!StringUtils.isEmpty(data) && !"null".equals(data)) {
      return cast(data, returnType, parameterClasses);
    } else {
      T result = supplier.get();
      valueOperations.set(key, castStr(result), timeOut, timeUnit);
      return result;
    }
  }

  public <T> T findHash(RedisKey redisKey
          , Class<T> returnType
          , Supplier<T> supplier) {
    return findHash(redisKey.getKey(), redisKey.getHashKey(), returnType, supplier);
  }

  /**
   * @param parameterClasses 泛型数组
   */
  public <T> T findHash(RedisKey redisKey
          , Class<T> returnType
          , Supplier<T> supplier
          , Class<?>... parameterClasses) {
    return findHash(redisKey.getKey(), redisKey.getHashKey(), returnType, supplier,
            parameterClasses);
  }

  public <T> T findHash(String key
          , String hashKey
          , Class<T> returnType
          , Supplier<T> supplier
          , Class<?>... parameterClasses) {
    HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
    String data = hashOperations.get(key, hashKey);
    if (!StringUtils.isEmpty(data) && !"null".equals(data)) {
      return cast(data, returnType, parameterClasses);
    } else {
      T result = supplier.get();
      hashOperations.put(key, hashKey, castStr(result));

      return result;
    }
  }

  /**
   * 格式转换
   */
  private <T> T cast(String data, Class<T> returnType, Class<?>... parameterClasses) {
    try {
      if (returnType.getName().contains("java.lang")) {
        return CONVERSION_SERVICE.convert(data, returnType);
      } else {
        JavaType javaType = this.objectMapper.getTypeFactory()
                .constructParametricType(returnType, parameterClasses);
        return objectMapper.readValue(data, javaType);
      }
    } catch (Exception e) {
      LOGGER.error("CacheRedisTemplate#cast", e);
      throw getException("redis格式转换失败");
    }
  }

  /**
   * 格式转换
   */
  private <T> String castStr(T data) {
    try {
      if (data.getClass().getName().contains("java.lang")) {
        return data + "";
      } else {
        return this.objectMapper.writeValueAsString(data);
      }
    } catch (Exception e) {
      LOGGER.error("CacheRedisTemplate#cast", e);
      throw getException("redis格式转换失败");
    }
  }
}