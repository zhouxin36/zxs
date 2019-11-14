package com.zx.algorithm.tree.version2;


import com.zx.algorithm.tree.core.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * 红黑树
 *
 * @author zhouxin
 * @since 2019/6/6
 */
public class RB3Tree<K extends Comparable<K>, V> {

    private final static Logger logger = LoggerFactory.getLogger(RB3Tree.class);

    private final Node<K, V> nil = new Node<>(null, null, 0, false);

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

    public void doPut(Node<K, V> node, K k, V v) {
        Node<K, V> x = node;
        while (true) {
            int i = x.getKey().compareTo(k);
            if (i > 0) {
                x.setSize(x.getSize() + 1);
                if (x.getLeft() == nil) {
                    Node<K, V> kvNode = new Node<>(k, v, 1, true, nil);
                    kvNode.setParents(x);
                    x.setLeft(kvNode);
                    x = kvNode;
                    break;
                }
                x = x.getLeft();
            } else {
                x.setSize(x.getSize() + 1);
                if (x.getRight() == nil) {
                    Node<K, V> kvNode = new Node<>(k, v, 1, true, nil);
                    kvNode.setParents(x);
                    x.setRight(kvNode);
                    x = kvNode;
                    break;
                }
                x = x.getRight();
            }
        }
        doPutAfter(x);
    }

    public void doPutAfter(Node<K, V> node) {
        Node<K, V> x = node;
        while (x != sentinel.getRight() && x != sentinel && x.getParents() != sentinel.getRight() && x.getParents().isRed()) {
//            logger.info("x:{},x.parents:{}",x.isRed(),x.getParents().isRed());
            if (x.getParents().getParents().getRight() == x.getParents()) {
                Node<K, V> y = x.getParents().getParents().getLeft();
                if (y.isRed()) {
                    rotateColor(x.getParents().getParents());
                    x = x.getParents().getParents();
                    continue;
                } else if (x == x.getParents().getLeft()) {
                    x = x.getParents();
                    rotateRight(x);
                }
                if (x != sentinel.getRight() && x.getParents() != sentinel.getRight()) {
                    rotateLeft(x.getParents().getParents());
                }
            } else {
                Node<K, V> y = x.getParents().getParents().getRight();
                if (y.isRed()){
                    rotateColor(x.getParents().getParents());
                    x = x.getParents().getParents();
                    continue;
                } else if (x == x.getParents().getRight()) {
                    x = x.getParents();
                    rotateLeft(x);
                }
                if (x != sentinel.getRight() && x.getParents() != sentinel.getRight()) {
                    rotateRight(x.getParents().getParents());
                }
            }
        }
    }

    public Node<K, V> get(Node<K, V> node, K k) {
        if (node == nil) {
            return null;
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

    public Node<K, V> minNode(Node<K, V> node) {
        if (node.getLeft() == nil) {
            return node;
        } else {
            return minNode(node.getLeft());
        }
    }

    public Node<K, V> maxNode(Node<K, V> node) {
        if (node.getRight() == nil) {
            return node;
        } else {
            return maxNode(node.getRight());
        }
    }

    public void transplant(Node<K, V> node, Node<K, V> childNode) {
        if (node == sentinel.getRight()) {
            sentinel.setRight(childNode);
        } else if (node.getParents().getLeft() == node) {
            node.getParents().setLeft(childNode);
        } else {
            node.getParents().setRight(childNode);
        }
        childNode.setSize(node.getRight().getSize() + node.getLeft().getSize());
        childNode.setParents(node.getParents());
    }

    public void delete(K k) {
        Node<K, V> deleteNode = get(sentinel.getRight(), k);
        Node<K, V> x;
        boolean color = deleteNode.isRed();
        if(deleteNode.getLeft() != nil && deleteNode.getRight() != nil){
            Node<K, V> maxNode = maxNode(deleteNode.getLeft());
            color = maxNode.isRed();
            x = maxNode.getLeft();
            if (maxNode.getParents() == deleteNode) {
                x.setParents(maxNode);
            } else {
                transplant(maxNode, maxNode.getLeft());
                maxNode.setLeft(deleteNode.getLeft());
                maxNode.getLeft().setParents(maxNode);
            }
            transplant(deleteNode, maxNode);
            maxNode.setRight(deleteNode.getRight());
            maxNode.getRight().setParents(maxNode);
            maxNode.setRed(deleteNode.isRed());
        }else if(deleteNode.getRight() != nil){
            x = deleteNode.getRight();
            transplant(deleteNode, deleteNode.getRight());
        }else if(deleteNode.getLeft() != nil){
            x = deleteNode.getLeft();
            transplant(deleteNode, deleteNode.getLeft());
        }else {
            x = deleteNode;
        }
        if (!color) {
            doDeleteAfter(x);
        }
    }

    private void doDeleteAfter(Node<K, V> x) {
        while (x != sentinel.getRight() && !x.isRed()) {
            if (x.getParents().getLeft() == x) {
                Node<K, V> y = x.getParents().getRight();
                if (y.isRed()) {
                    rotateLeft(x.getParents());
                    y = x.getParents().getRight();
                }
                if (!y.getLeft().isRed() && !y.getRight().isRed()) {
                    y.setRed(true);
                    x = x.getParents();
                } else {
                    if (!y.getRight().isRed()) {
                        rotateRight(y);
                        y = x.getParents().getRight();
                    }
                    y.getRight().setRed(false);
                    rotateLeft(x.getParents());
                    x = sentinel.getRight();
                }
            } else {
                Node<K, V> y = x.getParents().getLeft();
                if (y.isRed()) {
                    rotateRight(x.getParents());
                    y = x.getParents().getLeft();
                }
                if (!y.getLeft().isRed() && !y.getRight().isRed()) {
                    y.setRed(true);
                    x = x.getParents();
                } else {
                    if (!y.getLeft().isRed()) {
                        rotateLeft(y);
                        y = x.getParents().getLeft();
                    }
                    y.getRight().setRed(false);
                    rotateRight(x.getParents());
                    x = sentinel.getRight();
                }
            }
        }
        x.setRed(false);
    }

    /**
     * 左旋转
     */
    public void rotateLeft(Node<K, V> node) {
        Node<K, V> x = node.getRight();
        Node<K, V> y = x.getLeft();
        // 换节点
        node.setRight(x.getLeft());
        x.setLeft(node);
        doRotateAfter(node, x, y);
    }

    /**
     * 右旋转
     */
    public void rotateRight(Node<K, V> node) {
        Node<K, V> x = node.getLeft();
        Node<K, V> y = x.getRight();
        // 换节点
        node.setLeft(x.getRight());
        x.setRight(node);
        doRotateAfter(node, x, y);
    }

    public void rotateColor(Node<K, V> node) {
        node.setRed(!node.isRed());
        node.getLeft().setRed(!node.getLeft().isRed());
        node.getRight().setRed(!node.getRight().isRed());
    }

    public void doRotateAfter(Node<K, V> node, Node<K, V> x, Node<K, V> y) {
        // 换颜色
        boolean xr = x.isRed();
        x.setRed(node.isRed());
        node.setRed(xr);

        // 换parents
        y.setParents(node);
        x.setParents(node.getParents());
        node.setParents(x);

        // 调整节点size
        int nodeNize = node.getSize();
        node.setSize(nodeNize - x.getSize() + y.getSize());
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
