package com.zx.jdkanalysis.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouxin
 * @since 2019/8/16
 */
public class Main {

  public static void main(String[] args) {
    Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
    Stream<Integer> integerStream1 = integerStream.flatMap(e -> Stream.of(e + 1, e + 2));
    List<Integer> collect = integerStream1.collect(Collectors.toList());
  }
}
