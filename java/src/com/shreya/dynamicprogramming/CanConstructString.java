package com.shreya.dynamicprogramming;

import java.util.Arrays;

public class CanConstructString {
    public static boolean canConstruct(String target, String[] dict) {
        boolean[] tab = new boolean[target.length()];
        Arrays.fill(tab, false);
        tab[0] = true;

        for (int i = 0; i < target.length(); i++) {
            if (tab[i] == true) {
                for (String e: dict) {
                    String newTarget = target.substring(i);
                    if (newTarget.startsWith(e)) {
                        tab[i + e.length()] = true;
                    }
                }
            }
        }

        return tab[target.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(CanConstructString.canConstruct("abcd", new String[]{"ab", "abc", "ce", "c", "c"})); // true
        System.out.println(CanConstructString.canConstruct("abcde", new String[]{"ab", "abc", "ce", "c", "c"})); // false
    }
}
