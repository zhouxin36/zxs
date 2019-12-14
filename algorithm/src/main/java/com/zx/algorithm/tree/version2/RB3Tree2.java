package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.Node;

/**
 * 红黑树
 *
 * @author zhouxin
 * @since 2019/12/10
 */
@SuppressWarnings({"unused", "DuplicatedCode", "WeakerAccess"})
public class RB3Tree2<K extends Comparable<K>, V> {

    private final Node<K, V> nil = new Node<>();

    private final Node<K, V> sentinel = new Node<>(null, null, 0, false, nil);

    public void put(K k, V v) {
        if (sentinel.getRight() == nil) {
            Node<K, V> root = new Node<>(k, v, 1, false, nil);
            root.setParents(sentinel);
            sentinel.setRight(root);
            sentinel.setParents(sentinel);
            return;
        }
        doPut(sentinel.getRight(), k, v);
        sentinel.getRight().setRed(false);
    }

    public Node<K, V> delete(K k){
        Node<K, V> deleteNode = deleteGet(sentinel.getRight(), k);
        Node<K, V> nextNode;
        boolean isRed = deleteNode.isRed();
        if(deleteNode.getLeft().equals(nil)){
            nextNode = deleteNode.getRight();
            transplant(deleteNode, nextNode);
        }else if(deleteNode.getRight().equals(nil)){
            nextNode = deleteNode.getLeft();
            transplant(deleteNode, nextNode);
        }else {
            Node<K, V> maxNode = maxNode(deleteNode.getLeft());
            nextNode = maxNode.getLeft();
            isRed = maxNode.isRed();
            if(!maxNode.getParents().equals(deleteNode)){
                transplant(maxNode, nextNode);
                maxNode.setLeft(deleteNode.getLeft());
                maxNode.getLeft().setParents(maxNode);
            }else {
                // nextNode 可能是nil
                nextNode.setParents(maxNode);
            }
            transplant(deleteNode, maxNode);
            maxNode.setRight(deleteNode.getRight());
            maxNode.getRight().setParents(maxNode);
            maxNode.setRed(deleteNode.isRed());
        }
        if(!isRed){
            doDeleteAfter(nextNode);
        }
        return deleteNode;
    }

    private void doDeleteAfter(Node<K, V> nextNode) {
        while (!nextNode.equals(sentinel.getRight()) && !nextNode.isRed()){
            if(nextNode.getParents().getRight().equals(nextNode)){
                Node<K, V> node = nextNode.getParents().getLeft();
                if(node.isRed()){
                    node = rotateRight(node.getParents());
                }
                if(!node.getRight().isRed() && !node.getLeft().isRed()){

                    nextNode = nextNode.getParents();node.setRed(true);
                }else {
                    if(!node.getLeft().isRed()){
                        node = rotateLeft(node);
                    }
                    node.getLeft().setRed(false);
                    rotateRight(node.getParents());
                    nextNode = sentinel.getRight();
                }
            }else {
                Node<K, V> node = nextNode.getParents().getRight();
                if(node.isRed()){
                    node = rotateLeft(node.getParents());
                }
                if(!node.getRight().isRed() && !node.getLeft().isRed()){
                    node.setRed(true);
                    nextNode = nextNode.getParents();
                }else {
                    if(!node.getRight().isRed()){
                        node = rotateRight(node);
                    }
                    node.getRight().setRed(false);
                    rotateLeft(node.getParents());
                    nextNode = sentinel.getRight();
                }
            }
        }
        nextNode.setRed(false);
    }

    public void transplant(Node<K, V> deleteNode, Node<K, V> nextNode){
        Node<K, V> parents = deleteNode.getParents();
        if(parents.getRight().equals(deleteNode)){
            parents.setRight(nextNode);
        }else {
            parents.setLeft(nextNode);
        }
        nextNode.setParents(parents);
    }

