package com.ccb.interview.algorithm;

/**
 * 全排列
 */
public class AllRange {

    public static void swap(char[] str, int i, int j) {
        if (i == j)
            return;
        str[i] = (char) (str[i] + str[j]);
        str[j] = (char) (str[i] - str[j]);
        str[i] = (char) (str[i] - str[j]);
    }

    public static void allRange(char[] str, int idx, int dep) {
        if (dep == str.length - 1) {
            for (char c : str) {
                System.out.print(c);
            }
            System.out.println();
            return;
        }
        for (int i = idx; i < str.length; i++) {
            swap(str, i, idx);
            allRange(str, idx + 1, dep + 1);
            swap(str, i, idx);
        }
    }

    public static void main(String[] args) {
        char[] str = {'a', 'b', 'c', 'd'};
        allRange(str, 0, 0);
    }
}
