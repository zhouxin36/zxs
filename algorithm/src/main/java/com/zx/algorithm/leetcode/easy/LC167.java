package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/7
 */
public class LC167 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC167().twoSum(new int[]{2, 7, 11, 15}, 9)).equals(Arrays.toString(new int[]{1, 2})));
    }

    public int[] twoSum(int[] numbers, int target) {
        return twoSum3(numbers, target);
    }

    public int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (target == numbers[i] + numbers[j]) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[0] = map.get(target - numbers[i]) + 1;
                result[1] = i + 1;
            }
            map.put(numbers[i], i);
        }
        return result;
    }

    public int[] twoSum3(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int[] result = new int[2];
        int head = 0;
        int tag = numbers.length - 1;
        while (head < tag) {
            if (numbers[head] + numbers[tag] > target) {
                tag--;
            } else if (numbers[head] + numbers[tag] < target) {
                head++;
            } else {
                result[0] = head + 1;
                result[1] = tag + 1;
                return result;
            }
        }
        return null;
    }
}
