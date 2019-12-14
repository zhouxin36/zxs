package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * 插入排序 O(n*n)
 *
 * @author zhouxin
 * @since 2019/12/04
 */
@SuppressWarnings("unused")
public class InsertionSortService<T> extends AbstractSort<T> {

  public InsertionSortService(List<T> source, Comparator<T> comparator) {
    super(source, comparator);
  }

  @Override
  protected ISort<T> doSort() {
    List<T> source = getSource();
    for (int i = 1; i < source.size(); i++) {
      for (int j = i; j > 0 && compare(j - 1, j) > 0; j--) {
        swap(j - 1, j);
      }
    }
    return this;
  }
}
