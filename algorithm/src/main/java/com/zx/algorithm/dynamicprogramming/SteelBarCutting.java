package com.zx.algorithm.dynamicprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 钢条切割
 *
 * @author zhouxin
 * @since 2020/3/15
 */
public class SteelBarCutting {

    interface SteelBar{
        int cut(int n, int[] princes);
    }
    private static final Logger logger = LoggerFactory.getLogger(SteelBarCutting.class);
    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30, 31, 34, 35, 38, 40, 45, 46};
        for (int i = 1; i < 17; i++) {
           print(SteelBarCutting::cut1, i, arr, "自顶向下递归实现");
           print(SteelBarCutting::cut2, i, arr, "备忘录自顶向下递归实现");
           print(SteelBarCutting::cut3, i, arr, "自底向上");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        }
    }

    /**
     * 自顶向下递归实现
     *
     * @param n      钢条总长度
     * @param prices 长度价格表
     * @return 最大收益
     */
    private static int cut1(int n, int[] prices) {
        if (n == 0) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            q = Math.max(q, prices[i] + cut1(n - i - 1, prices));
        }
        return q;
    }

    /**
     * 备忘录自顶向下递归实现
     *
     * @param n      钢条总长度
     * @param prices 长度价格表
     * @return 最大收益
     */
    private static int cut2(int n, int[] prices) {
        return doCut2(n, prices, new int[n]);
    }
    private static int doCut2(int n, int[] prices, int[] memories) {
        if (n == 0) {
            return 0;
        }
        if (memories[n - 1] != 0) {
            return memories[n - 1];
        }
        int q = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            q = Math.max(q, prices[i] + doCut2(n - i - 1, prices, memories));
        }
        memories[n - 1] = q;
        return q;
    }

    /**
     * 自底向上
     *
     * @param n      钢条总长度
     * @param prices 长度价格表
     * @return 最大收益
     */
    private static int cut3(int n, int[] prices) {
        if (n == 0) {
            return 0;
        }
        if (n == 1){
            return prices[0];
        }
        int[] arr = new int[n + 1];
        arr[0] = 0;
        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                q = Math.max(q, prices[j - 1] + arr[i - j]);
            }
            arr[i] = q;
        }
        return arr[n];
    }

    private static void print(SteelBar steelBar, int n, int[] prices, String desc){
        long start = System.nanoTime();
        int price = steelBar.cut(n, prices);
        long end = System.nanoTime();
        logger.info("{} - 钢条总长度:{}, 钢条最大收益:{}, 运行时间:{}", desc, n, price, end - start);
    }
}
