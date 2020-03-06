package com.zx.algorithm.leetcode.easy;

import java.util.Collections;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/6
 */
public class LC119 {


    public static void main(String[] args) {
        System.out.println(new LC119().getRow(32).toString().replace(" ", "").equals("[1,32,496,4960,35960,201376,906192,3365856,10518300,28048800,64512240,129024480,225792840,347373600,471435600,565722720,601080390,565722720,471435600,347373600,225792840,129024480,64512240,28048800,10518300,3365856,906192,201376,35960,4960,496,32,1]"));
        System.out.println(new LC119().getRow(32).toString().replace(" ", ""));
        System.out.println("[1,32,496,4960,35960,201376,906192,3365856,10518300,28048800,64512240,129024480,225792840,347373600,471435600,565722720,601080390,565722720,471435600,347373600,225792840,129024480,64512240,28048800,10518300,3365856,906192,201376,35960,4960,496,32,1]");
        System.out.println(new LC119().getRow(30).toString().replace(" ", "").equals("[1,30,435,4060,27405,142506,593775,2035800,5852925,14307150,30045015,54627300,86493225,119759850,145422675,155117520,145422675,119759850,86493225,54627300,30045015,14307150,5852925,2035800,593775,142506,27405,4060,435,30,1]"));
        System.out.println(new LC119().getRow(18).toString().replace(" ", "").equals("[1,18,153,816,3060,8568,18564,31824,43758,48620,43758,31824,18564,8568,3060,816,153,18,1]"));
        System.out.println(new LC119().getRow(3).toString().replace(" ", "").equals("[1,3,3,1]"));
    }

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return Collections.emptyList();
        }
        Integer[] integers = new Integer[rowIndex + 1];
        long tmp = rowIndex;
        int index = 0;
        while (index <= (rowIndex - index)) {
            switch (index) {
                case 0:
                    integers[index] = integers[rowIndex - index] = 1;
                    break;
                case 1:
                    integers[index] = integers[rowIndex - index] = rowIndex;
                    break;
                default:
                    tmp = tmp * (rowIndex - index + 1) / index;
                    integers[index] = integers[rowIndex - index] = (int)tmp;
            }
            index++;
        }
        return List.of(integers);
    }
}
