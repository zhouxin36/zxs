package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/5
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class LC112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return root.val == sum && root.left == null && root.right == null || hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
