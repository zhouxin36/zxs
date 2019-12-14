package com.zx.algorithm.sort.exercise;

import java.util.List;

/**
 * 排序算法接口
 *
 * @author zhouxin
 * @since 2019/12/04
 */
public interface ISort<T> {
  int getSwapCount();

  int getCompareCount();

  List<T> getSource();

  ISort<T> sort();

  void print();
}
