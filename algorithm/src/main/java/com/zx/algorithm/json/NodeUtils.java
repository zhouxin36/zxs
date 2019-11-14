package com.zx.algorithm.json;

import java.util.List;
import java.util.Stack;

/**
 * @author zhouxin
 * @since 2019/6/21
 */
public class NodeUtils {

    /**
     * 获取路径与path相同的节点
     *
     * @param nodes 节点集合
     * @param path  路径
     * @return 与path相同的节点，否则返回NIL
     */
    public static Node getNode(List<Node> nodes, String path) {
        if (nodes == null || nodes.isEmpty() || path == null) {
            return Node.NIL;
        }
        return doGetNodeByRecursive(nodes, path);
    }

    /**
     * 递归获取节点
     */
    private static Node doGetNodeByRecursive(List<Node> nodes, String path) {
        if (nodes.isEmpty()) {
            return Node.NIL;
        }
        for (Node node : nodes) {
            if (path.equals(node.getPath())) {
                return node;
            }
            Node resultNode = doGetNodeByRecursive(node.getChildren(), path);
            if (resultNode != Node.NIL) {
                return resultNode;
            }
        }
        return Node.NIL;
    }

    /**
     * 循环获取节点
     */
    private static Node doGetNodeByWhile(List<Node> nodes, String path) {
        if (nodes.isEmpty()) {
            return Node.NIL;
        }
        Stack<Node> stack = new Stack<>();
        List<Node> x = nodes;
        while (!stack.empty() || !x.isEmpty()){
            for (Node node : x) {
                if (path.equals(node.getPath())) {
                    return node;
                }
                stack.push(node);
            }
            x = stack.pop().getChildren();
        }
        return Node.NIL;
    }
}
