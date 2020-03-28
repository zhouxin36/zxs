package com.zx.algorithm.leetcode.easy;

import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class LC559 {

    public static void main(String[] args) {
        System.out.println(new LC559().maxDepth(Node.getInstance(new Integer[]{1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10, null, null, 11, null, 12, null, 13, null, null, 14})) == 5);
        System.out.println(new LC559().maxDepth(Node.getInstance(new Integer[]{1, null, 3, 2, 4, null, 5, 6})) == 3);
    }

    public int maxDepth(Node root) {
        if (root == null){
            return 0;
        }
        List<Node> list = root.children;
        if (list == null || list.size() == 0){
            return 1;
        }else {
            return list.stream().map(this::maxDepth).max(Integer::compareTo).orElse(0) + 1;
        }
    }
}
