package com.zx.algorithm.tree.version2;

import java.util.*;

public class Main {


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
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        List<Character> source = new ArrayList<>(set);
        List<Character> list = new ArrayList<>();
        int size = source.size();
        StringBuilder sb = new StringBuilder("[");
        c(source, list, size, sb);
        return sb.delete(sb.length() - 2, sb.length()).append("]").toString();
    }

    private static void c(List<Character> source, List<Character> list, int size, StringBuilder sb) {
        if (size == 0) {
            list.forEach(sb::append);
            sb.append(", ");
            return;
        }
        for (int i = 0; i < source.size(); i++) {
            Character ch = source.get(i);
            if (ch == ' ') {
                continue;
            }
            source.set(i, ' ');
            list.add(ch);
            size--;
            c(source, list, size, sb);
            size++;
            source.set(i, ch);
            list.remove(ch);
        }
    }
}
