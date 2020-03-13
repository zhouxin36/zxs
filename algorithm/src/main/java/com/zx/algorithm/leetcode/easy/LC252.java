package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/13
 */
public class LC252 {

    public static void main(String[] args) {
        // [[0, 30],[5, 10],[15, 20]]
        System.out.println(!new LC252().canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(new LC252().canAttendMeetings(new int[][]{{0, 4}, {5, 10}, {15, 20}}));
    }

    public boolean canAttendMeetings(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i][0] > intervals[j][0] && intervals[i][1] > intervals[j][1] || intervals[i][0] < intervals[j][0] && intervals[i][1] < intervals[j][1]) {

                }else {
                    return false;
                }
            }
        }
        return true;
    }
}
