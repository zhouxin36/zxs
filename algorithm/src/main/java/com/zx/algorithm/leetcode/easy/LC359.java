package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/18
 */
public class LC359 {

    public static void main(String[] args) {
        Logger logger = new Logger();
        System.out.println(logger.shouldPrintMessage(1, "foo"));
        System.out.println(logger.shouldPrintMessage(2, "bar"));
        System.out.println(!logger.shouldPrintMessage(3, "foo"));
        System.out.println(!logger.shouldPrintMessage(8, "bar"));
        System.out.println(!logger.shouldPrintMessage(10, "foo"));
        System.out.println(logger.shouldPrintMessage(11, "foo"));
    }
    public static class Logger {

        private Map<String, Integer> map;
        public Logger() {
            map = new HashMap<String, Integer>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            if (!map.containsKey(message)) {
                map.put(message, timestamp);
                return true;
            }else {
                Integer integer = map.get(message);
                if (timestamp - integer >= 10){
                    map.put(message, timestamp);
                    return true;
                }else {
                    return false;
                }
            }
        }
    }

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
}
