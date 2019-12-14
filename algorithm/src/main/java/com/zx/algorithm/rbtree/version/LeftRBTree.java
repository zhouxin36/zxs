package com.zx.algorithm.rbtree.version;

import com.zx.algorithm.rbtree.core.TNode;

/**
 * 左倾红黑树
 *
 * @author zhouxin
 * @since 2019/12/10
 */
@SuppressWarnings("WeakerAccess")
public class LeftRBTree<K extends Comparable<K>, V> {

    public final TNode<K, V> NIL = new TNode<>();
    public final TNode<K, V> sentinel = new TNode<>(NIL);

    public void put(K k, V v) {
        if (sentinel.getRight().equals(NIL)) {
            TNode<K, V> root = new TNode<>(k, v, false, NIL);
            sentinel.setRight(root);
            return;
        }
        sentinel.setRight(doPut(sentinel.getRight(), k, v));
        sentinel.getRight().setRed(false);
    }

    private TNode<K, V> doPut(TNode<K, V> node, K k, V v) {
        int i = node.getKey().compareTo(k);
        if (i > 0) {
            if (node.getLeft().equals(NIL)) {
                TNode<K, V> lift = new TNode<>(k, v, true, NIL);
                node.setLeft(lift);
            } else {
                node.setLeft(doPut(node.getLeft(), k, v));
            }
        } else {
            if (node.getRight().equals(NIL)) {
                TNode<K, V> right = new TNode<>(k, v, true, NIL);
                node.setRight(right);
            } else {
                node.setRight(doPut(node.getRight(), k, v));
            }
        }
        return doPutAfter(node);
    }

    private TNode<K, V> doPutAfter(TNode<K, V> node) {
        if(node.getRight().isRed() && !node.getLeft().isRed()){
            node = turnLeft(node);
        }
        if(node.getLeft().isRed() && node.getLeft().getLeft().isRed()){
            node = turnRight(node);
        }
        if(node.getLeft().isRed() && node.getRight().isRed()){
            changeColor(node);
        }
        return node;
    }

    private void changeColor(TNode<K, V> node) {
        node.setRed(!node.isRed());
        node.getRight().setRed(!node.getRight().isRed());
        node.getLeft().setRed(!node.getLeft().isRed());
    }

    private TNode<K, V> turnRight(TNode<K, V> node) {
        TNode<K, V> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        left.setRed(node.isRed());
        node.setRed(true);
        return left;
    }

    private TNode<K, V> turnLeft(TNode<K, V> node) {
        TNode<K, V> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        right.setRed(node.isRed());
        node.setRed(true);
        return right;
    }

    public static void main(String[] args) {
        Character[] chars1 = {'S', 'E', 'A', 'R', 'C', 'H', 'X', 'M', 'P', 'L'};
        Character[] chars2 = {'A', 'C', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'X'};
        LeftRBTree<Character, Object> leftRBTree = new LeftRBTree<>();
        LeftRBTree<Character, Object> leftRBTree2 = new LeftRBTree<>();
        for(Character c : chars1){
            leftRBTree.put(c,c);
        }
        for(Character c : chars2){
            leftRBTree2.put(c,c);
        }
        TNode.prettyPrintTree(leftRBTree.sentinel.getRight());
        TNode.prettyPrintTree(leftRBTree2.sentinel.getRight());
    }
}
