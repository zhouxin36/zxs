package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class LC589 {

    public static void main(String[] args) {
        System.out.println(new LC589().preorder(Node.getInstance(new Integer[]{1, null, 3, 2, 4, null, 5, 6})).toString().replace(" ", "").equals("[1,3,5,6,2,4]"));
        System.out.println(new LC589().preorder(Node.getInstance(new Integer[]{1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10, null, null, 11, null, 12, null, 13, null, null, 14})).toString().replace(" ", "").equals("[1,2,3,6,7,11,14,4,8,12,5,9,13,10]"));
    }

    public List<Integer> preorder(Node root) {
        return preorder2(root, new ArrayList<>());
    }

    public List<Integer> preorder1(Node root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        list.add(root.val);
        if (root.children != null && root.children.size() != 0) {
            root.children.forEach(e -> preorder1(e, list));
        }
        return list;
    }

    public List<Integer> preorder2(Node root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            list.add(pop.val);
            if (pop.children != null && pop.children.size() != 0){
                for (int i = pop.children.size() - 1; i >= 0; i--) {
                    stack.push(pop.children.get(i));
                }
            }
        }
        return list;
    }
}
