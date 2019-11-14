package com.zx.algorithm.graph;


import java.util.Stack;

/**
 * 有向图强连通性
 *
 * @author zhouxin
 * @since 2019/5/27
 */
public class KosarajuSCC {
    private boolean[] marked;     // marked[v] = has vertex v been visited?
    private int[] id;             // id[v] = id of strong component containing v
    private int count = 1;            // number of strongly-connected components

    public KosarajuSCC(DiGraph G) {

        DepthFirsOrder dfs = new DepthFirsOrder(G.reverse());

        marked = new boolean[G.V()];
        id = new int[G.V()];
        Stack<Integer> reserve = dfs.getReserve();
        while (!reserve.empty()) {
            int v = reserve.pop();
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }

    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public int count() {
        return count;
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }
}