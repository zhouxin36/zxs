package com.zx.algorithm.graph;

import edu.princeton.cs.algs4.Bag;

/**
 * @author zhouxin
 * @since 2019/5/24
 */
public class DiGraph implements GraphInterface{

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public DiGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj =  new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }


    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int degree(int v) {
        return adj[v].size();
    }

    public String toString() {
        return toStr();
    }

    /**
     * 反向图
     */
    public DiGraph reverse(){
        DiGraph diGraph = new DiGraph(V());
        for (int i = 0; i < V(); i++) {
            for (Integer integer : adj(i)){
                diGraph.addEdge(integer,i);
            }
        }
        return diGraph;
    }
}
