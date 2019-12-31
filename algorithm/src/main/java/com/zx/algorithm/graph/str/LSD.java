package com.zx.algorithm.graph.str;

import java.util.UUID;

/**
 * 低位优先排序  等长子串排序
 */
public class LSD {

    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (String value : a) {
                count[value.charAt(d) + 1]++;
            }

            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            for (String s : a) {
                aux[count[s.charAt(d)]++] = s;
            }

            System.arraycopy(aux, 0, a, 0, n);
        }
    }

    public static void main(String[] args) {
        int size = 10;
        String[] a = new String[size];
        for (int i = 0; i < size; i++) {
            a[i] = UUID.randomUUID().toString();
        }
        sort(a, size);
        for (String s : a) {
            System.out.println(s);
        }
    }
}