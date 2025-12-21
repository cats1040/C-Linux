package com.shreya.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBestSum {
    /**
     * Height of tree: target / minimum_element
     * T.C: target^minimum_element
     */

    final Map<Integer, List<Integer>> cache;
    public FindBestSum() {
        this.cache = new HashMap<>();
    }

    public List<Integer> findSum(int target, int[] a) {
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;
        
        List<Integer> res = null;

        for (int i: a) {
            List<Integer> r = findSum(target - i, a);
            if (r != null) {
                List<Integer> temp = new ArrayList<>(r);
                temp.add(i);
                if (res == null || res.size() > temp.size()) res = temp; 
            }
        }

        return res;
    }

    public List<Integer> findSumOptimized(int target, int[] a) {
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;
        
        if (cache.get(target) != null) return cache.get(target);

        List<Integer> res = null;

        for (int i: a) {
            List<Integer> r = findSumOptimized(target - i, a);
            if (r != null) {
                List<Integer> temp = new ArrayList<>(r);
                temp.add(i);
                if (res == null || res.size() > temp.size()) res = temp; 
            }
        }

        cache.put(target, res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FindBestSum().findSumOptimized(7, new int[]{2, 3, 7})); // [7]
        System.out.println(new FindBestSum().findSumOptimized(100, new int[]{2, 5, 25})); // [25,25,2,5,25]
        System.out.println(new FindBestSum().findSumOptimized(7, new int[]{2, 4})); // null
    }
}
