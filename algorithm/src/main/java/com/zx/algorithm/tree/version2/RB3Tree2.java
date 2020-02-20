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

    private final Node<K, V> NIL = new Node<>();

    private final Node<K, V> sentinel = new Node<>(null, null, 0, false, NIL);

    public void put(K k, V v) {
        if (getRoot().equals(NIL)) {
            Node<K, V> root = new Node<>(k, v, 1, false, NIL);
            sentinel.setRight(root);
            root.setParents(sentinel);
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
        while (!getRoot().equals(node) && !getRoot().equals(node.getParents()) && node.getParents().isRed()) {
            Node<K, V> npp = node.getParents().getParents();
            if (npp.getLeft().isRed() && npp.getRight().isRed()) {
                node = rotateColor(npp);
                continue;
            }
            if (node.getParents().equals(npp.getRight())) {
                if (node.equals(npp.getRight().getLeft())) {
                    node = rotateRight(npp.getRight());
                }
                rotateLeft(npp);
                break;
            } else {
                if (node.equals(npp.getLeft().getRight())) {
                    node = rotateLeft(npp.getLeft());
                }
                rotateRight(npp);
                break;
            }
        }
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        doRotateAfter(node, right, node.getRight());
        return right;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        doRotateAfter(node, left, node.getLeft());
        return left;
    }

    private void doRotateAfter(Node<K, V> node, Node<K, V> x, Node<K, V> y) {
        boolean red = x.isRed();
        x.setRed(node.isRed());
        node.setRed(red);

        if (node.getParents().getLeft().equals(node)) {
            node.getParents().setLeft(x);
        } else {
            node.getParents().setRight(x);
        }
        x.setParents(node.getParents());
        node.setParents(x);
        y.setParents(node);

        int size = node.getSize();
        node.setSize(size - x.getSize() + y.getSize());
        x.setSize(size);
    }

    private Node<K, V> rotateColor(Node<K, V> node) {
        node.setRed(!node.isRed());
        node.getLeft().setRed(!node.getLeft().isRed());
        node.getRight().setRed(!node.getRight().isRed());
        return node;
    }

    private Node<K, V> maxNode(Node<K, V> node) {
        if (node.getRight().equals(NIL)) {
            return node;
        }
        return maxNode(node.getRight());
    }

    private Node<K, V> getNode(Node<K, V> node, K k) {
        if (node.equals(NIL)) {
            return NIL;
        }
        int i = node.getKey().compareTo(k);
        if (i > 0) {
            return getNode(node.getLeft(), k);
        } else if (i < 0) {
            return getNode(node.getRight(), k);
        } else {
            return node;
        }
    }

    private Node<K, V> transplant(Node<K, V> deleteNode, Node<K, V> inheritNode) {
        if (deleteNode.getParents().getRight().equals(deleteNode)) {
            deleteNode.getParents().setRight(inheritNode);
        } else {
            deleteNode.getParents().setLeft(inheritNode);
        }
        inheritNode.setParents(deleteNode.getParents());
        return inheritNode;
    }

    public Node<K, V> getRoot() {
        return sentinel.getRight();
    }

    public void delete(K k) {
        Node<K, V> deleteNode = getNode(getRoot(), k);
        if (deleteNode.equals(NIL)) {
            return;
        }
        Node<K, V> inheritNode;
        boolean red = deleteNode.isRed();
        if (deleteNode.getRight().equals(NIL)) {
            inheritNode = transplant(deleteNode, deleteNode.getLeft());
        } else if (deleteNode.getLeft().equals(NIL)) {
            inheritNode = transplant(deleteNode, deleteNode.getRight());
        } else {
            Node<K, V> maxNode = maxNode(deleteNode.getLeft());
            red = maxNode.isRed();
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

        if (!red) {
            deleteAfter(inheritNode);
        }
    }

    private void deleteAfter(Node<K, V> node) {
        while (!getRoot().equals(node) && !node.isRed()) {
            if (node.getParents().getLeft().equals(node)) {
                Node<K, V> npr = node.getParents().getRight();
                if (npr.isRed()) {
                    rotateLeft(node.getParents());
                    npr = node.getParents().getRight();
                }
                if (!npr.getRight().isRed() && !npr.getLeft().isRed()) {
                    npr.setRed(true);
                    node = node.getParents();
                } else {
                    if (!npr.getRight().isRed()) {
                        npr = rotateRight(npr);
                    }
                    npr.getRight().setRed(false);
                    rotateLeft(npr.getParents());
                    break;
                }
            } else {
                Node<K, V> npl = node.getParents().getLeft();
                if (npl.isRed()) {
                    rotateRight(node.getParents());
                    npl = node.getParents().getLeft();
                }
                if (!npl.getRight().isRed() && !npl.getLeft().isRed()) {
                    npl.setRed(true);
                    node = node.getParents();
                } else {
                    if (!npl.getLeft().isRed()) {
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
}
