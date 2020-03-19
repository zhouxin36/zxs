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

    private static final Logger logger = LoggerFactory.getLogger(SteelBarCutting.class);

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
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
    private static SteelCut cut1(int n, int[] prices, SteelCut steelCut) {
        if (n == 0) {
            return steelCut.setTotalPrice(0);
        }
        int[] cutStrategy = steelCut.getCutStrategy();
        int q = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            int price = i < prices.length ? prices[i] : 0;
            cut1(n - i - 1, prices, steelCut);
            if (q < price + steelCut.getTotalPrice()) {
                q = price + steelCut.getTotalPrice();
                cutStrategy[n] = i + 1;
            }
        }
        return steelCut.setTotalPrice(q);
    }

    /**
     * 备忘录自顶向下递归实现
     *
     * @param n      钢条总长度
     * @param prices 长度价格表
     * @return 最大收益
     */
    private static SteelCut cut2(int n, int[] prices, SteelCut steelCut) {
        return doCut2(n, prices, new int[n], steelCut);
    }

    private static SteelCut doCut2(int n, int[] prices, int[] memories, SteelCut steelCut) {
        if (n == 0) {
            return steelCut.setTotalPrice(0);
        }
        if (memories[n - 1] != 0) {
            return steelCut.setTotalPrice(memories[n - 1]);
        }
        int[] cutStrategy = steelCut.getCutStrategy();
        int q = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int price = i < prices.length ? prices[i] : 0;
            doCut2(n - i - 1, prices, memories, steelCut);
            if (q < price + steelCut.getTotalPrice()){
                q = price + steelCut.getTotalPrice();
                cutStrategy[n] = i + 1;
            }
        }
        memories[n - 1] = q;
        return steelCut.setTotalPrice(q);
    }

    /**
     * 自底向上
     *
     * @param n      钢条总长度
     * @param prices 长度价格表
     * @return 最大收益
     */
    private static SteelCut cut3(int n, int[] prices, SteelCut steelCut) {
        if (n == 0) {
            return steelCut.setTotalPrice(0).setCutStrategy(new int[]{});
        }
        if (n == 1) {
            return steelCut.setTotalPrice(prices[0]).setCutStrategy(new int[]{1});
        }
        int[] arr = new int[n + 1];
        int[] res = new int[n + 1];
        arr[0] = 0;
        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                int price = (j - 1) < prices.length ? prices[j - 1] : 0;
                if (q < price + arr[i - j]) {
                    q = price + arr[i - j];
                    res[i] = j;
                }
            }
            arr[i] = q;
        }
        return steelCut.setTotalPrice(arr[n]).setCutStrategy(res);
    }

    private static void print(SteelBar steelBar, int n, int[] prices, String desc) {
        long start = System.nanoTime();
        SteelCut steelCut = steelBar.cut(n, prices, new SteelCut().setCutStrategy(new int[n + 1]));
        long end = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        int[] res = steelCut.getCutStrategy();
        int index = res.length - 1;
        while (index >= 0) {
            int re = res[index];
            sb.append(re).append(",");
            if (re == index || re == 0) {
                break;
            } else {
                index = index - re;
            }
        }
        logger.info("{} - 钢条总长度:{}, 钢条最大收益:{}, 分割策略:{}, 运行时间:{}", desc, n, steelCut.getTotalPrice(), sb.toString(), end - start);
    }

    interface SteelBar {
        SteelCut cut(int n, int[] princes, SteelCut steelCut);
    }

    static class SteelCut {
        private int totalPrice;
        private int[] cutStrategy;

        public SteelCut(int totalPrice, int[] cutStrategy) {
            this.totalPrice = totalPrice;
            this.cutStrategy = cutStrategy;
        }

        public SteelCut() {
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public SteelCut setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public int[] getCutStrategy() {
            return cutStrategy;
        }

        public SteelCut setCutStrategy(int[] cutStrategy) {
            this.cutStrategy = cutStrategy;
            return this;
        }
    }
}
