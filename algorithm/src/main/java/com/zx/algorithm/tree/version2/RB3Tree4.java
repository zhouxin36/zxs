package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.Node;

/**
 * @author zhouxin
 * @since 2019/12/18
 */
@SuppressWarnings({"DuplicatedCode", "WeakerAccess", "UnusedReturnValue"})
public class RB3Tree4<K extends Comparable<K>, V> {

    private final Node<K, V> NIL = new Node<>();
    private final Node<K, V> sentinel = new Node<>(null, null, 0, false, NIL);

    public void put(K k, V v) {
        if (sentinel.getRight().equals(NIL)) {
            Node<K, V> root = new Node<>(k, v, 1, false, NIL);
            root.setParents(sentinel);
            sentinel.setRight(root);
            return;
        }
        doPut(k, v);
        sentinel.getRight().setRed(false);
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
        while (!(getRoot().equals(node) || getRoot().equals(node.getParents()) || getRoot().getParents().equals(node.getParents())) && node.getParents().isRed()) {
            Node<K, V> npp = node.getParents().getParents();
            if (npp.getRight().isRed() && npp.getLeft().isRed()) {
                node = rotateColor(npp);
                continue;
            }
            if (npp.getLeft().equals(node.getParents())) {
                if (node.getParents().getRight().equals(node)) {
                    rotateLeft(node.getParents());
                }
                rotateRight(npp);
                break;
            } else {
                if (node.getParents().getLeft().equals(node)) {
                    rotateRight(node.getParents());
                }
                rotateLeft(npp);
                break;
            }
        }
    }

    public Node<K, V> delete(K k) {
        Node<K, V> deleteNode = get(getRoot(), k);
        if (deleteNode.equals(NIL)) {
            return NIL;
        }
        Node<K, V> inheritNode;
        boolean red = deleteNode.isRed();
        if (deleteNode.getLeft().equals(NIL)) {
            inheritNode = transplant(deleteNode, deleteNode.getRight());
        } else if (deleteNode.getRight().equals(NIL)) {
            inheritNode = transplant(deleteNode, deleteNode.getLeft());
        } else {
            Node<K, V> maxNode = maxNode(deleteNode.getLeft());
            inheritNode = maxNode.getLeft();
            red = maxNode.isRed();
            if (maxNode.getParents().equals(deleteNode)) {
                inheritNode.setParents(maxNode);
            } else {
                transplant(maxNode, inheritNode);
                maxNode.setLeft(deleteNode.getLeft());
                maxNode.getLeft().setParents(maxNode);
            }
            transplant(deleteNode, maxNode);
            maxNode.setRight(deleteNode.getRight());
            maxNode.getRight().setParents(maxNode);
            maxNode.setRed(deleteNode.isRed());
        }
        if (!red) {
            doDeleteAfter(inheritNode);
        }
        return deleteNode;
    }

    private void doDeleteAfter(Node<K, V> node) {
        while (!node.equals(getRoot()) && !node.isRed()) {
            if (node.getParents().getLeft().equals(node)) {
                Node<K, V> npr = node.getParents().getRight();
                if (npr.isRed()) {
                    rotateLeft(npr.getParents());
                    npr = node.getParents().getRight();
                }
                if (!npr.getRight().isRed() && !npr.getLeft().isRed()) {
                    npr.setRed(true);
                    node = npr.getParents();
                } else {
                    if(!npr.getRight().isRed()){
                        npr = rotateRight(npr);
                    }
                    npr.getRight().setRed(false);
                    rotateLeft(npr.getParents());
                    node = getRoot();
                }
            } else {
                Node<K, V> npl = node.getParents().getLeft();
                if(npl.isRed()){
                    rotateRight(npl.getParents());
                    npl = node.getParents().getLeft();
                }
                if(!npl.getRight().isRed() && !npl.getLeft().isRed()){
                    npl.setRed(true);
                    node = npl.getParents();
                }else {
                    if(!npl.getLeft().isRed()){
                        npl = rotateLeft(npl);
                    }
                    npl.getLeft().setRed(false);
                    rotateRight(npl.getParents());
                    node = getRoot();
                }
            }
        }
        node.setRed(false);
    }

    private Node<K, V> rotateRight(Node<K, V> npp) {
        Node<K, V> left = npp.getLeft();
        npp.setLeft(left.getRight());
        left.setRight(npp);
        return rotateAfter(npp, left, npp.getLeft());
    }

    private Node<K, V> rotateLeft(Node<K, V> npp) {
        Node<K, V> right = npp.getRight();
        npp.setRight(right.getLeft());
        right.setLeft(npp);
        return rotateAfter(npp, right, npp.getRight());
    }

    private Node<K, V> rotateAfter(Node<K, V> npp, Node<K, V> x, Node<K, V> y) {
        boolean red = npp.isRed();
        npp.setRed(x.isRed());
        x.setRed(red);

        x.setParents(npp.getParents());
        npp.setParents(x);
        y.setParents(npp);

        if (x.getParents().getRight().equals(npp)) {
            x.getParents().setRight(x);
        } else {
            x.getParents().setLeft(x);
        }

        int size = npp.getSize();
        npp.setSize(size - x.getSize() + y.getSize());
        x.setSize(size);
        return x;
    }

    private Node<K, V> rotateColor(Node<K, V> npp) {
        npp.setRed(!npp.isRed());
        npp.getRight().setRed(!npp.getRight().isRed());
        npp.getLeft().setRed(!npp.getLeft().isRed());
        return npp;
    }

    private Node<K, V> get(Node<K, V> node, K k) {
        if (node.getKey() == null) {
            return NIL;
        }
        int i = node.getKey().compareTo(k);
        if (i > 0) {
            return get(node.getLeft(), k);
        } else if (i < 0) {
            return get(node.getRight(), k);
        } else {
            return node;
        }
    }

    private Node<K, V> transplant(Node<K, V> deleteNode, Node<K, V> node) {
        if (deleteNode.getParents().getLeft().equals(deleteNode)) {
            deleteNode.getParents().setLeft(node);
        } else {
            deleteNode.getParents().setRight(node);
        }
        node.setParents(deleteNode.getParents());
        return node;
    }

    private Node<K, V> maxNode(Node<K, V> node) {
        if (node.getRight().equals(NIL)) {
            return node;
        }
        return maxNode(node.getRight());
    }

    public Node<K, V> getRoot() {
        return sentinel.getRight();
    }
}
