package com.zx.algorithm.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 拓扑排序
 *
 * @author zhouxin
 * @since 2019/5/23
 */
public class DepthFirsOrder {

    private final static Logger logger = LoggerFactory.getLogger(DepthFirsOrder.class);

    private boolean[] marked;    // marked[v] = is there an s-v path?
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reserve;
    private boolean isCycle;


    public DepthFirsOrder(GraphInterface G) {
        marked = new boolean[G.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reserve = new Stack<>();
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i] && !dfs(G, i)) {
                break;
            }
        }
    }

    private boolean dfs(GraphInterface G, int v) {
        pre.offer(v);
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            } else {
                this.isCycle = true;
            }
        }
        post.offer(v);
        reserve.push(v);
        return true;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Stack<Integer> getReserve() {
        if(isCycle){
            logger.info("有环");
        }
        return reserve;
    }
}