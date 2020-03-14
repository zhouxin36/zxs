package com.zx.algorithm.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author zhouxin
 * @since 2020/3/4
 */
public class TreeNode {

    public static final TreeNode NULL = new TreeNode((Integer) null);
    Integer val;
    TreeNode left;
    TreeNode right;

    TreeNode(Integer x) {
        val = x;
    }

    TreeNode(Integer[] arr) {
        val = arr[0];
        Queue<TreeNode> deque = new ArrayDeque<>();
        deque.add(this);
        int index = 1;
        while (index < arr.length) {
            TreeNode node = deque.poll();
            node.left = init(arr[index], deque);
            index++;
            if (index < arr.length) {
                node.right = init(arr[index], deque);
                index++;
            }
        }
    }

    public static TreeNode init(Integer integer, Queue<TreeNode> deque) {
        if (integer == null) {
            return null;
        }
        TreeNode node = new TreeNode(integer);
        deque.add(node);
        return node;
    }

    @Override
    public String toString() {
        Queue<TreeNode> deque = new ArrayDeque<>();
        deque.add(this);
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            TreeNode poll = deque.poll();
            sb.append(print(poll));
            if (poll.left != null) {
                deque.add(poll.left);
            }
            if (poll.right != null) {
                deque.add(poll.right);
            }
        }
        return sb.toString();
    }

    public String print(TreeNode node) {
        if (node == NULL) {
            return "null,";
        }
        return node.val + ",";
    }
}