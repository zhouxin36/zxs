package com.zx.algorithm.leetcode.easy;

import java.util.Stack;

/**
 * @author zhouxin
 * @since 2020/3/26
 */
public class LC538 {

    public static void main(String[] args) {
        System.out.println(new LC538().convertBST(new TreeNode(new Integer[]{
                5, 2, 13
        })).toString());
    }

    public TreeNode convertBST(TreeNode root) {
        doRDL2(root);
        return root;
    }

    // 中序右遍历
    private void doRDL1(TreeNode root) {
        if (root == null){
            return;
        }
        doRDL1(root.right);
        root.val += (pref == null ? 0 : pref.val);
        pref = root;
        doRDL1(root.left);
    }
    private TreeNode pref = null;

    // 中序右遍历
    private void doRDL2(TreeNode root) {
        if (root == null){
            return;
        }
        int prefVal = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            if (root == null){
                TreeNode pop = stack.pop();
                pop.val += prefVal;
                prefVal = pop.val;
                root = pop.left;
            }else {
                stack.push(root);
                root = root.right;
            }
        }
    }

}
