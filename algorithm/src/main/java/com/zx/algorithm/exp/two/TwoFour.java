package com.zx.algorithm.exp.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * (逆序对)  假设$A[1..n]$是一个有$n$个不同数的数组。若$i<j$且$A[i]>A[j]$，则对偶$(i,j)$称为$A$的一个**逆序对**(inversion)。
 * 给出一个确定在$n$个元素的任何排序中逆序对数量的算法，最坏情况需要$\Theta(nlog_2n)$时间。
 *
 * @author zhouxin
 * @since 2020/1/17
 */
public class TwoFour {

    private List<Integer> arr;

    public TwoFour(List<Integer> list) {
        this.arr = list;
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int i1 = random.nextInt(20);
            list1.add(i1);
        }
        list1 = Arrays.asList(15, 10, 11, 6, 4);
        System.out.println(list1);
        TwoFour twoFour = new TwoFour(list1);
        System.out.println(twoFour.sort());
        System.out.println(twoFour.getSource());
    }

    public int sort() {
        return doInversionCount(0, arr.size() - 1);
    }

    private int doInversionCount(int start, int end) {
        if (start >= end) {
            return 0;
        }
        int count = 0;
        int mid = (start + end) / 2;
        count += doInversionCount(start, mid);
        count += doInversionCount(mid + 1, end);
        count += doMerge(start, mid, end);
        return count;
    }

    private int doMerge(int start, int mid, int end) {
        System.out.println("----------------------------------------------" + getSource() + "-----------------------------------------------------");
        int leftLength = mid - start + 1;
        int rightLength = end - mid;
        Integer[] left = new Integer[leftLength + 1];
        Integer[] right = new Integer[rightLength + 1];
        for (int i = 0; i < leftLength; i++) {
            left[i] = arr.get(start + i);
        }
        for (int i = 0; i < rightLength; i++) {
            right[i] = arr.get(mid + i + 1);
        }
        left[leftLength] = right[rightLength] = Integer.MAX_VALUE;
        int count = 0;
        boolean counted = false;
        for (int i = start, j = 0, k = 0; i <= end; i++) {
            if (!counted && left[j] > right[k]) {
                count = count + leftLength - j;
                System.out.println("----start:" + start + "----mid:" + mid + "----end:" + end + "----j:" + j + "----count:" + count + "-----" + getSource());
                counted = true;
            }
            if (left[j] <= right[k]) {
                arr.set(i, left[j]);
                j++;
            } else {
                arr.set(i, right[k]);
                k++;
                counted = false;
            }
        }
        return count;
    }

    public List<Integer> getSource() {
        return this.arr;
    }
}
