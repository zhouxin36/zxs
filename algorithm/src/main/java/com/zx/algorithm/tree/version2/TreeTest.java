package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.PrintWrapper;
import com.zx.algorithm.tree.core.Traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author zhouxin
 * @since 2019/12/18
 */
public class TreeTest {


    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        RB3Tree7<Integer, Integer> tree = new RB3Tree7<>();
        List<Integer> integers = Arrays.asList(9, 10, 18, 7, 10, 12, 5, 13, 4, 19, 5, 14, 16, 8, 2, 6, 11, 7, 7, 4);
        boolean put = true;
        for (Integer i : integers) {
            if(i == -1){
                put = false;
                continue;
            }
            if(put) {
                tree.put(i, i);
            }else {
                tree.delete(i);
            }
        }
        PrintWrapper.prettyPrintTree(tree.getRoot());
        PrintWrapper.check(tree.getRoot());
        System.out.println("前序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalDLR));
        System.out.println("前序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalDLR2));
        System.out.println("前序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalDLR3));
        System.out.println("前序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalDLR4));
        System.out.println("中序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalLDR));
        System.out.println("中序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalLDR2));
        System.out.println("中序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalLDR3));
        System.out.println("后序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalLRD));
        System.out.println("后序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalLRD2));
        System.out.println("后序遍历" + Traversal.traversal(tree.getRoot(), Traversal::traversalLRD3));
        System.out.println("前序遍历（右）" + Traversal.traversal(tree.getRoot(), Traversal::traversalDRL));
        System.out.println("层序遍历" + Traversal.traversal(tree.getRoot(), Traversal::stratificationTraversal));
    }

    private static void test1() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        RB3Tree2<Integer, Integer> characterRB3Tree = new RB3Tree2<>();
        RB3Tree3<Integer, Integer> characterRB3Tree3 = new RB3Tree3<>();
        RB3Tree6<Integer, Integer> characterRB3Tree4 = new RB3Tree6<>();
        int a = 9999;
        int b = 9999;
        for (int i = 0; i < a; i++) {
//            System.out.println("-----------------------------------------------------------------------------------------------------");
            int i1 = random.nextInt(b);
            characterRB3Tree.put(i1, i);
//            characterRB3Tree2.put(i1, i);
//            characterRB3Tree3.put(i1, i);
            characterRB3Tree4.put(i1, i);
            list.add(i1);
//            PrintWrapper.prettyPrintTree(characterRB3Tree.getRoot());
//            PrintWrapper.prettyPrintTree(characterRB3Tree4.getRoot());
//            System.out.println(list);
            PrintWrapper.checkTree(characterRB3Tree.getRoot(), characterRB3Tree4.getRoot());
//            characterRB3Tree3.put(i1, i);
//            characterRB3Tree4.put(i1, i);
        }
        list.add(-1);
        for (int i = 0; i < a; i++) {
//            System.out.println("-----------------------------------------------------------------------------------------------------");
            int i1 = random.nextInt(b);
            characterRB3Tree.delete(i1);
//            characterRB3Tree2.delete(i1);
//            characterRB3Tree3.delete(i1);
            characterRB3Tree4.delete(i1);
            list.add(i1);
//            PrintWrapper.prettyPrintTree(characterRB3Tree.getRoot());
//            PrintWrapper.prettyPrintTree(characterRB3Tree4.getRoot());
//            System.out.println(list);
            PrintWrapper.check(characterRB3Tree.getRoot());
            PrintWrapper.check(characterRB3Tree4.getRoot());
            PrintWrapper.checkTree(characterRB3Tree.getRoot(), characterRB3Tree4.getRoot());
//            characterRB3Tree3.put(i1, i);
//            characterRB3Tree4.put(i1, i);
        }
        System.out.println("good");
//        PrintWrapper.check(characterRB3Tree.getRoot());
//        PrintWrapper.check(characterRB3Tree2.getRoot());
//        PrintWrapper.check(characterRB3Tree3.getRoot());
//        PrintWrapper.check(characterRB3Tree4.getRoot());
    }
}
