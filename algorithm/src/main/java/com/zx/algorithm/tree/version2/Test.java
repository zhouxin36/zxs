package com.zx.algorithm.tree.version2;

import com.zx.algorithm.tree.core.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author zhouxin
 * @since 2019/6/6
 */
public class Test {

    private final static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
//        btTest();
//        charTest2();
        charTest2();
    }

    public static void btTest() {
        RB2Tree<Integer, Integer> kObjectRB2Tree = new RB2Tree<>();
        Random random = new Random();
        int[] ints = new int[10];
        int[] ints2 = new int[]{3, 8, 2, 5, 6, 0, 7, 1};
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(10);
            kObjectRB2Tree.put(ints[i], ints[i]);
        }
//        for (int i = 0; i < ints2.length; i++) {
//            kObjectRB2Tree.put(ints2[i], ints2[i]);
//        }
        logger.info("ints:{}", ints);
        PrintWrapper.prettyPrintTree(kObjectRB2Tree.getRoot());
    }

    public static void btTest2() {
        RB3Tree<Integer, Integer> kObjectRB2Tree = new RB3Tree<>();
        Random random = new Random();
        int[] ints = new int[100];
//        int[] ints2 = new int[]{3, 8, 2, 5, 6, 0, 7, 1};
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(100);
            kObjectRB2Tree.put(ints[i], ints[i]);
        }
//        for (int i = 0; i < ints2.length; i++) {
//            kObjectRB2Tree.put(ints2[i], ints2[i]);
//        }
        logger.info("ints:{}", ints);
        PrintWrapper.prettyPrintTree(kObjectRB2Tree.getRoot());
        kObjectRB2Tree.delete(5);
        PrintWrapper.prettyPrintTree(kObjectRB2Tree.getRoot());
    }

    public static void charTest() {
        RB2Tree<Character, Character> kObjectRB2Tree = new RB2Tree<>();
        Character[] characters = {'S', 'E', 'A', 'R', 'C', 'H', 'X', 'M', 'P', 'L'};
        for (int i = 0; i < characters.length; i++) {
            kObjectRB2Tree.put(characters[i], characters[i]);
        }
        PrintWrapper.prettyPrintTree(kObjectRB2Tree.getRoot());
    }

    public static void charTest2() {
        RB3Tree<Character, Character> characterRB3Tree = new RB3Tree<>();
        RB3Tree2<Character, Character> characterRB3Tree2 = new RB3Tree2<>();
        Character[] characters = {'S', 'E', 'A', 'R', 'C', 'H', 'X', 'M', 'P', 'L'};
        Character[] C = {'8', '2', '0', '7', '1', '3', '9', '5', '6', '4'};
        for (int i = 0; i < C.length; i++) {
            characterRB3Tree.put(C[i], C[i]);
            characterRB3Tree2.put(C[i], C[i]);
        }
        PrintWrapper.prettyPrintTree(characterRB3Tree2.getRoot());
        PrintWrapper.prettyPrintTree(characterRB3Tree.getRoot());
        PrintWrapper.printUnRec(characterRB3Tree.getRoot());
        PrintWrapper.printRec(characterRB3Tree.getRoot());
//        kObjectRB2Tree.delete('R');
//        PrintWrapper.prettyPrintTree(kObjectRB2Tree.getRoot());
    }
}
