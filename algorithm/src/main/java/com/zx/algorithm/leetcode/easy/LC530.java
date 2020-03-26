package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/26
 */
public class LC530 {

    public static void main(String[] args) {
        System.out.println(new LC530().getMinimumDifference(new TreeNode(new Integer[]{1, null, 3, 2})) == 1);
        System.out.println(new LC530().getMinimumDifference(new TreeNode(new Integer[]{236, 104, 701, null, 227, null, 911})) == 9);
    }

    Integer pref = null;
    int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }
        getMinimumDifference(root.left);
        if (pref != null) {
            min = Math.min(root.val - pref, min);
        }
        pref = root.val;
        getMinimumDifference(root.right);
        return min;
    }
}
