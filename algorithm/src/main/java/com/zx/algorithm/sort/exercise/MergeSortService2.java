package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * 归并排序 n*log2n
 *
 * @author zhouxin
 * @since 2019/12/4
 */
@SuppressWarnings("unused")
public class MergeSortService2<T> extends AbstractSort<T> {

  private T[] tmp;

  /**
   * 插入排序最大长度
   */
  private int n;

  public MergeSortService2(List<T> source, Comparator<T> comparator) {
    super(source, comparator);
  }

  @Override
  protected ISort<T> doSort() {
    List<T> source = getSource();
    if (source.size() < 1) {
      return this;
    }
    n = (int)(Math.log(source.size()) / Math.log(2));
    //noinspection unchecked
    this.tmp = (T[]) new Object[source.size()];
    doSort(source, 0, source.size() - 1);
    return this;
  }

  private void doSort(List<T> source,final int start,final int end) {
    if (end - start < n) {
      insertSort(start, end);
      return;
    }
    int middle = start + (end - start) / 2;
    doSort(source, start, middle);
    doSort(source, middle + 1, end);
    for (int i = start; i <= end; i++) {
      tmp[i] = source.get(i);
    }
    for (int i = start, s = start, m = middle + 1; i <= end; i++) {
      if (s > middle) {
        source.set(i, tmp[m]);
        m++;
      } else if (m > end) {
        source.set(i, tmp[s]);
        s++;
      } else if (compare(tmp, s, m) > 0) {
        source.set(i, tmp[m]);
        m++;
      } else {
        source.set(i, tmp[s]);
        s++;
      }
    }
  }

  private void insertSort(int start, int end) {
      if(end - start < 2){
        return;
      }
    for (int i = start + 1; i <= end; i++) {
      for (int j = i; j > start && compare(j, j - 1) < 0; j--) {
        swap(j - 1, j);
      }
    }
  }
}
