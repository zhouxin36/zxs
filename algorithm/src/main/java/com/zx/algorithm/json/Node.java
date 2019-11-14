package com.zx.algorithm.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/6/21
 */
public class Node {

    public final static Node NIL = new Node("", new Object());

    private String path;

    private Object value;

    private List<Node> children;

    public Node(String path, Object value) {
        if(path == null){
            throw new IllegalArgumentException("路径path不能为空");
        }
        this.path = path;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Node> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Node addChild(Node node){
        if(node == null || node == NIL){
            throw new IllegalArgumentException("node不能为空");
        }
        this.children.add(node);
        return this;
    }

    public void removeChild(int idx){
        this.children.remove(idx);
    }
}
