package com.zx.algorithm;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author zhouxin
 * @since 2020/3/16
 */
public class Main {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String ipv6 = in.next();
//        System.out.println(ipv6(ipv6));
        System.out.println(ipv6("::1"));
        System.out.println(ipv6("::0000"));
        System.out.println(ipv6("::0001"));
        System.out.println(ipv6("::000G"));
        System.out.println(ipv6("FE81:0001:0000:0000:FF01:0203:0405:0607"));
        System.out.println(ipv6("FE91:0001:0000:0000:FF01:0203:0405:0607"));
        System.out.println(ipv6("FEA1:0001:0000:0000:FF01:0203:0405:0607"));
        System.out.println(ipv6("FFA1:0001:0000:0000:FF01:0203:0405:"));
        System.out.println(ipv6("FFA1:0001:0000:0000:FF01:0203:0a05:0607"));

    }

    private static String ipv6(String ipv6) {
        if (ipv6 == null) {
            return "Error";
        }
        Pattern pr1 = Pattern.compile("^::[0-9A-F]{4}");
        Pattern pr2 = Pattern.compile("^::[0-9A-F]");
        Pattern pr3 = Pattern.compile("^([0-9A-F]{4}:){7}[0-9A-F]{4}");
        if (!pr1.matcher(ipv6).matches() && !pr2.matcher(ipv6).matches() && !pr3.matcher(ipv6).matches()){
            return "Error";
        }
//        if (ipv6.contains("::")) {
//            String[] split = ipv6.split("::");
//            if (split[0].equals("")) {
//                if (split[1].length() == 1) {
//                    if (!check(split[1].charAt(0))) {
//                        return "Error";
//                    }
//                } else {
//                    if (!check(split[1])) {
//                        return "Error";
//                    }
//                }
//            } else {
//                return "Error";
//            }
//        } else {
//            String[] split = ipv6.split(":");
//            if (split.length != 8) {
//                return "Error";
//            } else {
//                for (String s : split) {
//                    if (!check(s)) {
//                        return "Error";
//                    }
//                }
//            }
//        }
        switch (ipv6) {
            case "::1":
            case "::0001":
            case "0000:0000:0000:0000:0000:0000:0000:0001":
                return "Loopback";
            case "::":
            case "::0":
            case "::0000":
            case "0000:0000:0000:0000:0000:0000:0000:0000":
                return "Unspecified";
        }
        if (ipv6.startsWith("FF")) {
            return "Multicast";
        }
        if (ipv6.startsWith("FE")) {
            char c = ipv6.charAt(2);
            int num;
            if (c >= '0' && c <= '9') {
                num = c - '0';
            } else {
                num = c - 'A' + 10;
            }
            if (num >= 8 && num <= 11) {
                return "LinkLocal";
            }
            if (num >= 12 && num <= 15) {
                return "SiteLocal";
            }
        }
        return "GlobalUnicast";
    }

    private static boolean check(String str) {
        if (str.length() != 4) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!check(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean check(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z');
    }
}
