package com.zx.algorithm.tree.core;

/**
 * @author zhouxin
 * @since 2019/6/6
 */
public class Node<K, V> {

    private K key;
    private V val;
    private Node<K, V> parents, left, right;
    private int size;
    private boolean isRed;

    public Node(K key, V val, int size, boolean isRed) {
        this.key = key;
        this.val = val;
        this.size = size;
        this.isRed = isRed;
    }

    public Node(K key, V val, int size, boolean isRed, Node<K, V> nil) {
        this.key = key;
        this.val = val;
        this.size = size;
        this.isRed = isRed;
        this.left = nil;
        this.right = nil;
    }

    public Node(K key, V val, boolean isRed, Node<K, V> nil) {
        this.key = key;
        this.val = val;
        this.size = 1;
        this.isRed = isRed;
        this.left = nil;
        this.right = nil;
    }

    public Node() {
        this.size = 0;
        this.isRed = false;
        this.left = this;
        this.right = this;
        this.parents = this;
    }

    public Node(Node<K, V> nil) {
        this.size = 0;
        this.isRed = false;
        this.left = nil;
        this.right = nil;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public Node<K, V> getParents() {
        return parents;
    }

    public void setParents(Node<K, V> parents) {
        this.parents = parents;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", val=" + val +
                ", size=" + size +
                ", isRed=" + isRed +
                ", parent=" + parents.getKey() +
                '}';
    }
}