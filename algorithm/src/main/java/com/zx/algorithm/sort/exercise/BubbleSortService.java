package com.zx.algorithm.sort.exercise;


import java.util.Comparator;
import java.util.List;

/**
 * 冒泡排序 O(n*n)
 *
 * @author zhouxin
 * @since 2019/12/04
 */
@SuppressWarnings("unused")
public class BubbleSortService<T> extends AbstractSort<T> {


  public BubbleSortService(List<T> source, Comparator<T> comparator) {
    super(source, comparator);
  }

  @Override
  public ISort<T> doSort() {
    List<T> source = getSource();
    for (int i = 0; i < source.size(); i++) {
      boolean change = false;
      for (int j = 0; j < source.size() - i - 1; j++) {
        if (compare(j, j + 1) > 0) {
          swap(j, j + 1);
          change = true;
        }
      }
      if (!change) {
        break;
      }
    }
    return this;
  }
}
