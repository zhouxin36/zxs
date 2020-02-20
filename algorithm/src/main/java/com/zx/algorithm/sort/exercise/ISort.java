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

  ISort<T> print();

  default ISort<T> method(Object o, Object o2){return this;}
}
