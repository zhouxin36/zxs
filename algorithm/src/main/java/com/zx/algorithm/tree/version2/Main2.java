package com.zx.algorithm.tree.version2;

import java.util.*;

public class Main2 {


    /**
     * abc
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(a(str));
    }

    private static String a(String str) {
        Set<Character> set = new HashSet<>();
//        List<Character> source = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
//            source.add(str.charAt(i));
        }
        List<Character> source = new ArrayList<>(set);
        List<String> result = new ArrayList<>();
        int size = source.size();
        c(source, "", size, result);
        return result.toString();
    }

    private static void c(List<Character> source, String str, int size, List<String> result) {
        if (size == 0) {
            result.add(str);
            return;
        }
        for (int i = 0; i < source.size(); i++) {
            Character ch = source.get(i);
            if (ch == null) {
                continue;
            }
            String change = str + ch;
            source.set(i, null);
            size--;
            c(source, change, size, result);
            size++;
            source.set(i, ch);
        }
    }
}
