package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxin
 * @since 2019/6/8
 */
public class HeapSort2 implements Sort {

  private int len;

  private AtomicInteger swapCount = new AtomicInteger(0);

  private List result;

  @Override
  public <T> void sort(List<T> list, Comparator<T> comparator) {
    this.len = list.size();
    buildHeap(list, comparator);
    while (len > 0) {
      swap(list, len - 1, 0);
      len--;
      swapHeap(list, comparator, 0);
    }
    this.result = list;
  }

  public <T> void buildHeap(List<T> list, Comparator<T> comparator) {
    // 数组索引从0开始   0->1,2  1->3,4
    for (int i = len / 2 - 1; i >= 0; i--) {
      swapHeap(list, comparator, i);
    }
  }

  public <T> void swapHeap(List<T> list, Comparator<T> comparator, int nodeNumber) {
    int max = nodeNumber;
    int left = nodeNumber * 2 + 1;
    int right = nodeNumber * 2 + 2;
    if (left < len && comparator.compare(list.get(left), list.get(max)) > 0) {
      max = left;
    }
    if (right < len && comparator.compare(list.get(right), list.get(max)) > 0) {
      max = right;
    }
    if (max == nodeNumber) {
      return;
    }
    swap(list, max, nodeNumber);
    swapHeap(list, comparator, max);
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
  public List getResult() {
    return result;
  }
}
