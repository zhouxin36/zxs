package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 希尔排序 n*log2n(内部使用插入排序)
 *
 * @author zhouxin
 * @since 2019/12/4
 */
@SuppressWarnings("unused")
public class ShellSortService<T> extends AbstractSort<T> {

  /**
   * 增量
   */
  private final IntFunction<Integer> gap;
  /**
   * 缩小增量
   */
  private final IntFunction<Integer> reduceGap;


  public ShellSortService(List<T> source, Comparator<T> comparator) {
    super(source, comparator);
    this.gap = e -> e / 2;
    this.reduceGap = e -> e < 1 ? 1 : e / 2;
  }

  public ShellSortService(List<T> source, Comparator<T> comparator, IntFunction<Integer> gap, IntFunction<Integer> reduceGap) {
    super(source, comparator);
    this.gap = gap;
    this.reduceGap = reduceGap;
  }

  @Override
  protected ISort<T> doSort() {
    List<T> source = getSource();
    Integer gap = this.gap.apply(source.size());
    while (gap > 0) {
      for (int i = gap; i < source.size(); i++) {
        for (int j = i; j >= gap && compare(j - gap, j) > 0; j -= gap) {
          swap(j - gap, j);
        }
      }
      gap = reduceGap.apply(gap);
    }
    return this;
  }
}
