package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhouxin
 * @since 2020/3/4
 */
@SuppressWarnings({"unused"})
public class LC107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for (int size = que.size(); size > 0; size--) {
                TreeNode cur = que.poll();
                list.add(cur.val);
                if (cur.left != null) que.add(cur.left);
                if (cur.right != null) que.add(cur.right);
            }
            res.addFirst(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        levelRecursion(root, result, 0);
        return result;
    }

    private void levelRecursion(TreeNode node,
                                LinkedList<List<Integer>> result, int level) {
        if (node == null) {
            return;
        }
        if (result.size() < level + 1) {// 说明还需要添加一行
            result.addFirst(new ArrayList<>());
        }
        result.get(result.size() - 1 - level).add(node.val);
        levelRecursion(node.left, result, level + 1);
        levelRecursion(node.right, result, level + 1);
    }
}
