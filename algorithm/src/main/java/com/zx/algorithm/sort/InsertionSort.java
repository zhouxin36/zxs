package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 插入排序 O(n*n)
 *
 * @author zhouxin
 * @since 2019/4/28
 */
public class InsertionSort implements Sort {

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

  public <T> void sort(List<T> list, Comparator<T> comparator) {
    for (int i = 1; i < list.size(); i++) {
      int j = i - 1;
      for (; j >= 0 && comparator.compare(list.get(j + 1), list.get(j)) < 0; j--) {
        swap(list, j + 1, j);
      }
    }
    this.result = list;
  }
}
