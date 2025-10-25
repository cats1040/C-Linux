package src.com.comparable_comparator;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if (o1 == null || o1.name == null) return -1;
        if (o2 == null || o2.name == null) return 1;

        // Compare by name alphabetically
        return o1.name.compareTo(o2.name);
    }
}
