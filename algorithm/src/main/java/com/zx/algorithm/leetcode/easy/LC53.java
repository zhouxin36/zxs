package com.zx.algorithm.leetcode.easy;

/**
 * 连续最大子数组和
 *
 * @author zhouxin
 * @since 2020/3/1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC53 {
    public static void main(String[] args) {
        System.out.println(new LC53().maxSubArray(new int[]{8, -19, 5, -4, 20}) == 21);
        System.out.println(new LC53().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6);
        System.out.println(new LC53().maxSubArray(new int[]{1, 2}) == 3);
//        Random random = new Random();
//        int[] nums = new int[200];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = random.nextInt(1000) - 500;
//        }
//        new LC53().maxSubArray(nums);
    }

    public int maxSubArray(int[] nums) {
//        System.out.println(Arrays.toString(nums));
//        System.out.println(maxSubArray2(nums) + ":" + maxSubArray3(nums));
        return maxSubArray3(nums);
    }

    /**
     * 定义状态：
     * dp[i] ： 表示以 nums[i] 结尾的连续子数组的最大和
     * <p>
     * 状态转移方程：
     * dp[i] = max{num[i],dp[i-1] + num[i]}
     * <p>
     * <p>归纳法证明：</p>
     * <p>i = 1时 成立</p>
     * <p>i = n时 有dp[n] = max{num[n],dp[n-1] + num[n]} </p>
     * <p>i = n + 1时 有dp[n] = max{num[n + 1],dp[n] + num[n + 1]} </p>
     *
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        int n = nums.length;
        int maximum = nums[0];
        int temp = nums[0];
        for (int i = 1; i < n; i++) {
            temp = Math.max(temp + nums[i], nums[i]);
            if (temp > maximum) {
                maximum = temp;
            }
        }
        return maximum;
    }

    /**
     * 暴力遍历
     */
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max) {
                    max = sum;
                    start = i;
                    end = j;
                }
            }
        }
        System.out.println("start:" + start + ",end:" + end);
        return max;
    }

    /**
     * 分治策略
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int mid = (nums.length - 1) >> 1;
        return doMaxSubArray(nums, 0, nums.length - 1);
    }


    /**
     * 分治策略迭代
     */
    public int doMaxSubArray(int[] nums, int start, int end) {
        if (end - start == 1 && nums[start] >= 0 && nums[end] >= 0) {
            return nums[start] + nums[end];
        } else if (end - start == 1) {
            return Math.max(nums[start], nums[end]);
        } else if (end == start) {
            return nums[start];
        }
        int mid = (start + end) / 2;
        int leftSum = doMaxSubArray(nums, start, mid);
        int rightSum = doMaxSubArray(nums, mid + 1, end);
        // 计算中间最大子数组和
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        int midLeftSum = 0;
        int midRightSum = 0;
        for (int i = mid; i >= start; i--) {
            midLeftSum += nums[i];
            if (leftMax < midLeftSum) {
                leftMax = midLeftSum;
            }
        }
        for (int i = mid; i <= end; i++) {
            midRightSum += nums[i];
            if (rightMax < midRightSum) {
                rightMax = midRightSum;
            }
        }
        int midSum = leftMax + rightMax - nums[mid];
        if (leftSum >= rightSum && leftSum >= midSum) {
            return leftSum;
        } else if (rightSum >= leftSum && rightSum >= midSum) {
            return rightSum;
        } else {
            return midSum;
        }
    }
}
