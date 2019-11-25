package com.zx.spingbootmicrowebflux.repository;

import com.zx.spingbootmicrowebflux.bean.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxin
 * @date 2018-11-24
 */
@Repository
public class UserRepository {

  private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

  private final AtomicInteger uuid = new AtomicInteger(0);

  public Boolean save(User user) {
    return users.put(uuid.incrementAndGet(), user) == null;
  }

  public Collection<User> findAll() {
    return users.values();
  }
}
