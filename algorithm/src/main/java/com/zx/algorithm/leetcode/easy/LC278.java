package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/15
 */
public class LC278 {

    public static void main(String[] args) {
        System.out.println(new Solution().setFirstBadVersion(1702766719).firstBadVersion(2126753390));
        System.out.println(new Solution().setFirstBadVersion(4).firstBadVersion(5));
        System.out.println(new Solution().setFirstBadVersion(4).firstBadVersion(100));
        System.out.println(new Solution().setFirstBadVersion(2).firstBadVersion(100));
        System.out.println(new Solution().setFirstBadVersion(5).firstBadVersion(100));
    }

    static abstract class VersionControl {

        private int firstBadVersion;

        public VersionControl setFirstBadVersion(int firstBadVersion) {
            this.firstBadVersion = firstBadVersion;
            return this;
        }

        boolean isBadVersion(int version) {
            if (version >= firstBadVersion) {
                return true;
            }
            return false;
        }

        public abstract int firstBadVersion(int n);
    }

    public static class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int start = 1, end = n;
            while (start < end) {
                int mid = start + ((end - start) >> 1);
                if (isBadVersion(mid)) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            return start;
        }
    }
}
