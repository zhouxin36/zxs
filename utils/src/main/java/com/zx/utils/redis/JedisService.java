package com.zx.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class JedisService {

    private static final String LOCK_PATH = "redisLock:";
    //操作redis客户端
    private static Jedis jedis;

    private final JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    private JedisService(JedisConnectionFactory jedisConnectionFactory) {
        this.jedisConnectionFactory = jedisConnectionFactory;
    }
    /**
     * 通过key删除（字节）
     *
     */
    public void del(byte[] key) {
        this.getJedis().del(key);
    }

    /**
     * 通过key删除
     *
     */
    public void del(String key) {
        this.getJedis().del(key);
    }

    /**
     * 添加key value 并且设置存活时间(byte)
     *
     */
    public void set(byte[] key, byte[] value, int liveTime) {
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }

    /**
     * 添加key value 并且设置存活时间
     *
     */
    public void set(String key, String value, int liveTime) {

        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        jedis.set(key, value);
        jedis.expire(key, liveTime);
        conn.close();
    }

    /**
     * 添加key value
     *
     */
    public void set(String key, String value) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        jedis.set(key, value);
        conn.close();
    }

    /**
     * 添加key value (字节)(序列化)
     *
     */
    public void set(byte[] key, byte[] value) {
        this.getJedis().set(key, value);
    }

    /**
     * 获取redis value (String)
     *
     */
    public String get(String key) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        String value = jedis.get(key);
        conn.close();
        return value;
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     *
     */
    public byte[] get(byte[] key) {
        return this.getJedis().get(key);
    }

    /**
     * 通过正则匹配keys
     *
     */
    public Set<String> keys(String pattern) {
        return this.getJedis().keys(pattern);
    }

    /**
     * 检查key是否已经存在
     *
     */
    public boolean exists(String key) {
        return this.getJedis().exists(key);
    }

    /**
     * 清空redis 所有数据
     *
     */
    public String flushDB() {
        return this.getJedis().flushDB();
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize() {
        return this.getJedis().dbSize();
    }

    /**
     * 检查是否连接成功
     *
     */
    public String ping() {
        return this.getJedis().ping();
    }

    /**
     * 获取一个jedis 客户端
     *
     */
    private Jedis getJedis() {
        if (jedis == null) {
            jedis = (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
            return jedis;
        }
        return jedis;
    }


    /**
     * 尝试获取分布式锁
     *
     * @param lockName    锁
     * @param acquireTimeout  请求超时时间/(毫秒)
     * @param expireTime 过期时间/(毫秒)
     * @return 唯一ID
     */
    public String tryGetDistributedLock(String lockName, long acquireTimeout, int expireTime) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        String identifier = UUID.randomUUID().toString();
        String lockKey = LOCK_PATH + lockName;
        long endTime = System.currentTimeMillis() + acquireTimeout;
        try(Jedis jedis = (Jedis) conn.getNativeConnection()) {
            while (System.currentTimeMillis() < endTime) {
                if (jedis.setnx(lockKey, identifier) == 1) {
                    jedis.expire(lockKey, expireTime/1000);
                    return identifier;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // nothing
                }
            }
        }finally {
            conn.close();
        }
        return null;
    }

    /**
     * 释放分布式锁
     *
     * @param lockName   锁
     * @param identifier 请求标识
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockName, String identifier) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        String lockKey = LOCK_PATH + lockName;
        boolean isRelease = false;
        try(Jedis jedis = (Jedis) conn.getNativeConnection()){
            while (true){
                //当对某个key进行watch后如果其他的客户端对这个key进行了更改，那么本次事务会被取消，事务的exec会返回null
                jedis.watch(lockKey);
                if(identifier.equals(jedis.get(lockKey))){
                    Transaction multi = jedis.multi();
                    multi.del(lockKey);
                    if(multi.exec().isEmpty()){
                        continue;
                    }
                    isRelease = true;
                }
                jedis.unwatch();
                break;
            }
        }finally {
            conn.close();
        }
        return isRelease;
    }

    public String hget(String key, String field) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        String result = jedis.hget(key, field);
        conn.close();
        return result;
    }

    public long hset(String key, String field, String value) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        long result = jedis.hset(key, field, value);
        conn.close();
        return result;
    }

    public Long delete(String key) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        Long result = jedis.del(key);
        conn.close();
        return result;
    }

    public Long incr(String key) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        Long result = jedis.incr(key);
        conn.close();
        return result;
    }

    public Long incrBy(String key, int value) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        Long result = jedis.incrBy(key, value);
        conn.close();
        return result;
    }

    public Long ttl(String key) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        Long result = jedis.ttl(key);
        conn.close();
        return result;
    }



    /**
     * 按降序获取集合元素，包含分值
     *
     */
    public Set<Tuple> getSortedSetByRangeDescWithScores(String key, int start, int end) {

        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        Set<Tuple> set = jedis.zrevrangeWithScores(key, start, end);
        conn.close();
        return set;
    }

    /**
     * 按升序获取集合元素，包含分值
     *
     */
    public Set<Tuple> getSortedByRangeSetAscWithScores(String key, int start, int end) {

        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        Set<Tuple> set = jedis.zrangeWithScores(key, start, end);
        conn.close();
        return set;
    }

    /**
     * 按升序获取集合元素
     *
     */
    public Set<String> getSortedSetByRangeDesc(String key, int start, int end) {

        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        Set<String> set = jedis.zrevrange(key, start, end);
        conn.close();
        return set;
    }

    /**
     * 按升序获取集合元素
     *
     */
    public Set<String> getSortedSetByRangeAsc(String key, int start, int end) {

        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        Set<String> set = jedis.zrange(key, start, end);
        conn.close();
        return set;
    }

    /**
     * 按降序获取集合元素，包含分值
     *
     * @param key
     * @param count 总数
     * @return
     */
    public Set<Tuple> getSortedSetByRangeDescWithScoresFromTop(String key, int count) {
        if (count <= 1) {
            return Collections.emptySet();
        }

        return getSortedSetByRangeDescWithScores(key, 0, count - 1);
    }

    /**
     * 按升序获取集合元素，包含分值
     *
     * @param key
     * @param count 总数
     * @return
     */
    public Set<Tuple> getSortedSetByRangeAscWithScoresFromTop(String key, int count) {
        if (count <= 1) {
            return Collections.emptySet();
        }

        return getSortedByRangeSetAscWithScores(key, 0, count - 1);
    }

    /**
     * 获取当前成员的排名--升序
     *
     * @param key
     * @param member
     * @return 排名
     */
    public Long getMemberRankAsc(String key, String member) {

        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        Long value = jedis.zrank(key, member);
        conn.close();
        return value;
    }

    /**
     * 获取当前成员的排名--降序
     *
     * @param key
     * @param member
     * @return 排名
     */
    public Long getMemberRankDesc(String key, String member) {

        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        Long value = jedis.zrevrank(key, member);
        conn.close();
        return value;
    }

    /**
     * 获取当前成员的分值
     *
     * @param key
     * @param member
     * @return 排名
     */
    public Double getMemberScore(String key, String member) {

        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        Double rtn = jedis.zscore(key, member);
        conn.close();
        return rtn;
    }

    /**
     * 有序集合中对指定成员的分数加上增量 increment
     *
     */
    public Double zincrBy(String key, String member, double increment) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        Double rtn = jedis.zincrby(key, increment, member);
        conn.close();
        return rtn;
    }

    /**
     * 按照排序,删除指定排名的成员（排名为升序）
     * eg. 升序保留前十名   start = 10， end=-1   (删除11到最后一名)
     * 降序保留前十名   start = 0， end=-11   (删除第一名到倒数11名，保留倒数第十到倒数第一名，即降序的第一到第十名)
     *
     */
    public void zremrangeByRank(String key, int start, int end) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        jedis.zremrangeByRank(key, start, end);
        conn.close();
    }

    /**
     * 设置过期时间
     *
     */
    public void setExpireTime(String key, int seconds) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();

        jedis.expire(key, seconds);
        conn.close();
    }

    /**
     * 有序集合中对指定成员的分数加上增量 increment
     *
     * @param increment return 1: 增加新成员表, 0:原成员值更新
     */
    public Long zadd(String key, String member, double increment) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        Long rtn = jedis.zadd(key, increment, member);
        conn.close();
        return rtn;
    }

    public Long rpush(String key, String value) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        Long rtn = jedis.rpush(key, value);
        conn.close();
        return rtn;
    }

    public String rpop(String key) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        String rtn = jedis.rpop(key);
        conn.close();
        return rtn;
    }

    public List<String> lrange(String key, long start, long end) {
        RedisConnection conn = jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) conn.getNativeConnection();
        List<String> rtn = jedis.lrange(key, start, end);
        conn.close();
        return rtn;
    }
}