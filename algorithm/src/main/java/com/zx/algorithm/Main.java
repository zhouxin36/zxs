package com.zx.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxin
 * @since 2020/3/16
 */
public class Main {
    private Map<String, Map<Integer, Integer>> inMap;
    private Map<String, Map<Integer, Integer>> outMap;

    public Main() {
        inMap = new HashMap<>();
        outMap = new HashMap<>();
    }

    public static void main(String[] args) {
//        System.out.println(findGoodStrings(3, "txa", "zyi", "p"));
        System.out.println(findGoodStrings(3, "szc", "zyi", "p"));
//        System.out.println(findGoodStrings(2, "aa", "da", "b"));
    }

    public int numTeams(int[] rating) {
        int count = 0;
        boolean isAsc;
        for (int i = 0; i < rating.length; i++) {
            for (int j = i + 1; j < rating.length; j++) {
                if (rating[i] < rating[j]) {
                    isAsc = true;
                } else {
                    isAsc = false;
                }
                for (int k = j + 1; k < rating.length; k++) {
                    if (isAsc && rating[j] < rating[k] || !isAsc && rating[j] > rating[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void checkIn(int id, String stationName, int t) {
        if (inMap.containsKey(stationName)) {
            inMap.get(stationName).put(id, t);
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(id, t);
            inMap.put(stationName, map);
        }
    }

    public void checkOut(int id, String stationName, int t) {
        if (outMap.containsKey(stationName)) {
            outMap.get(stationName).put(id, t);
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(id, t);
            outMap.put(stationName, map);
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        Map<Integer, Integer> in = inMap.get(startStation);
        Map<Integer, Integer> out = outMap.get(endStation);
        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger sum = new AtomicInteger(0);
        out.forEach((k, v) -> {
            if (in.containsKey(k)) {
                Integer inVlaue = in.get(k);
                sum.addAndGet(v - inVlaue);
                count.incrementAndGet();
            }
        });
        return sum.get() * 1.0 / count.get();
    }

    public static int findGoodStrings(int n, String s1, String s2, String evil) {
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        char[] ar = new char[n];
        return a(0, a1, a2, ar, evil);
    }

    private static int a(int i, char[] a1, char[] a2, char[] ar, String evil) {
        int sum = 0;
        if (i == 0 || a1[i - 1] == a2[i - 1]){
            for (char j = a1[i]; j <= a2[i]; j++) {
                sum = getSum(i, a1, a2, ar, evil, sum, j);
            }

        }else if (new String(ar, 0, i).equals(new String(a2, 0 ,i))){
            for (char j = 'a'; j <= a2[i]; j++) {
                sum = getSum(i, a1, a2, ar, evil, sum, j);
            }
        }else if (new String(ar, 0, i).equals(new String(a1, 0 ,i))){
            for (char j = a1[i]; j <= 'z'; j++) {
                sum = getSum(i, a1, a2, ar, evil, sum, j);
            }
        }else {
            for (char j = 'a'; j <= 'z'; j++) {
                sum = getSum(i, a1, a2, ar, evil, sum, j);
            }
        }
        return sum;
    }

    private static int getSum(int i, char[] a1, char[] a2, char[] ar, String evil, int sum, char j) {
        ar[i] = j;
        if (new String(ar).contains(evil)){
            return sum;
        }
        if (i < a1.length - 1) {
            sum += a(i + 1, a1, a2, ar, evil);
        }else {
            sum++;
        }
        return sum;
    }

}
