package com.zx.algorithm.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * @author zhouxin
 * @since 2019/6/5
 */
public class FindMedianSortedArrays {

    private final static Logger logger = LoggerFactory.getLogger(FindMedianSortedArrays.class);

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) { // to ensure m<=n
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j-1] > nums1[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && nums1[i-1] > nums2[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft;
                if (i == 0) { maxLeft = nums2[j-1]; }
                else if (j == 0) { maxLeft = nums1[i-1]; }
                else { maxLeft = Math.max(nums1[i-1], nums2[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight;
                if (i == m) { minRight = nums2[j]; }
                else if (j == n) { minRight = nums1[i]; }
                else { minRight = Math.min(nums2[j], nums1[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        pr(findMedianSortedArrays, new int[]{5}, new int[]{1, 2, 3, 4, 6, 7, 8});
        pr(findMedianSortedArrays, new int[]{1}, new int[]{2});
        pr(findMedianSortedArrays, new int[]{1, 2, 3, 4, 5, 6}, new int[]{7, 8, 9, 10});
        pr(findMedianSortedArrays, new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10});
        pr(findMedianSortedArrays, new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8, 9, 10});
        pr(findMedianSortedArrays, new int[]{6, 7, 8, 9, 10}, new int[]{1, 2, 3, 4, 5});
        pr(findMedianSortedArrays, new int[]{7, 8, 9, 10}, new int[]{1, 2, 3, 4, 5, 6});
        pr(findMedianSortedArrays, new int[]{5, 6, 7, 8, 9, 10}, new int[]{1, 2, 3, 4});
        pr(findMedianSortedArrays, new int[]{}, new int[]{1});
        pr(findMedianSortedArrays, new int[]{}, new int[]{1, 2});
        pr(findMedianSortedArrays, new int[]{}, new int[]{1, 2, 3});
    }

    public static void pr(FindMedianSortedArrays findMedianSortedArrays, int[] a, int[] b) {
        logger.info("a={},b={}中位数：{}", a, b, findMedianSortedArrays.findMedianSortedArrays(a, b));
    }
}
