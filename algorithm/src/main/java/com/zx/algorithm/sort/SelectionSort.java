package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 选择排序 O(n*n)
 *
 * @author zhouxin
 * @since 2019/4/29
 */
public class SelectionSort implements Sort {

  private AtomicInteger swapCount = new AtomicInteger(0);

  private List result;

  @Override
  public List getResult() {
    return result;
  }

  @Override
  public int getSwapCount() {
    return swapCount.get();
  }

  @Override
  public void incrementSwapCount() {
    swapCount.incrementAndGet();
  }

  @Override
  public <T> void sort(List<T> list, Comparator<T> comparator) {
    int change;
    for (int i = 0; i < list.size(); i++) {
      change = i;
      int j = i + 1;
      for (; j < list.size(); j++) {
        if (comparator.compare(list.get(change), list.get(j)) > 0) {
          change = j;
        }
      }
      swap(list, change, i);
    }
    this.result = list;
  }
}
