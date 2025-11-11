package com.shreya.datastructures;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> arr = new MyArrayList<>();
        
        arr.add(1);
        arr.add(3);
        arr.add(-1);
        arr.add(3);
        arr.add(10);

        for (int i: arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(arr);
        System.out.println(arr.contains(99));
        System.out.println(arr.contains(10));

        arr.remove(10);

        System.out.println(arr.contains(10));
    }
}
