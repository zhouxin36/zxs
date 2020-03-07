package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/7
 */
public class LC169 {

    public static void main(String[] args) {
        System.out.println(new LC169().majorityElement(new int[]{3, 2, 3}) == 3);
        System.out.println(new LC169().majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}) == 2);
    }

    /**
     * 因为多数是超过一半的，所以一定存在对消其他的还有剩余
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
