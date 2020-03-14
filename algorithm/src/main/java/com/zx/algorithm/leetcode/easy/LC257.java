package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/14
 */
public class LC257 {

    public static void main(String[] args) {
        System.out.println(new LC257().binaryTreePaths(new TreeNode(new Integer[]{1, 2, 3, null, 5})));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<String> list = new LinkedList<>();
        a(root, list, "");
        return list;
    }

    public void a(TreeNode root, List<String> list, String str) {
        if (root == null) {
            return;
        }
        str += Integer.toString(root.val);
        if (root.left == null && root.right == null) {
            list.add(str);
        } else {
            str += "->";
            a(root.left, list, str);
            a(root.right, list, str);
        }
    }
}
