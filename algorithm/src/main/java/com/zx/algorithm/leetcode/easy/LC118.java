package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/5
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class LC118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        list.add(1);
        result.add(list);
        if (numRows == 1) {
            return result;
        }
        for (int i = 1; i < numRows; i++) {
            List<Integer> integers = new ArrayList<>();
            List<Integer> beforeList = result.get(result.size() - 1);
            for (int j = 0; j < beforeList.size(); j++) {
                if (j == 0) {
                    integers.add(1);
                    continue;
                }
                integers.add(beforeList.get(j - 1) + beforeList.get(j));
            }
            integers.add(1);
            result.add(integers);
        }
        return result;
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> a = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> al = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    al.add(1);
                } else {
                    al.add(a.get(i - 1).get(j - 1) + a.get(i - 1).get(j));
                }
            }
            a.add(al);
        }
        return a;
    }
}
