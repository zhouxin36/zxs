package com.zx.algorithm.recursive;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归 - N皇后问题
 *
 * @author zhouxin
 * @since 2019/5/8
 */
public class NQueens {

    private final static Logger logger = LoggerFactory.getLogger(NQueens.class);

    private List<List<Integer>> queues = new ArrayList<>();

    public void solveNQueens(int n) {
        doSolveNQueens(new ArrayList<>(), n, 0);
        queues.forEach(e -> {
            logger.info("----------------------------------------");
            for (int i = 0; i < n; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (e.get(i) == j) {
                        stringBuilder.append("Q");
                    } else {
                        stringBuilder.append("×");
                    }
                }
                logger.info("------------{}----------------", stringBuilder.toString());
            }
        });
    }

    private void doSolveNQueens(List<Integer> list, int n, int newLocal) {
        for (int i = 0; i < n; i++) {
            if (newLocal == 0) {
                ArrayList<Integer> newList = new ArrayList<>(list);
                newList.add(i);
                doSolveNQueens(newList, n, 1);
            } else {
                if (isLegal(list, i, newLocal)) {
                    if (list.size() == n) {
                        break;
                    }
                    ArrayList<Integer> newList = new ArrayList<>(list);
                    newList.add(i);
                    doSolveNQueens(newList, n, newLocal + 1);
                }
            }
        }
        if (n == list.size()) {
            queues.add(list);
        }
    }

    /**
     * @param list     位置集合
     * @param newValue 当前新值
     * @param newLocal 当前要放的list的索引位置
     * @return 该值是否合法
     */
    private boolean isLegal(List<Integer> list, int newValue, int newLocal) {
        for (int i = newLocal - 1, j = 1; i >= 0; i--, j++) {
            if (list.get(i) == newValue) {
                return false;
            } else if (list.get(i) + j == newValue) {
                return false;
            } else if (list.get(i) == newValue + j) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
