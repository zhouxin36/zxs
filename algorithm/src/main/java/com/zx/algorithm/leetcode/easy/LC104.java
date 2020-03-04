package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/4
 */
@SuppressWarnings({"unused"})
public class LC104 {

    public int maxDepth(TreeNode root) {
        if(root != null){
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }else {
            return 0;
        }
    }

}
