import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        l.insert(1);
        l.insert(2);
        l.insert(3);
        l.insert(4);

        // l.print();

        for(int a: l) {
            System.out.print(a + " ");
        }
        System.out.println();

        Iterator<Integer> ite = l.iterator();
        while (ite.hasNext()) {
            System.out.print(ite.next() + " ");
        }
        System.out.println();
    }
}
