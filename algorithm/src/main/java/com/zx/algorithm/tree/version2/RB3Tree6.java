package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.Node;

/**
 * @author zhouxin
 * @since 2019/12/29
 */
public class RB3Tree6<K extends Comparable<K>, V> {

    private final Node<K, V> NIL = new Node<>();
    private final Node<K, V> sentinel = new Node<>(null, null, 0, false, NIL);

    public Node<K, V> getRoot() {
        return sentinel.getRight();
    }

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
            int i = node.getKey().compareTo(k);
            node.setSize(node.getSize() + 1);
            if (i > 0) {
                if (node.getLeft().equals(NIL)) {
                    Node<K, V> left = new Node<>(k, v, 1, true, NIL);
                    node.setLeft(left);
                    left.setParents(node);
                    node = left;
                    break;
                }
                node = node.getLeft();
            } else {
                if (node.getRight().equals(NIL)) {
                    Node<K, V> right = new Node<>(k, v, 1, true, NIL);
                    node.setRight(right);
                    right.setParents(node);
                    node = right;
                    break;
                }
                node = node.getRight();
            }
        }
        doPutAfter(node);
    }

    private void doPutAfter(Node<K, V> node) {
        while (!(node.equals(getRoot()) || node.getParents().equals(getRoot())) && node.getParents().isRed()) {
            Node<K, V> npp = node.getParents().getParents();
            if (npp.getRight().isRed() && npp.getLeft().isRed()) {
                node = rotateColor(npp);
                continue;
            }
            if (node.getParents().equals(npp.getLeft())) {
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

    private Node<K, V> rotateRight(Node<K, V> parents) {
        Node<K, V> left = parents.getLeft();
        parents.setLeft(left.getRight());
        left.setRight(parents);
        doRotateAfter(parents, left, parents.getLeft());
        return left;
    }

    private void doRotateAfter(Node<K, V> parents, Node<K, V> x, Node<K, V> y) {
        boolean red = x.isRed();
        x.setRed(parents.isRed());
        parents.setRed(red);

        x.setParents(parents.getParents());
        parents.setParents(x);
        y.setParents(parents);

        if (x.getParents().getLeft().equals(parents)) {
            x.getParents().setLeft(x);
        } else {
            x.getParents().setRight(x);
        }

        int size = parents.getSize();
        parents.setSize(size - x.getSize() + y.getSize());
        x.setSize(size);
    }

    private Node<K, V> rotateLeft(Node<K, V> parents) {
        Node<K, V> right = parents.getRight();
        parents.setRight(right.getLeft());
        right.setLeft(parents);
        doRotateAfter(parents, right, parents.getRight());
        return right;
    }

    private Node<K, V> rotateColor(Node<K, V> npp) {
        npp.setRed(!npp.isRed());
        npp.getLeft().setRed(!npp.getLeft().isRed());
        npp.getRight().setRed(!npp.getRight().isRed());
        return npp;
    }

    public void delete(K k) {
        Node<K, V> deleteNode = getNode(getRoot(), k);
        if (deleteNode.equals(NIL)) {
            return;
        }
        Node<K, V> inheritNode;
        boolean color = deleteNode.isRed();
        if (deleteNode.getLeft().equals(NIL)) {
            inheritNode = transplant(deleteNode, deleteNode.getRight());
        } else if (deleteNode.getRight().equals(NIL)) {
            inheritNode = transplant(deleteNode, deleteNode.getLeft());
        } else {
            Node<K, V> maxNode = getMaxNode(deleteNode.getLeft());
            color = maxNode.isRed();
            inheritNode = maxNode.getLeft();
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
        if (!color) {
            doDeleteAfter(inheritNode);
        }
    }

    private void doDeleteAfter(Node<K, V> node) {
        while (!node.equals(getRoot()) && !node.isRed()) {
            if(node.getParents().getRight().equals(node)){
                Node<K, V> npl = node.getParents().getLeft();
                if(npl.isRed()){
                    rotateRight(npl.getParents());
                    npl = node.getParents().getLeft();
                }
                if(!npl.getLeft().isRed() && !npl.getRight().isRed()){
                    npl.setRed(true);
                    node = npl.getParents();
                } else {
                    if(!npl.getLeft().isRed()){
                        npl = rotateLeft(npl);
                    }
                    npl.getLeft().setRed(false);
                    rotateRight(npl.getParents());
                    node = getRoot();
                }
            }else {
                Node<K, V> npr = node.getParents().getRight();
                if(npr.isRed()){
                    rotateLeft(npr.getParents());
                    npr = node.getParents().getRight();
                }
                if(!npr.getLeft().isRed() && !npr.getRight().isRed()){
                    npr.setRed(true);
                    node = npr.getParents();
                }else {
                    if(!npr.getRight().isRed()){
                        npr = rotateRight(npr);
                    }
                    npr.getRight().setRed(false);
                    rotateLeft(npr.getParents());
                    node = getRoot();
                }
            }
        }
        node.setRed(false);
    }

    private Node<K, V> getMaxNode(Node<K, V> node) {
        if (node.getRight().equals(NIL)) {
            return node;
        }
        return getMaxNode(node.getRight());
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

    private Node<K, V> getNode(Node<K, V> root, K k) {
        if (root.equals(NIL)) {
            return NIL;
        }
        int i = root.getKey().compareTo(k);
        if (i > 0) {
            return getNode(root.getLeft(), k);
        } else if (i < 0) {
            return getNode(root.getRight(), k);
        } else {
            return root;
        }
    }
}
