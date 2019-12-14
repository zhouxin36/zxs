package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * 选择排序 O(n*n)
 *
 * @author zhouxin
 * @since 2019/12/4
 */
@SuppressWarnings("unused")
public class SelectionSortService<T> extends AbstractSort<T> {

  public SelectionSortService(List<T> source, Comparator<T> comparator) {
    super(source, comparator);
  }

  @Override
  protected ISort<T> doSort() {
    List<T> source = getSource();
    for (int i = 0; i < source.size(); i++) {
      int change = i;
      for (int j = i + 1; j < source.size(); j++) {
        if (compare(change, j) > 0) {
          change = j;
        }
      }
      if (i != change) {
        swap(i, change);
      }
    }
    return this;
  }
}
