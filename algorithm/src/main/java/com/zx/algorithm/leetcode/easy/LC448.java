package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/22
 */
public class LC448 {

    public static void main(String[] args) {
        // [5,6]
        System.out.println(new LC448().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        return findDisappearedNumbers1(nums);
    }
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int j = Math.abs(nums[i]) - 1;
            // 标记对应下标的桶为负，说明这个桶里的数字出现了，没被篡改
            if (nums[j] > 0){
                nums[j] = - nums[j];
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 将所有的数组遍历之后，没被标记的说明被篡改了，也就是没出现的
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;
    }
}
