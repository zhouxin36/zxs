package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Comparator 代理
 *
 * @author zhouxin
 * @since 2019/12/04
 */
public class ProxyComparator<T> implements Comparator<T> {

  private final Comparator<T> target;

  private AtomicInteger compareCount = new AtomicInteger(0);

  public ProxyComparator(Comparator<T> target) {
    this.target = target;
  }

  @Override
  public int compare(T o1, T o2) {
    incrementCompareCount();
    return target.compare(o1, o2);
  }

  @SuppressWarnings("UnusedReturnValue")
  private int incrementCompareCount(){
    return compareCount.getAndIncrement();
  }

  public int getCompareCount(){
    return compareCount.get();
  }
}
