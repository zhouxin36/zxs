package com.zx.algorithm.tree.core;

/**
 * @author zhouxin
 * @since 2019/5/22
 */
public class PrintWrapper {

    public static void prettyPrintTree(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.getRight() != null && !node.getRight().equals(node) && node.getRight().getSize() != 0) {
            prettyPrintTree(node.getRight(), prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.getKey() + (node.isRed() ? "|R" : "") + "|" + node.getSize() + "|" + node.getParents().getKey());

        if (node.getLeft() != null && !node.getLeft().equals(node) && node.getLeft().getSize() != 0) {
            prettyPrintTree(node.getLeft(), prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(Node node) {
        prettyPrintTree(node, "", true);
    }

    public static <K, V> void checkTree(Node<K, V> source, Node<K, V> target){
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

}