package com.shreya.dynamicprogramming;

public class longestCommonSubseq {
    public static int lcs(String m, String n) {
        int [][] dp = new int[m.length() + 1][n.length() + 1];

        for (int i = 1; i < m.length() + 1; i++) {
            for (int j = 1; j < n.length() + 1; j++) {
                if (m.charAt(i - 1) == n.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int i = m.length();
        int j = n.length();

        StringBuilder lcsStr = new StringBuilder();
        while (i > 0 && j > 0) {
            if (m.charAt(i - 1) == n.charAt(j - 1)) {
                lcsStr.append(m.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        System.out.println("LCS String: " + lcsStr.reverse().toString());

        return dp[m.length()][n.length()];
    }

    public static void main(String[] args) {
        System.out.println(lcs("AGGTAB", "GXTXAYB"));
        System.out.println(lcs("abcde", "be"));
    }
}
