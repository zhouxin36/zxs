package com.zx.algorithm.sort.exercise;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 排序算法抽象类
 *
 * @author zhouxin
 * @since 2019/12/04
 */
public abstract class AbstractSort<T> implements ISort<T> {

    public static final String PAD_CHAR = "*";
    public static final int SIZE = 10;
    public static final int N = 1000;

  private final Logger logger;

  private final List<T> source;

  private final ProxyComparator<T> comparator;

  private AtomicInteger swapCount = new AtomicInteger(0);

  private StopWatch stopWatch = new StopWatch();

  public AbstractSort(List<T> source, Comparator<T> comparator) {
    this.source = source;
    this.comparator = new ProxyComparator<>(comparator);
    this.logger = LoggerFactory.getLogger(StringUtils.rightPad(this.getClass().getSimpleName(), 3 * SIZE, PAD_CHAR));
  }

  protected void swap(int a, int b) {
    incrementSwapCount();
    T temp;
    temp = source.get(a);
    source.set(a, source.get(b));
    source.set(b, temp);
  }

  int compare(int a, int b) {
    return compare(source, a, b);
  }

  int compare(List<T> list ,int a, int b) {
    return comparator.compare(list.get(a), list.get(b));
  }

  int compare(T[] t ,int a, int b) {
    return comparator.compare(t[a], t[b]);
  }

  public int getSwapCount() {
    return swapCount.get();
  }

  public ISort<T> sort() {
    stopWatch.start();
    ISort<T> sort = doSort();
    stopWatch.stop();
    return sort;
  }

  protected abstract ISort<T> doSort();

  @SuppressWarnings("UnusedReturnValue")
  private int incrementSwapCount() {
    return swapCount.getAndIncrement();
  }

  public List<T> getSource() {
    return source;
  }

  @Override
  public int getCompareCount() {
    return comparator.getCompareCount();
  }

  public void print() {
    logger.info("----->compare:{},swap:{},time:{},list:{}"
        , StringUtils.rightPad(getCompareCount() + "", SIZE, PAD_CHAR)
        , StringUtils.rightPad(getSwapCount() + "", SIZE, PAD_CHAR)
        , StringUtils.rightPad(stopWatch.getNanoTime() / N + "", SIZE, PAD_CHAR)
        , getSource());
  }
}
