package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/15
 */
public class LC299 {

    public static void main(String[] args) {
        System.out.println(new LC299().getHint("1123", "0111").equals("1A1B"));
        System.out.println(new LC299().getHint("1807", "7810").equals("1A3B"));
        System.out.println(new LC299().getHint("1234", "0111").equals("0A1B"));
    }

    public String getHint2(String secret, String guess) {
        Map<Character, Integer> map = new HashMap<>();
        int a = 0;
        int b = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                char sc = secret.charAt(i);
                if (map.containsKey(sc)) {
                    Integer remove = map.remove(sc);
                    map.put(sc, remove + 1);
                } else {
                    map.put(sc, 1);
                }
            }
        }
        for (int i = 0; i < guess.length(); i++) {
            char gc = guess.charAt(i);
            if (secret.charAt(i) != gc) {
                if (map.containsKey(gc)) {
                    b++;
                    Integer remove = map.remove(gc);
                    if (remove != 1) {
                        {
                            map.put(gc, remove - 1);
                        }
                    }
                }
            }
        }
        return a + "A" + b + "B";
    }

    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] count = new int[10], given = new int[10];
        for (char c : secret.toCharArray()) {
            count[c - '0']++;
        }
        for (char c : guess.toCharArray()) {
            given[c - '0']++;
        }
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
        }
        for (int i = 0; i < 10; i++) {
            cows += Math.min(count[i], given[i]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(bulls);
        sb.append("A");
        cows = Math.max(cows - bulls, 0);
        sb.append(cows);
        sb.append("B");
        return sb.toString();
    }
}
