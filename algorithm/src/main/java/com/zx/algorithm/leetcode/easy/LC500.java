package com.zx.algorithm.leetcode.easy;

import java.util.*;

/**
 * @author zhouxin
 * @since 2020/3/24
 */
public class LC500 {

    private static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('q', 1);
        map.put('w', 1);
        map.put('e', 1);
        map.put('r', 1);
        map.put('t', 1);
        map.put('y', 1);
        map.put('u', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('p', 1);
        map.put('a', 2);
        map.put('s', 2);
        map.put('d', 2);
        map.put('f', 2);
        map.put('g', 2);
        map.put('h', 2);
        map.put('j', 2);
        map.put('k', 2);
        map.put('l', 2);
        map.put('z', 3);
        map.put('x', 3);
        map.put('c', 3);
        map.put('v', 3);
        map.put('b', 3);
        map.put('n', 3);
        map.put('m', 3);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC500().findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})).equals(Arrays.toString(new String[]{"Alaska", "Dad"})));
    }

    public String[] findWords(String[] words) {
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 0) {
                arr.add("");
            } else {
                char ch = words[i].charAt(0);
                int n = map.get(ch < 'a' ? (char) (ch + 32) : ch);
                boolean is = true;
                for (int j = 1; j < words[i].length(); j++) {
                    ch = words[i].charAt(j);
                    int n1 = map.get(ch < 'a' ? (char) (ch + 32) : ch);
                    if (n1 != n){
                        is = false;
                        break;
                    }
                }
                if (is){
                    arr.add(words[i]);
                }
            }
        }
        return arr.toArray(new String[]{});
    }
}
