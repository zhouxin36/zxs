package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.Node;

/**
 * @author zhouxin
 * @since 2019/12/20
 */
@SuppressWarnings({"DuplicatedCode", "WeakerAccess"})
public class RB3Tree5<K extends Comparable<K>, V> {

    private final Node<K, V> NIL = new Node<>();

    private final Node<K, V> sentinel = new Node<>(null, null, 0, false, NIL);

    public Node<K, V> getRoot() {
        return sentinel.getRight();
    }

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
        while (!(node.equals(getRoot()) || node.getParents().equals(getRoot())) && node.getParents().isRed()) {
            Node<K, V> npp = node.getParents().getParents();
            if (npp.getRight().isRed() && npp.getLeft().isRed()) {
                node = rotateColor(npp);
                continue;
            }
            if (npp.getRight().equals(node.getParents())) {
                if (node.getParents().getLeft().equals(node)) {
                    rotateRight(node.getParents());
                }
                rotateLeft(npp);
                break;
            } else {
                if (node.getParents().getRight().equals(node)) {
                    rotateLeft(node.getParents());
                }
                rotateRight(npp);
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
        boolean color = deleteNode.isRed();
        if (deleteNode.getLeft().equals(NIL)) {
            inheritNode = transplant(deleteNode, deleteNode.getRight());
        } else if (deleteNode.getRight().equals(NIL)) {
            inheritNode = transplant(deleteNode, deleteNode.getLeft());
        } else {
            Node<K, V> maxNode = maxNode(deleteNode.getLeft());
            inheritNode = maxNode.getLeft();
            color = maxNode.isRed();
            if(maxNode.getParents().equals(deleteNode)){
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
        if(!color){
            doDeleteAfter(inheritNode);
        }
        return deleteNode;
    }

    private void doDeleteAfter(Node<K, V> node) {
        while (!node.equals(getRoot()) && !node.isRed()){
            if(node.getParents().getLeft().equals(node)){
                Node<K, V> npr = node.getParents().getRight();
                if(npr.isRed()){
                    rotateLeft(node.getParents());
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
                    break;
                }
            }else {
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
                    break;
                }
            }
        }
        node.setRed(false);
    }

    private Node<K, V> maxNode(Node<K, V> node){
        if(node.getRight().equals(NIL)){
            return node;
        }
        return maxNode(node.getRight());
    }

    private Node<K, V> transplant(Node<K, V> deleteNode, Node<K, V> node) {
        if(deleteNode.getParents().getLeft().equals(deleteNode)){
            deleteNode.getParents().setLeft(node);
        }else {
            deleteNode.getParents().setRight(node);
        }
        node.setParents(deleteNode.getParents());
        return node;
    }

    private Node<K, V> get(Node<K, V> node, K k) {
        if (node.equals(NIL)) {
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

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return rotateAfter(node, left, node.getLeft());
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return rotateAfter(node, right, node.getRight());
    }

    private Node<K, V> rotateAfter(Node<K, V> node, Node<K, V> x, Node<K, V> y) {
        boolean red = node.isRed();
        node.setRed(x.isRed());
        x.setRed(red);

        x.setParents(node.getParents());
        node.setParents(x);
        y.setParents(node);

        if (x.getParents().getLeft().equals(node)) {
            x.getParents().setLeft(x);
        } else {
            x.getParents().setRight(x);
        }

        int size = node.getSize();
        node.setSize(size - x.getSize() + y.getSize());
        x.setSize(size);
        return x;
    }

    private Node<K, V> rotateColor(Node<K, V> npp) {
        npp.setRed(!npp.isRed());
        npp.getLeft().setRed(!npp.getLeft().isRed());
        npp.getRight().setRed(!npp.getRight().isRed());
        return npp;
    }

}
