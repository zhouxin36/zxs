package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;

/**
 * 希尔排序 n*log2n(内部使用插入排序)
 *
 * @author zhouxin
 * @since 2019/5/4
 */
public class ShellSort implements Sort {

  private AtomicInteger swapCount = new AtomicInteger(0);

  private List result;
  private IntFunction<Integer> defaultGap;
  private IntFunction<Integer> defaultReduceGap;

  ShellSort() {
    this.defaultGap = null;
    this.defaultReduceGap = null;
  }

  ShellSort(IntFunction<Integer> defaultGap, IntFunction<Integer> defaultReduceGap) {
    this.defaultGap = defaultGap;
    this.defaultReduceGap = defaultReduceGap;
  }

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

  /**
   * 默认增量
   *
   * @param collectionSize 集合大小
   * @return 增量
   */
  private int defaultGap(int collectionSize) {
    return collectionSize / 2;
  }

  /**
   * 默认缩小增量
   *
   * @param gap 缩小前增量
   * @return 缩小后增量
   */
  private int defaultReduceGap(int gap) {
    return gap < 2 && gap != 1 ? 1 : gap / 2;
  }

  @Override
  public <T> void sort(List<T> list, Comparator<T> comparator) {
    int gap = defaultGap != null ? defaultGap.apply(list.size()) : defaultGap(list.size());
    while (gap > 0) {
      for (int i = gap; i < list.size(); i++) {
        for (int j = i; j >= gap; j -= gap) {
          if (comparator.compare(list.get(j), list.get(j - gap)) >= 0) {
            break;
          }
          swap(list, j - gap, j);
        }
      }
      gap = defaultReduceGap != null ? defaultReduceGap.apply(gap) : defaultReduceGap(gap);
    }
    this.result = list;
  }
}