    public Node<K, V> get(Node<K, V> node, K k){
        node.setSize(node.getSize() - 1);
        int i = node.getKey().compareTo(k);
        if(i > 0){
            return get(node.getLeft(), k);
        }else if(i < 0){
            return get(node.getRight(), k);
        }else {
            return node;
        }
    }
    private Node<K, V> deleteGet(Node<K, V> node, K k){
        node.setSize(node.getSize() - 1);
        int i = node.getKey().compareTo(k);
        if(i > 0){
            return deleteGet(node.getLeft(), k);
        }else if(i < 0){
            return deleteGet(node.getRight(), k);
        }else {
            return node;
        }
    }

    public Node<K, V> maxNode(Node<K, V> node){
        if(node.getRight().equals(nil)){
            return node;
        }
        return maxNode(node.getRight());
    }
    /**
     * 设置左节点并返回左节点
     */
    private Node<K, V> setLeftParents(Node<K, V> node, Node<K, V> parents){
        parents.setLeft(node);
        node.setParents(parents);
        return node;
    }

    /**
     * 设置右节点并返回右节点
     */
    private Node<K, V> setRightParents(Node<K, V> node, Node<K, V> parents){
        parents.setRight(node);
        node.setParents(parents);
        return node;
    }

    private void doPut(Node<K, V> node, K k, V v) {
        while (true) {
            int i = node.getKey().compareTo(k);
            node.setSize(node.getSize() + 1);
            if (i > 0) {
                if(node.getLeft().equals(nil)){
                    Node<K, V> left = new Node<>(k, v, true, nil);
                    node = setLeftParents(left, node);
                    break;
                }else {
                    node = node.getLeft();
                }
            } else {
                if(node.getRight().equals(nil)){
                    Node<K, V> right = new Node<>(k, v, true, nil);
                    node = setRightParents(right, node);
                    break;
                }else {
                    node = node.getRight();
                }
            }
        }
        doPutAfter(node);
    }

    private void doPutAfter(Node<K, V> node) {
        while (node != sentinel && node != sentinel.getRight() && node.getParents() != sentinel.getRight() && node.getParents().isRed()){
            Node<K, V> p = node.getParents().getParents();
            if(p.getLeft().isRed() && p.getRight().isRed()){
                node = rotateColor(p);
                continue;
            }
            // 二节点（二节点不分裂，所以固定转换不用返回值，当前循环也结束）
            if(p.getRight().equals(node.getParents())){
                if(node.equals(node.getParents().getLeft())){
                    node = rotateRight(node.getParents());
                }
                rotateLeft(p);
            }else {
                if(node.equals(node.getParents().getRight())){
                    node = rotateLeft(node.getParents());
                }
                rotateRight(p);
            }
        }
    }

    private Node<K, V> rotateLeft(Node<K, V> p) {
        Node<K, V> right = p.getRight();
        p.setRight(right.getLeft());
        right.setLeft(p);
        return rotateAfter(p, right, p.getRight());
    }

    private Node<K, V> rotateRight(Node<K, V> p) {
        Node<K, V> left = p.getLeft();
        p.setLeft(left.getRight());
        left.setRight(p);
        return rotateAfter(p, left, p.getLeft());
    }

    private Node<K, V> rotateAfter(Node<K, V> p, Node<K, V> x, Node<K, V> y) {
        boolean leftRed = x.isRed();
        x.setRed(p.isRed());
        p.setRed(leftRed);

        x.setParents(p.getParents());
        y.setParents(p);
        p.setParents(x);

        if(x.getParents().getRight().equals(p)){
            x.getParents().setRight(x);
        }else {
            x.getParents().setLeft(x);
        }

        int size = p.getSize();
        p.setSize(size - x.getSize() + y.getSize());
        x.setSize(size);
        return x;
    }

    private Node<K, V> rotateColor(Node<K, V> p) {
        p.setRed(!p.isRed());
        p.getRight().setRed(!p.getRight().isRed());
        p.getLeft().setRed(!p.getLeft().isRed());
        return p;
    }

    public Node<K, V> getRoot() {
        return sentinel.getRight();
    }
}
