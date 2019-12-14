package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/4/28
 */
public interface Sort {

  <T> void sort(List<T> list, Comparator<T> comparator);

  default <T> void swap(List<T> list, int a, int b) {
    incrementSwapCount();
    T temp;
    temp = list.get(a);
    list.set(a, list.get(b));
    list.set(b, temp);
  }

  int getSwapCount();

  void incrementSwapCount();

  List getResult();
}
