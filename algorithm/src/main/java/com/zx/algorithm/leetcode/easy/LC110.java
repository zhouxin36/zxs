package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/5
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class LC110 {

    /**
     * LC 答案有问题
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) - minDepth(root) <= 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public int maxDepth(TreeNode root) {
        if (root != null) {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        } else {
            return 0;
        }
    }
}
