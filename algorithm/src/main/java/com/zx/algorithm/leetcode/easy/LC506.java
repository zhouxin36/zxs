package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/25
 */
public class LC506 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC506().findRelativeRanks(new int[]{10,3,8,9,4})).equals("[Gold Medal, 5, Bronze Medal, Silver Medal, 4]"));
        System.out.println(Arrays.toString(new LC506().findRelativeRanks(new int[]{5, 4})).equals("[Gold Medal, Silver Medal]"));
        System.out.println(Arrays.toString(new LC506().findRelativeRanks(new int[]{5, 4, 3, 2, 1})).equals("[Gold Medal, Silver Medal, Bronze Medal, 4, 5]"));
    }

    public String[] findRelativeRanks(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            map.put(nums[i],i);
        }
        Arrays.sort(nums);
        String[] strings = new String[nums.length];
        for (int i = nums.length - 1; i >= 0; i--){
            String str;
            if (i == nums.length - 1){
                str = "Gold Medal";
            }else if (i == nums.length - 2){
                str = "Silver Medal";
            }else if (i == nums.length - 3){
                str = "Bronze Medal";
            }else {
                str = String.valueOf(nums.length - i);
            }
            strings[map.get(nums[i])] = str;
        }
        return strings;
    }
}
