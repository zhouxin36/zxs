package com.zx.algorithm.tree.core;

import java.util.*;

/**
 * @author zhouxin
 * @since 2020/2/25
 */
public interface Traversal {

    static <V, K extends Comparable<K>> List<K> traversal(Node<K, V> node, Traversal traversal) {
        List<K> list = new ArrayList<>();
        traversal.doTraversal(node, list);
        return list;
    }

    /**
     * 前序遍历（右）
     */
    static <V, K extends Comparable<K>> void traversalDRL(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        // 当前节点
        list.add(node.getKey());
        // 右节点
        traversalDRL(node.getRight(), list);
        // 左节点
        traversalDRL(node.getLeft(), list);
    }

    /**
     * 前序遍历
     */
    static <V, K extends Comparable<K>> void traversalDLR(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        // 当前节点
        list.add(node.getKey());
        // 左节点
        traversalDLR(node.getLeft(), list);
        // 右节点
        traversalDLR(node.getRight(), list);
    }

    /**
     * 前序遍历
     */
    static <V, K extends Comparable<K>> void traversalDLR2(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        Stack<Node<K, V>> stack = new Stack<>();
        Stack<Integer> count = new Stack<>();
        stack.push(node);
        count.push(0);
        while (!stack.isEmpty()) {
            Node<K, V> pop = stack.peek();
            Integer c = count.pop();
            if (c == 0) {
                list.add(pop.getKey());
            }
            if (c == 0 && !isNil(pop.getLeft())) {
                stack.push(pop.getLeft());
                count.push(1);
                count.push(0);
                continue;
            }
            if (c < 2 && !isNil(pop.getRight())) {
                stack.push(pop.getRight());
                count.push(2);
                count.push(0);
                continue;
            }
            stack.pop();
        }
    }

    /**
     * 前序遍历
     */
    static <V, K extends Comparable<K>> void traversalDLR3(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        Stack<Node<K, V>> stack = new Stack<>();
        while (!stack.isEmpty() || !isNil(node)) {
            if (isNil(node)) {
                node = stack.pop();
                node = node.getRight();
            } else {
                list.add(node.getKey());
                stack.push(node);
                node = node.getLeft();
            }
        }
    }

    /**
     * 前序遍历
     */
    static <V, K extends Comparable<K>> void traversalDLR4(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        Stack<Node<K, V>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<K, V> pop = stack.pop();
            if (!isNil(pop.getRight())) {
                stack.push(pop.getRight());
            }
            if (!isNil(pop.getLeft())) {
                stack.push(pop.getLeft());
            }
            list.add(pop.getKey());
        }
    }

    private static <V, K extends Comparable<K>> boolean isNil(Node<K, V> node) {
        return node.equals(node.getRight());
    }

    /**
     * 中序遍历
     */
    static <V, K extends Comparable<K>> void traversalLDR(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        // 左节点
        traversalLDR(node.getLeft(), list);
        // 当前节点
        list.add(node.getKey());
        // 右节点
        traversalLDR(node.getRight(), list);
    }

    /**
     * 中序遍历
     */
    static <V, K extends Comparable<K>> void traversalLDR2(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        Stack<Node<K, V>> stack = new Stack<>();
        Stack<Integer> count = new Stack<>();
        stack.push(node);
        count.push(0);
        while (!stack.isEmpty()) {
            Node<K, V> pop = stack.peek();
            Integer c = count.pop();
            if (c == 0 && !isNil(pop.getLeft())) {
                stack.push(pop.getLeft());
                count.push(1);
                count.push(0);
                continue;
            }
            if (c < 2) {
                list.add(pop.getKey());
            }
            if (c < 2 && !isNil(pop.getRight())) {
                stack.push(pop.getRight());
                count.push(2);
                count.push(0);
                continue;
            }
            stack.pop();
        }
    }

    /**
     * 中序遍历
     */
    static <V, K extends Comparable<K>> void traversalLDR3(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        Stack<Node<K, V>> stack = new Stack<>();
        while (!stack.isEmpty() || !isNil(node)) {
            if (isNil(node)) {
                node = stack.pop();
                list.add(node.getKey());
                node = node.getRight();
            } else {
                stack.push(node);
                node = node.getLeft();
            }
        }
    }

    /**
     * 后序遍历
     */
    static <V, K extends Comparable<K>> void traversalLRD(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        // 左节点
        traversalLRD(node.getLeft(), list);
        // 右节点
        traversalLRD(node.getRight(), list);
        // 当前节点
        list.add(node.getKey());
    }

    /**
     * 后序遍历
     */
    static <V, K extends Comparable<K>> void traversalLRD2(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        Stack<Node<K, V>> stack = new Stack<>();
        Stack<Integer> count = new Stack<>();
        stack.push(node);
        count.push(0);
        while (!stack.isEmpty()) {
            Node<K, V> pop = stack.peek();
            Integer c = count.pop();
            if (c == 0 && !isNil(pop.getLeft())) {
                stack.push(pop.getLeft());
                count.push(1);
                count.push(0);
                continue;
            }
            if (c < 2 && !isNil(pop.getRight())) {
                stack.push(pop.getRight());
                count.push(2);
                count.push(0);
                continue;
            }
            list.add(pop.getKey());
            stack.pop();
        }
    }

    /**
     * 后序遍历
     */
    static <V, K extends Comparable<K>> void traversalLRD3(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        Stack<Node<K, V>> stack = new Stack<>();
        Stack<Node<K, V>> remark = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<K, V> pop = stack.pop();
            remark.push(pop);
            if (!isNil(pop.getLeft())) {
                stack.push(pop.getLeft());
            }
            if (!isNil(pop.getRight())) {
                stack.push(pop.getRight());
            }
        }
        while (!remark.isEmpty()) {
            list.add(remark.pop().getKey());
        }
    }

    /**
     * 层序
     */
    static <V, K extends Comparable<K>> void stratificationTraversal(Node<K, V> node, List<K> list) {
        // nil返回
        if (isNil(node)) {
            return;
        }
        Deque<Node<K, V>> deque = new ArrayDeque<>();
        deque.add(node);
        while (!deque.isEmpty()) {
            Node<K, V> n = deque.poll();
            if (!isNil(n.getLeft())) {
                deque.add(n.getLeft());
            }
            if (!isNil(n.getRight())) {
                deque.add(n.getRight());
            }
            list.add(n.getKey());
        }
    }

    <V, K extends Comparable<K>> void doTraversal(Node<K, V> node, List<K> list);
}
