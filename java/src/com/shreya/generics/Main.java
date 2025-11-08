import java.util.ArrayList;
import java.util.List;

class Student {
    public <T> void display(T a, T b) {
        System.out.println(a + " " + b);
    }

    public <K extends Number> void show(List<K> list) {
        for (K element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public void doSomething(List<Number> list) {
        for (Number element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public <T> T doSomething2(T t) {
        System.out.println(t);
        return t;
    }

    // Number or below
    public void doSomething3(List<? extends Number> list) {
        for (Number element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // doubt
    // Integer or its parents
    public void doSomething4(List<? super Integer> list) {
        for (Object element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public void doSomething5(List<? super Integer> list) {
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }

        for (Object element : list) {
            System.out.print(element + " ");
        }

        System.out.println();
    }
}; 

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();

        s1.display(10, 20);           
        s2.display("Hello", "World");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(10);
        numbers.add(8);
        numbers.add(120);
        numbers.add(19);

        s1.show(numbers);
        // s1.doSomething(numbers); // wrong
        s1.doSomething3(numbers);
        s1.doSomething4(numbers);

        List<Object> l = new ArrayList<Object>();
        l.add("abc");
        l.add("def");
        l.add("ghi");
        l.add("jkl");

        s1.doSomething5(l);
    }
}