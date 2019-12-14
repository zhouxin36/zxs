package com.zx.algorithm.rbtree.core;

/**
 * @author zhouxin
 * @since 2019/12/10
 */
public class TNode<K, V> {

    private K key;
    private V val;
    private TNode<K, V> left, right;
    private int size;
    private boolean isRed;

    public TNode(K key, V val, int size, boolean isRed) {
        this.key = key;
        this.val = val;
        this.size = size;
        this.isRed = isRed;
    }

    public TNode(K key, V val, int size, boolean isRed, TNode<K, V> nil) {
        this.key = key;
        this.val = val;
        this.size = size;
        this.isRed = isRed;
        this.left = nil;
        this.right = nil;
    }

    public TNode(K key, V val, boolean isRed, TNode<K, V> nil) {
        this.key = key;
        this.val = val;
        this.size = 1;
        this.isRed = isRed;
        this.left = nil;
        this.right = nil;
    }

    public TNode() {
        this.size = 0;
        this.isRed = false;
        this.left = this;
        this.right = this;
    }

    public TNode(TNode<K, V> nil) {
        this.size = 0;
        this.isRed = false;
        this.left = nil;
        this.right = nil;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public void setLeft(TNode<K, V> left) {
        this.left = left;
    }

    public void setRight(TNode<K, V> right) {
        this.right = right;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

    public TNode<K, V> getLeft() {
        return left;
    }

    public TNode<K, V> getRight() {
        return right;
    }

    public int getSize() {
        return size;
    }

    public boolean isRed() {
        return isRed;
    }

    public static void prettyPrintTree(TNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (!node.getRight().equals(node) && node.getRight().getSize() != 0) {
            prettyPrintTree(node.getRight(), prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.getKey() + (node.isRed() ? "|R" : "") + "|" + node.getSize());

        if (!node.getLeft().equals(node) && node.getLeft().getSize() != 0) {
            prettyPrintTree(node.getLeft(), prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(TNode node) {
        prettyPrintTree(node, "", true);
    }
}
