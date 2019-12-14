package com.zx.algorithm.tree.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /**
     * 递归遍历
     */
    public static void printRec(Node node) {
        List<Node> preList = new ArrayList<>();
        List<Node> minList = new ArrayList<>();
        List<Node> sufList = new ArrayList<>();
        doPrintRec(preList, minList, sufList, node);
        System.out.print("递归 preList:");
        preList.forEach(e -> System.out.print(e.getKey() + " "));
        System.out.println();
        System.out.print("递归 minList:");
        minList.forEach(e -> System.out.print(e.getKey() + " "));
        System.out.println();
        System.out.print("递归 sufList:");
        sufList.forEach(e -> System.out.print(e.getKey() + " "));
        System.out.println();
    }

    private static void doPrintRec(List<Node> preList, List<Node> minList, List<Node> sufList, Node node) {
        if (node.getSize() == 0) {
            return;
        }
        preList.add(node);
        doPrintRec(preList, minList, sufList, node.getLeft());
        minList.add(node);
        doPrintRec(preList, minList, sufList, node.getRight());
        sufList.add(node);
    }

    /**
     * 非递归遍历
     */
    public static void printUnRec(Node node) {
        List<Node> preList = new ArrayList<>();
        List<Node> minList = new ArrayList<>();
        List<Node> sufList = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node x = node;

        do {
            if (x.getSize() != 0 && !sufList.contains(x)) {
                preList.add(x);
                stack.push(x);
                x = x.getLeft();
            } else {
                Node pop = stack.peek();
                if (!minList.contains(pop)) {
                    minList.add(pop);
                }
                x = pop.getRight();
                if ((pop.getRight().getSize() == 0 || sufList.contains(pop.getRight()))
                        && (pop.getLeft().getSize() == 0 || sufList.contains(pop.getLeft()))) {
                    sufList.add(pop);
                    stack.pop();
                }
            }
        } while (!stack.empty());

        System.out.print("非递归preList:");
        preList.forEach(e -> System.out.print(e.getKey() + " "));
        System.out.println();
        System.out.print("非递归minList:");
        minList.forEach(e -> System.out.print(e.getKey() + " "));
        System.out.println();
        System.out.print("非递归sufList:");
        sufList.forEach(e -> System.out.print(e.getKey() + " "));
        System.out.println();
    }

}