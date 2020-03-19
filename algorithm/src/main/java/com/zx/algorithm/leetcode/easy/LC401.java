package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhouxin
 * @since 2020/3/19
 */
public class LC401 {
    public static final int[] ARR = new int[]{1, 2, 4, 8, 16, 32, 60, 120, 240, 480};

    public static void main(String[] args) {
        System.out.println(new LC401().readBinaryWatch2(1).toString().equals("[0:01, 0:02, 0:04, 0:08, 0:16, 0:32, 1:00, 2:00, 4:00, 8:00]"));
    }

    private static String intToStr(int i) {
        int h = i / 60;
        int m = i % 60;
        return h + ":" + (m < 10 ? "0" + m : m);
    }

    public List<String> readBinaryWatch(int num) {
        int[] a = ARR.clone();
        List<Integer> list = new ArrayList<>();
        doReadBinaryWatch(num, list, 0, 0, a, -1);
        return list.stream().sorted().map(e -> intToStr(e)).collect(Collectors.toList());
    }

    public void doReadBinaryWatch(int num, List<Integer> list, int h, int m, int[] a, int index) {
        if (h >= 720 || m >= 60) {
            return;
        }
        if (num == 0) {
            list.add(h + m);
            return;
        }
        for (int i = index + 1; i < a.length; i++) {
            if (i < 6) {
                if (a[i] != 0) {
                    m += a[i];
                    a[i] = 0;
                    doReadBinaryWatch(num - 1, list, h, m, a, i);
                    a[i] = ARR[i];
                    m -= a[i];
                }
            } else {
                if (a[i] != 0) {
                    h += a[i];
                    a[i] = 0;
                    doReadBinaryWatch(num - 1, list, h, m, a, i);
                    a[i] = ARR[i];
                    h -= a[i];
                }
            }
        }
    }

    public List<String> readBinaryWatch2(int num) {
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
                    sb.setLength(0);
                    sb.append(h);
                    sb.append(":");
                    if (m < 10) {
                        sb.append("0");
                    }
                    sb.append(m);
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }
}
