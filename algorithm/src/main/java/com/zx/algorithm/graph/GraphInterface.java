package com.zx.algorithm.graph;

/**
 * @author zhouxin
 * @since 2019/5/24
 */
public interface GraphInterface {
    String NEWLINE = "||";

    int V();

    int E();

    void addEdge(int v, int w) ;

    Iterable<Integer> adj(int v);

    int degree(int v);

    default String toStr(){
        StringBuilder s = new StringBuilder();
        s.append(V() + " vertices, " + E() + " edges " + NEWLINE);
        for (int v = 0; v < V(); v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
