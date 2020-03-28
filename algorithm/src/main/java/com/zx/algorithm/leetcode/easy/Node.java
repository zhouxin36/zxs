package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public void setChild(Node node){
        this.children.add(node);
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }

    public static Node getInstance(Integer[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        Node node = new Node(arr[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        Node poll = null;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != null && poll != null){
                Node node1 = new Node(arr[i]);
                poll.setChild(node1);
                queue.add(node1);
            }else {
                poll = queue.poll();
            }
        }
        return node;
    }
}