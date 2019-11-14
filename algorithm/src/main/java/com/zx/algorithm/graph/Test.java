package com.zx.algorithm.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @since 2019/5/23
 */
public class Test {

    private final static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
//        getGraph();
//        getDiGraph();
        getTopolDiGraph();
    }

    /**
     * 无向图
     */
    public static void getGraph() {
        GraphInterface graph = new Graph(6);
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        runGraph(graph);
    }

    /**
     * 有向图
     */
    public static void getDiGraph() {
        GraphInterface graph = new DiGraph(13);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 0);
        graph.addEdge(3, 2);
        graph.addEdge(3, 5);
        graph.addEdge(4, 2);
        graph.addEdge(4, 3);
        graph.addEdge(5, 4);
        graph.addEdge(6, 0);
        graph.addEdge(6, 4);
        graph.addEdge(6, 9);
        graph.addEdge(7, 8);
        graph.addEdge(7, 6);
        graph.addEdge(8, 9);
        graph.addEdge(8, 7);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(10, 12);
        graph.addEdge(11, 12);
        graph.addEdge(11, 4);
        graph.addEdge(12, 9);
        runGraph(graph);
    }

    /**
     * 拓扑用有向图
     */
    public static void getTopolDiGraph() {
        GraphInterface graph = new DiGraph(13);
        graph.addEdge(0, 6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 0);
        graph.addEdge(3, 5);
        graph.addEdge(5, 4);
        graph.addEdge(6, 4);
        graph.addEdge(6, 9);
        graph.addEdge(7, 6);
        graph.addEdge(8, 7);
        graph.addEdge(9, 10);
        graph.addEdge(9, 12);
//        graph.addEdge(12, 9);//环
        graph.addEdge(9, 11);
        graph.addEdge(11, 12);
        runGraph(graph);
    }

    public static void runGraph(GraphInterface graph) {
        DepthFirsOrder depthFirsOrder = new DepthFirsOrder(graph);
        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph, 2);
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, 2);
        if(graph instanceof DiGraph) {
            KosarajuSCC kosarajuSCC = new KosarajuSCC((DiGraph) graph);
            kosarajuSCC.count();
        }
        depthFirstPaths.pathTo(6).forEach(e -> {
            logger.info("深度路径" + e);
        });
        breadthFirstPaths.pathTo(6).forEach(e -> {
            logger.info("广度路径" + e);
        });
        System.out.println(1);
    }
}
