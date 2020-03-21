package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/21
 */
public class LC437 {

    public static void main(String[] args) {
        System.out.println(new LC437().pathSum(new TreeNode(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1}), 8) == 3);
    }

    public int pathSum(TreeNode root, int sum) {
        final Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return helper(preSum, root, 0, sum);
    }

    private int helper(Map<Integer, Integer> preSum, TreeNode root, int curSum, int target) {
        if (root == null) return 0;
        curSum += root.val;
        int res = preSum.getOrDefault(curSum - target, 0);
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        res += helper(preSum, root.left, curSum, target) + helper(preSum, root.right, curSum, target);
        preSum.put(curSum, preSum.get(curSum) - 1);
        return res;
    }
}
