package src.com.comparable_comparator;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Student[] students = {
            new Student(1, "a", 40.3f),
            new Student(2, "a", 4.5f),
            new Student(3, "a", 49.4f),
            new Student(4, "a", 20.0f),  
        };

        Arrays.sort(students); 
        System.out.println(Arrays.toString(students));
        
        ArrayList<Integer> a;
    }
    
}
