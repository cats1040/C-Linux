package com.shreya.dynamicprogramming;

import java.util.*;

public class CountUniqueWays {
    private static Map<String, Integer> cache = new HashMap<>();

    public static int travelGrid(int r, int c) {
        String key = r + "--" + c;

        if (cache.containsKey(key)) return cache.get(key);

        if (r == 0 || c == 0) return 0;
        if (r == 1 && c == 1) {
            return 1;
        }
        
        int res = travelGrid(r - 1, c) + travelGrid(r, c - 1);
        cache.put(key, res);

        return res;
    }  
    
    public static void main(String[] args) {
        System.out.println(travelGrid(3,3)); // 6
        System.out.println(travelGrid(1,1)); // 1
        System.out.println(travelGrid(2,3)); // 3
        System.out.println(travelGrid(3,2)); // 3
        System.out.println(travelGrid(20,20)); // TLE

    }
}
