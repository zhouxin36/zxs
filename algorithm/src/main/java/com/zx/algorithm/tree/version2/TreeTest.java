package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.PrintWrapper;

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
        test1();
//        test2();
    }

    private static void test2() {
        RB3Tree3<Integer, Integer> characterRB3Tree = new RB3Tree3<>();
        List<Integer> integers = Arrays.asList(6, 19, 14, 19, 12, 17, 4, 6, 18, 15, 13, 2, 15, 17, 12, 10, 2, 3, 2, 14, -1, 8, 16, 14);
        boolean put = true;
        for (Integer i : integers) {
            if(i == -1){
                put = false;
                continue;
            }
            if(put) {
                characterRB3Tree.put(i, i);
            }else {
                characterRB3Tree.delete(i);
            }
        }
        PrintWrapper.prettyPrintTree(characterRB3Tree.getRoot());
        PrintWrapper.check(characterRB3Tree.getRoot());
    }

    private static void test1() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        RB3Tree6<Integer, Integer> characterRB3Tree = new RB3Tree6<>();
        RB3Tree2<Integer, Integer> characterRB3Tree2 = new RB3Tree2<>();
        RB3Tree3<Integer, Integer> characterRB3Tree3 = new RB3Tree3<>();
        RB3Tree5<Integer, Integer> characterRB3Tree4 = new RB3Tree5<>();
        int a = 4534;
        int b = 4234;
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
