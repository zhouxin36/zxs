package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/18
 */
public class LC374 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.setNum(6);
        System.out.println(solution.guessNumber(10) == 6);
        solution = new Solution();
        solution.setNum(1702766719);
        System.out.println(solution.guessNumber(2126753390) == 1702766719);


    }

    public static class Solution extends GuessGame {
        public int guessNumber(int n) {
            int start = 1;
            int end = n;
            while (start < end) {
                int mid = start + (end - start) / 2;
                int guess = guess(mid);
                if (guess == 1){
                    start = mid + 1;
                }else if (guess == -1){
                    end = mid - 1;
                }else {
                    return mid;
                }
            }
            return start;
        }
    }

    static class GuessGame {

        private int num;

        public void setNum(int num) {
            this.num = num;
        }

        public int guess(int num) {
            return Integer.compare(this.num, num);
        }
    }
}
