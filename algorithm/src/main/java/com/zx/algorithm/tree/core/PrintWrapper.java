package com.zx.algorithm.tree.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zhouxin
 * @since 2019/5/22
 */
public class PrintWrapper {

    private final static Logger logger = LoggerFactory.getLogger(PrintWrapper.class);
    public static void prettyPrintTree(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.getRight().getKey() != null) {
            prettyPrintTree(node.getRight(), prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.getKey() + (node.isRed() ? "|R" : "") + "|" + node.getSize() + "|" + node.getParents().getKey());

        if (node.getLeft().getKey() != null) {
            prettyPrintTree(node.getLeft(), prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(Node node) {
        prettyPrintTree(node, "", true);
    }

    public static <K extends Comparable<K>, V> void checkTree(Node<K, V> source, Node<K, V> target){
        if (!source.getKey().equals(target.getKey()) || source.isRed() != target.isRed()) {
            System.out.println("source:" + source);
            System.out.println("target:" + target);
            throw new RuntimeException("我们不一样~~");
        }
        if(!source.getLeft().equals(source.getLeft().getLeft())){
            checkTree(source.getLeft(), target.getLeft());
        }
        if(!source.getRight().equals(source.getRight().getRight())){
            checkTree(source.getRight(), target.getRight());
        }
    }

    public static <K extends Comparable<K>, V> void  check(Node<K, V> node){
        if (!isBST(node))            throw new RuntimeException("不是二叉树");
        if (!isBalanced(node))       throw new RuntimeException("不是平衡树");
    }

    private static <V, K extends Comparable<K>> boolean isBalanced(Node<K, V> node) {
        int black = 0;
        Node<K, V> x = node;
        while (x != x.getRight()) {
            if (!x.isRed()) black++;
            x = x.getLeft();
        }
        return isBalanced(node, black);
    }

    private static <V, K extends Comparable<K>> boolean isBalanced(Node<K, V> node, int black) {
        if (node == node.getLeft())
            return black == 0;
        if (!node.isRed()) black--;
        return isBalanced(node.getLeft(), black) && isBalanced(node.getRight(), black);
    }

    private static <V, K extends Comparable<K>> boolean isBST(Node<K, V> node) {
        if(node.getLeft().equals(node.getRight())){
            return true;
        }
        if(node.getLeft().getKey() != null){
            if(node.getLeft().getKey().compareTo(node.getKey()) <= 0){
                return isBST(node.getLeft());
            }else {
                logger.error("node:{},child:{}", node, node.getLeft());
                throw new RuntimeException("不是二叉树");
            }
        }
        if(node.getRight().getKey() != null){
            if(node.getRight().getKey().compareTo(node.getKey()) >= 0){
                return isBST(node.getRight());
            }else {
                logger.error("node:{},child:{}", node, node.getRight());
                throw new RuntimeException("不是二叉树");
            }
        }
        return false;
    }

}