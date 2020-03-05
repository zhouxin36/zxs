package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/5
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class LC111 {

    /**
     * LC答案有问题
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
//        if(root.left == null && root.right == null)
//            return 1;
//        if(root.left == null)
//            return 1 + minDepth(root.right);
//        if(root.right == null)
//            return 1 + minDepth(root.left);
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
