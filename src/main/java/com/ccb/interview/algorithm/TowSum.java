package com.ccb.interview.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TowSum {

    //不能处理负数
//    public int[] twoSum(int[] nums, int target) {
//        int[] res = new int[2];
//        int n = nums.length;
//        int m = target + 1;
//        int pos = 1;
//        boolean[] opt = new boolean[m];
//        for (int i = 0; i < n; i++) {
//            for (int j = m - 1; j > 0; j--) {
//                if (j == nums[i]) {
//                    opt[j] = true;
//                } else if (j > nums[i] && i > 0) {
//                    opt[j] = opt[j - nums[i]] | opt[j];
//                }
//            }
//        }
//
//        if (opt[target]) {
//            int row = n - 1, col = target;
//            while (col != 0) {
//                if (nums[row] > col) {
//                    row--;
//                    continue;
//                } else if (opt[col - nums[row]]) {
//                    res[pos--] = row;
//                    col -= nums[row];
//                }
//                row--;
//            }
//        }
//        return res;
//    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int t;
        for (int i = 0; i < nums.length; i++) {
            t = target - nums[i];
            if (map.containsKey(t)) {
                return new int[]{map.get(t), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {-3, 4, 3, 90};
        int[] ints = new TowSum().twoSum(nums, 0);
        System.out.println(ints[0]+" "+ints[1]);
    }
}
