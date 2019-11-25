package com.zx.utils.redis;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouxin
 * @since 2019/9/27
 */
public enum RedisKey {
  DEFALUT_KEY("DEFALUT:KEY", null, 10L, TimeUnit.MINUTES, "默认key"),
  DEFALUT_HASHKEY("DEFALUT:HASHKEY", "key", null, null, "默认hashkey"),
  ;
  private String key;
  private String hashKey;
  private Long timeOut;
  private TimeUnit timeUnit;
  private String notice;

  RedisKey(String key, String hashKey, Long timeOut, TimeUnit timeUnit,
           String notice) {
    this.key = key;
    this.hashKey = hashKey;
    this.timeOut = timeOut;
    this.timeUnit = timeUnit;
    this.notice = notice;
  }

  public String getKey() {
    return key;
  }

  public String getHashKey() {
    return hashKey;
  }

  public Long getTimeOut() {
    return timeOut;
  }

  public TimeUnit getTimeUnit() {
    return timeUnit;
  }

  public String getNotice() {
    return notice;
  }
}
