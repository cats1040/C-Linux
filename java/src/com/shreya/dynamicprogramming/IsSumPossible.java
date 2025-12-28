package com.shreya.dynamicprogramming;

public class IsSumPossible {
    public  boolean isPossible(int target, int[] a) {
        if (target == 0) return true;
        if (target < 0) return false;

        boolean[] tab = new boolean[target + 1];
        tab[0] = true;

        for (int i = 0; i < target + 1; i++) {
            if (tab[i] == true) {
                for (int e: a) {
                    if (i + e > target) continue;
                    tab[i + e] = true;
                }
            }
        }

        return tab[target];
    }

    public static void main(String[] args) {
        System.out.println(new IsSumPossible().isPossible(7, new int[]{2, 3, 7})); // [7]
        System.out.println(new IsSumPossible().isPossible(100, new int[]{2, 5, 25})); // [25,25,2,5,25]
        System.out.println(new IsSumPossible().isPossible(7, new int[]{2, 4})); // null       
    }
}
