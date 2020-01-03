package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.Node;

/**
 * @author zhouxin
 * @since 2020/1/3
 */
public class RB3Tree7<K extends Comparable<K>, V> {

    private final Node<K, V> NIL = new Node<>();
    private final Node<K, V> sentinel = new Node<>(null, null, 1, false, NIL);

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
                    left.setParents(node);
                    node.setLeft(left);
                    node = left;
                    break;
                } else {
                    node = node.getLeft();
                }
            } else {
                if (node.getRight().equals(NIL)) {
                    Node<K, V> right = new Node<>(k, v, 1, true, NIL);
                    right.setParents(node);
                    node.setRight(right);
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
        while (!getRoot().equals(node) && !getRoot().equals(node.getParents()) && node.getParents().isRed()) {
            Node<K, V> npp = node.getParents().getParents();
            if (npp.getRight().isRed() && npp.getLeft().isRed()) {
                node = rotateColor(npp);
                continue;
            }
            if (node.getParents().equals(npp.getLeft())) {
                if (node.getParents().getRight().equals(node)){
                    rotateLeft(node.getParents());
                }
                rotateRight(npp);
                break;
            } else {
                if (node.getParents().getLeft().equals(node)){
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
        rotateAfter(parents, left, parents.getLeft());
        return left;
    }

    private Node<K, V> rotateLeft(Node<K, V> parents) {
        Node<K, V> right = parents.getRight();
        parents.setRight(right.getLeft());
        right.setLeft(parents);
        rotateAfter(parents, right, parents.getRight());
        return right;
    }

    private void rotateAfter(Node<K, V> parents, Node<K, V> x, Node<K, V> y) {
        boolean red = x.isRed();
        x.setRed(parents.isRed());
        parents.setRed(red);

        x.setParents(parents.getParents());
        parents.setParents(x);
        y.setParents(parents);

        if(x.getParents().getRight().equals(parents)){
            x.getParents().setRight(x);
        }else {
            x.getParents().setLeft(x);
        }

        int size = parents.getSize();
        parents.setSize(size - x.getSize() + y.getSize());
        x.setSize(size);
    }

    private Node<K, V> rotateColor(Node<K, V> npp) {
        npp.setRed(!npp.isRed());
        npp.getLeft().setRed(!npp.getLeft().isRed());
        npp.getRight().setRed(!npp.getRight().isRed());
        return npp;
    }

    public Node<K, V> getRoot() {
        return sentinel.getRight();
    }

    public Node<K, V> transplant(Node<K, V> node, Node<K, V> inheritNode){
        if(node.getParents().getRight().equals(node)){
            node.getParents().setRight(inheritNode);
        }else {
            node.getParents().setLeft(inheritNode);
        }
        inheritNode.setParents(node.getParents());
        return inheritNode;
    }

    public Node<K, V> getNode(K k, Node<K, V> node){
        if (node.equals(NIL)) {
            return NIL;
        }
        int i = node.getKey().compareTo(k);
        if(i > 0){
            return getNode(k, node.getLeft());
        }else if(i < 0){
            return getNode(k, node.getRight());
        }else {
            return node;
        }
    }

    public Node<K, V> getMaxNode(Node<K, V> node){
        if(node.getRight().equals(NIL)){
            return node;
        }
        return getMaxNode(node.getRight());
    }

    public void delete(K k){
        Node<K, V> deleteNode = getNode(k, getRoot());
        if (deleteNode.equals(NIL)) {
            return;
        }
        Node<K, V> inheritNode;
        boolean red = deleteNode.isRed();
        if(deleteNode.getLeft().equals(NIL)){
            inheritNode = transplant(deleteNode, deleteNode.getRight());
        }else if(deleteNode.getRight().equals(NIL)){
            inheritNode = transplant(deleteNode, deleteNode.getLeft());
        }else {
            Node<K, V> maxNode = getMaxNode(deleteNode.getLeft());
            inheritNode = maxNode.getLeft();
            red = maxNode.isRed();
            if(maxNode.getParents().equals(deleteNode)){
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
            deleteAfter(inheritNode);
        }
    }

    private void deleteAfter(Node<K, V> node) {
        while (!getRoot().equals(node) && !node.isRed()){
            if(node.getParents().getRight().equals(node)){
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
            }else {
                Node<K, V> npr = node.getParents().getRight();
                if(npr.isRed()){
                    rotateLeft(npr.getParents());
                    npr = node.getParents().getRight();
                }
                if(!npr.getRight().isRed() && !npr.getLeft().isRed()){
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
}
