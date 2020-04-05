package com.zx.algorithm.leetcode.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/4/5
 */
public class Contest183 {
    public static void main(String[] args) {
//        4
        System.out.println(new Contest183().stoneGameIII(new int[]{1, 2, 3, 7}).equals("Bob"));
        System.out.println(new Contest183().stoneGameIII(new int[]{1, 2, 3, -9}).equals("Alice"));
        System.out.println(new Contest183().stoneGameIII(new int[]{1, 2, 3, 6}).equals("Tie"));
        System.out.println(new Contest183().stoneGameIII(new int[]{1, 2, 3, -1, -2, -3, 7}).equals("Alice"));
        System.out.println(new Contest183().stoneGameIII(new int[]{-1, -2, -3}).equals("Tie"));
//        3
//        System.out.println(new Contest183().longestDiverseString(0, 9, 12));
//        System.out.println(new Contest183().longestDiverseString(5, 4, 3));
//        System.out.println(new Contest183().longestDiverseString(4, 5, 3));
//        System.out.println(new Contest183().longestDiverseString(4, 3, 5));
//        System.out.println(new Contest183().longestDiverseString(0, 0, 0));
//        System.out.println(new Contest183().longestDiverseString(0, 0, 7));
//        System.out.println(new Contest183().longestDiverseString(2, 2, 1));
//        System.out.println(new Contest183().longestDiverseString(7, 1, 0));
//        System.out.println(new Contest183().longestDiverseString(1, 1, 7));
//        System.out.println(new Contest183().longestDiverseString(7, 1, 1));
//        System.out.println(new Contest183().longestDiverseString(1, 7, 1));
//        2
//        System.out.println(new Main().numSteps("1101"));
//        System.out.println(new Main().numSteps("1"));
//        System.out.println(new Main().numSteps("10"));
//        1
//        System.out.println(new Main().minSubsequence(new int[]{4, 3, 10, 9, 8}));
//        System.out.println(new Main().minSubsequence(new int[]{4, 4, 7, 6, 7}));
//        System.out.println(new Main().minSubsequence(new int[]{6}));
    }

    /**
     * dp[i] means, if we ignore before A[i],
     * what's the highest score that Alex can win over the Bob？
     * <p>
     * There are three option for Alice to choose.
     * Take A[i], win take - dp[i+1]
     * Take A[i] + A[i+1], win take - dp[i+2]
     * Take A[i] + A[i+1] + A[i+2], win take - dp[i+3]
     * dp[i] equals the best outcome of these three solutions.
     */
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[4];
        for (int i = n - 1; i >= 0; --i) {
            dp[i % 4] = Integer.MIN_VALUE;
            for (int k = 0, take = 0; k < 3 && i + k < n; ++k) {
                take += stoneValue[i + k];
                dp[i % 4] = Math.max(dp[i % 4], take - dp[(i + k + 1) % 4]);
            }
        }
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }

    public String longestDiverseString(int a, int b, int c) {
        if (a >= b && a >= c) {
            return doLongestDiverseString(a, b, c, 'a', 'b', 'c');
        } else if (b >= a && b >= c) {
            return doLongestDiverseString(b, a, c, 'b', 'a', 'c');
        } else {
            return doLongestDiverseString(c, a, b, 'c', 'a', 'b');
        }
    }

    public String doLongestDiverseString(int a, int b, int c, char as, char bs, char cs) {
        StringBuilder sb = new StringBuilder();
        int a1 = 0, b1 = 0, c1 = 0;
        a = Math.min(a, (b + c + 1) * 2);
        while (a != 0 || b != 0 || c != 0) {
            if (a != 0 && a1 < 2 && a >= b && a >= c) {
                sb.append(as);
                a--;
                a1++;
                b1 = 0;
                c1 = 0;
            } else if (b != 0 && b1 < 2 && ((b >= a && b >= c) || a1 == 2)) {
                sb.append(bs);
                b--;
                b1++;
                c1 = 0;
                a1 = 0;
            } else if (c != 0 && c1 < 2) {
                sb.append(cs);
                c--;
                c1++;
                b1 = 0;
                a1 = 0;
            }
        }
        return sb.toString();
    }

    public int numSteps(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        int index = chars.length - 1;
        boolean[] flags = new boolean[s.length()];
        while (index > 0) {
            char ch = chars[index];
            if (flags[index]) {
                if (ch == '0') {
                    ch = '1';
                } else {
                    ch = '0';
                    flags[index - 1] = true;
                }
                flags[index] = false;
            }
            if (ch == '1') {
                flags[index - 1] = true;
                chars[index] = '0';
            } else {
                index--;
            }
            count++;
        }
        if (flags[index]) {
            count++;
        }
        return count;
    }

    public List<Integer> minSubsequence(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int target = sum / 2;
        sum = 0;
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            list.add(nums[i]);
            if (sum > target) {
                break;
            }
        }
        return list;
    }
}
