package com.zx.algorithm.tree.version2;


import com.zx.algorithm.tree.core.Node;

import java.util.Optional;

/**
 * 左倾红黑树
 *
 * @author zhouxin
 * @since 2019/6/6
 */
public class RB2Tree<K extends Comparable<K>, V> {

    private final Node<K, V> sentinel = new Node<>(null, null, 0, false);

    public void put(K k, V v) {
        if (sentinel.getRight() == null) {
            Node<K, V> root = new Node<>(k, v, 1, false);
            root.setParents(sentinel);
            sentinel.setRight(root);
            return;
        }
        doPut(sentinel.getRight(), k, v);
        sentinel.getRight().setRed(false);
    }

    /**
     * 左倾红黑树
     */
    public void doPut(Node<K, V> node, K k, V v) {
        int i = node.getKey().compareTo(k);
        if (i > 0) {
            if (node.getLeft() == null) {
                Node<K, V> kvNode = new Node<>(k, v, 1, true);
                kvNode.setParents(node);
                node.setSize(node.getSize() + 1);
                node.setLeft(kvNode);
            } else {
                doPut(node.getLeft(), k, v);
                node.setSize(1 + node.getLeft().getSize()
                        + Optional.of(node).map(Node::getRight).map(Node::getSize).orElse(0));
            }
        } else if (i < 0) {
            if (node.getRight() == null) {
                Node<K, V> kvNode = new Node<>(k, v, 1, true);
                kvNode.setParents(node);
                node.setSize(node.getSize() + 1);
                node.setRight(kvNode);
            } else {
                doPut(node.getRight(), k, v);
                node.setSize(1 + node.getRight().getSize()
                        + Optional.of(node).map(Node::getLeft).map(Node::getSize).orElse(0));
            }
        } else {
            node.setVal(v);
        }
        doAfter(node);
    }

    public void doAfter(Node<K, V> node) {
        Node<K, V> x = node;
        if (Optional.ofNullable(x).map(Node::getRight).map(Node::isRed).orElse(false)
                && !Optional.of(x).map(Node::getLeft).map(Node::isRed).orElse(false)) {
            x = rotateLeft(x);
        }
        if (Optional.ofNullable(x).map(Node::getLeft).map(Node::isRed).orElse(false)
                && Optional.of(x).map(Node::getLeft).map(Node::getLeft).map(Node::isRed).orElse(false)) {
            x = rotateRight(x);
        }
        if (Optional.ofNullable(x).map(Node::getLeft).map(Node::isRed).orElse(false)
                && Optional.of(x).map(Node::getRight).map(Node::isRed).orElse(false)) {
            rotateColor(x);
        }
    }

    /**
     * 左旋转
     */
    public Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> x = node.getRight();
        Node<K, V> y = x.getLeft();
        // 换节点
        node.setRight(x.getLeft());
        x.setLeft(node);
        doRotateAfter(node, x, y);
        return x;
    }

    /**
     * 右旋转
     */
    public Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> x = node.getLeft();
        Node<K, V> y = x.getRight();
        // 换节点
        node.setLeft(x.getRight());
        x.setRight(node);
        doRotateAfter(node, x, y);
        return x;
    }

    public void rotateColor(Node<K, V> node) {
        node.setRed(!node.isRed());
        node.getLeft().setRed(!node.getLeft().isRed());
        node.getRight().setRed(!node.getRight().isRed());
    }

    public void doRotateAfter(Node<K, V> node, Node<K, V> x, Node<K, V> y) {
        // 换颜色
        x.setRed(node.isRed());
        node.setRed(true);

        // 换parents
        if (y != null)
            y.setParents(node);
        x.setParents(node.getParents());
        node.setParents(x);

        // 调整节点size
        int nodeNize = node.getSize();
        node.setSize(nodeNize - x.getSize() + (y != null ? y.getSize() : 0));
        x.setSize(nodeNize);

        //父节点设置左右节点
        if (node.equals(x.getParents().getRight())) {
            x.getParents().setRight(x);
        } else if (node.equals(x.getParents().getLeft())) {
            x.getParents().setLeft(x);
        }
    }

    public Node<K, V> getRoot() {
        return sentinel.getRight();
    }
}
