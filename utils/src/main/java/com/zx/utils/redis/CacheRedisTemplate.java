package com.zx.utils.redis;

//import com.beiming.framework.enums.APIResultCodeEnums;
//import com.beiming.framework.util.AssertUtils.AssertionException;
//import com.beiming.mcourt.court.enums.RedisKey;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.lang.reflect.Method;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Supplier;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class CacheRedisTemplate {
//
//  private final StringRedisTemplate redisTemplate;
//
//  private final ObjectMapper objectMapper;
//
//  public CacheRedisTemplate(StringRedisTemplate stringRedisTemplate,
//      ObjectMapper objectMapper) {
//    this.redisTemplate = stringRedisTemplate;
//    this.objectMapper = objectMapper;
//  }
//
//  public <T> T findString(RedisKey redisKey
//      , Class<T> returnType
//      , Supplier<T> supplier) {
//    return findString(redisKey.getKey(), redisKey.getTimeOut(), redisKey.getTimeUnit(), returnType,
//        supplier);
//  }
//
//  /**
//   * @param parameterClasses 泛型数组
//   */
//  public <T> T findString(RedisKey redisKey
//      , Class<T> returnType
//      , Supplier<T> supplier
//      , Class<?>... parameterClasses) {
//    return findString(redisKey.getKey(), redisKey.getTimeOut(), redisKey.getTimeUnit(), returnType,
//        supplier, parameterClasses);
//  }
//
//  public <T> T findString(String key
//      , Long timeOut
//      , TimeUnit timeUnit
//      , Class<T> returnType
//      , Supplier<T> supplier
//      , Class<?>... parameterClasses) {
//    ValueOperations valueOperations = redisTemplate.opsForValue();
//    String data = (String) valueOperations.get(key);
//    if (!StringUtils.isEmpty(data) && !"null".equals(data)) {
//      return cast(data, returnType, parameterClasses);
//    } else {
//      T result = supplier.get();
//      //noinspection unchecked
//      valueOperations.set(key, castStr(result), timeOut, timeUnit);
//      return result;
//    }
//  }
//
//  public <T> T findHash(RedisKey redisKey
//      , Class<T> returnType
//      , Supplier<T> supplier) {
//    return findHash(redisKey.getKey(), redisKey.getHashKey(), returnType, supplier);
//  }
//
//  /**
//   * @param parameterClasses 泛型数组
//   */
//  public <T> T findHash(RedisKey redisKey
//      , Class<T> returnType
//      , Supplier<T> supplier
//      , Class<?>... parameterClasses) {
//    return findHash(redisKey.getKey(), redisKey.getHashKey(), returnType, supplier, parameterClasses);
//  }
//
//  public <T> T findHash(String key
//      , String hashKey
//      , Class<T> returnType
//      , Supplier<T> supplier
//      , Class<?>... parameterClasses) {
//    HashOperations hashOperations = redisTemplate.opsForHash();
//    //noinspection unchecked
//    String data = (String) hashOperations.get(key, hashKey);
//    if (!StringUtils.isEmpty(data) && !"null".equals(data)) {
//      return cast(data, returnType, parameterClasses);
//    } else {
//      T result = supplier.get();
//      //noinspection unchecked
//      hashOperations.put(key, hashKey, castStr(result));
//
//      return result;
//    }
//  }
//
//  /**
//   * 格式转换
//   */
//  @SuppressWarnings("unchecked")
//  private <T> T cast(String data, Class<T> returnType, Class<?>... parameterClasses) {
//    try {
//      if (String.class.equals(returnType)) {
//        return (T) data;
//      } else if (returnType.getName().contains("java.lang")) {
//        Method valueof = returnType.getDeclaredMethod("valueOf", String.class);
//        valueof.setAccessible(true);
//        return (T) valueof.invoke(null, data);
//      } else {
//        JavaType javaType = this.objectMapper.getTypeFactory()
//            .constructParametricType(returnType, parameterClasses);
//        return objectMapper.readValue(data, javaType);
//      }
//    } catch (Exception e) {
//      log.error("CacheRedisTemplate#cast", e);
//      throw new AssertionException(APIResultCodeEnums.UNEXCEPTED, "redis格式转换失败");
//    }
//  }
//
//  /**
//   * 格式转换
//   */
//  private <T> String castStr(T data) {
//    try {
//      if (data.getClass().getName().contains("java.lang")) {
//        return data + "";
//      } else {
//        return this.objectMapper.writeValueAsString(data);
//      }
//    } catch (Exception e) {
//      log.error("CacheRedisTemplate#cast", e);
//      throw new AssertionException(APIResultCodeEnums.UNEXCEPTED, "redis格式转换失败");
//    }
//  }
//}