package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/4/1
 */
public class LC617 {

    public static void main(String[] args) {
        System.out.println(new LC617().mergeTrees(new TreeNode(new Integer[]{1, 3, 2, 5}), new TreeNode(new Integer[]{2, 1, 3, null, 4, null,7})));
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return doMergeTrees(t1, t2);
    }
    public TreeNode doMergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null){
            t1.val += t2.val;
            t1.left = doMergeTrees(t1.left, t2.left);
            t1.right = doMergeTrees(t1.right, t2.right);
        }else if (t1 != null){
            t1.left = doMergeTrees(t1.left, null);
            t1.right = doMergeTrees(t1.right, null);
        }else if (t2 != null){
            t1 = t2;
            t1.left = doMergeTrees(null, t2.left);
            t1.right = doMergeTrees(null, t2.right);
        }
        return t1;
    }
}
