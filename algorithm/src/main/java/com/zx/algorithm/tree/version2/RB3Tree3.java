package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.Node;

/**
 * 红黑树V3
 *
 * @author zhouxin
 * @since 2019/12/14
 */
public class RB3Tree3<K extends Comparable<K>, V> {

    public final Node<K, V> NIL = new Node<>();
    public final Node<K, V> sentinel = new Node<>(null, null, 0, false, NIL);

    public void put(K k, V v) {
        if (getRoot().equals(NIL)) {
            Node<K, V> root = new Node<>(k, v, 1, false, NIL);
            root.setParents(sentinel);
            sentinel.setRight(root);
            return;
        }
        doPut(k, v);
        getRoot().setRed(false);
    }

    private void doPut(K k, V v) {
        Node<K, V> node = getRoot();
        while (true) {
            node.setSize(node.getSize() + 1);
            int i = node.getKey().compareTo(k);
            if (i > 0) {
                if (node.getLeft().equals(NIL)) {
                    Node<K, V> left = new Node<>(k, v, 1, true, NIL);
                    node.setLeft(left);
                    left.setParents(node);
                    node = left;
                    break;
                } else {
                    node = node.getLeft();
                }
            } else {
                if (node.getRight().equals(NIL)) {
                    Node<K, V> right = new Node<>(k, v, 1, true, NIL);
                    node.setRight(right);
                    right.setParents(node);
                    node = right;
                    break;
                } else {
                    node = node.getRight();
                }
            }
        }
        doPutAfter(node);
    }

    private void doPutAfter(Node<K, V> node) {
        while (!node.equals(sentinel) && !node.equals(getRoot()) && !node.getParents().equals(getRoot()) && node.getParents().isRed()) {
            Node<K, V> npp = node.getParents().getParents();
            if (npp.getRight().isRed() && npp.getLeft().isRed()) {
                node = rotateColor(npp);
                continue;
            }
            if (npp.getRight().equals(node.getParents())) {
                if (node.getParents().getLeft().equals(node)) {
                    node = rotateRight(node.getParents());
                }
                rotateLeft(npp);
            } else {
                if (node.getParents().getRight().equals(node)) {
                    node = rotateLeft(node.getParents());
                }
                rotateRight(npp);
            }
        }
    }

    public Node<K, V> delete(K k){
        Node<K, V> deleteNode = get(getRoot(), k);
        Node<K, V> inheritNode;
        boolean red = deleteNode.isRed();
        if(deleteNode.getLeft().equals(NIL)){
            inheritNode = transplant(deleteNode, deleteNode.getRight());
        }else if(deleteNode.getRight().equals(NIL)){
            inheritNode = transplant(deleteNode, deleteNode.getLeft());
        }else {
            Node<K, V> maxNode = maxNode(deleteNode.getLeft());
            inheritNode = maxNode.getLeft();
            red = maxNode.isRed();
            if(inheritNode.getParents().equals(maxNode)){
                // 避免NIL节点
                inheritNode.setParents(maxNode);
            }else {
                transplant(maxNode, inheritNode);
                maxNode.setLeft(deleteNode.getLeft());
                maxNode.getLeft().setParents(maxNode);
            }
            transplant(deleteNode, maxNode);
            maxNode.setRight(deleteNode.getRight());
            maxNode.getRight().setParents(maxNode);
            maxNode.setRed(deleteNode.isRed());
        }
        if(!red){
            doDeleteAfter(inheritNode);
        }
        return deleteNode;
    }

    private void doDeleteAfter(Node<K, V> node) {
        while (!node.equals(getRoot()) && !node.isRed()){

        }
    }

    private Node<K, V> transplant(Node<K, V> deleteNode, Node<K, V> node) {
        if (deleteNode.getParents().getRight().equals(deleteNode)) {
            deleteNode.getParents().setRight(node);
        } else {
            deleteNode.getParents().setLeft(node);
        }
        node.setParents(deleteNode.getParents());
        return node;
    }

    private Node<K, V> get(Node<K, V> root, K k) {
        int i = root.getKey().compareTo(k);
        if (i > 0) {
            return get(root.getLeft(), k);
        } else if (i < 0){
            return get(root.getRight(), k);
        }else {
            return root;
        }
    }

    private Node<K, V> maxNode(Node<K, V> node){
        if(node.getRight().equals(NIL)){
            return node;
        }
        return maxNode(node.getRight());
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return rotateAfter(node, right, node.getRight());
    }

    private Node<K, V> rotateColor(Node<K, V> node) {
        node.setRed(!node.isRed());
        node.getLeft().setRed(!node.getLeft().isRed());
        node.getRight().setRed(!node.getRight().isRed());
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> lift = node.getLeft();
        node.setLeft(lift.getRight());
        lift.setRight(node);
        return rotateAfter(node, lift, node.getLeft());
    }

    private Node<K, V> rotateAfter(Node<K, V> node, Node<K, V> x, Node<K, V> y) {
        boolean red = x.isRed();
        x.setRed(node.isRed());
        node.setRed(red);

        x.setParents(node.getParents());
        node.setParents(x);
        y.setParents(node);
        if (x.getParents().getRight().equals(node)) {
            x.getParents().setRight(x);
        } else {
            x.getParents().setLeft(x);
        }

        int size = node.getSize();
        node.setSize(size - x.getSize() + y.getSize());
        x.setSize(size);
        return x;
    }

    public Node<K, V> getRoot() {
        return sentinel.getRight();
    }
}
