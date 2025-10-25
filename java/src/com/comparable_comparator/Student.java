package src.com.comparable_comparator;

public class Student implements Comparable<Student> {
    public int id;
    public String name;
    public float marks;

    Student(int id, String name, float marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student s) {
        if (this.marks == s.marks) return 0;
        if (this.marks < s.marks) return -1;
        return 1;
    }

    @Override
    public String toString() {
        return "Student{" + this.name + " " + this.id + " " + this.marks + "}";
    }
}
