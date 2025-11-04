package src.com.iterable_iterator;

import java.util.Iterator;

public class MyLinkedList implements Iterable<Integer> {
    @Override
    public Iterator<Integer> iterator() {
        // return new MyIterator();

        // 👇 SAME AS ABOVE

        return new Iterator<Integer>() {
             Node temp = head;
            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public Integer next() {
                int t = temp.val;
                temp = temp.next;
                return t;
            } 
        };
    }
    
    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;

    public void insert(int val) {
        Node n = new Node(val);
        if (head == null) {
            head = n;
            return;
        }

        Node temp  = head;
        while (temp.next != null) temp = temp.next;
        temp.next = n;

        return;
    }

    public void print() {
        Node t = head;
        if (t == null) return;
        while (t.next != null) {
            System.out.print(t.val + " -> ");
            t = t.next;
        }
        System.out.println(t.val);
    }

    /**
     * preferred to keep in the same file with the class
     * as it is only one, so no need to create outside.
     */
    public class MyIterator implements Iterator<Integer> {
        Node temp = head;
        @Override
        public boolean hasNext() {
            return temp != null;
        }

        @Override
        public Integer next() {
            int t = temp.val;
            temp = temp.next;
            return t;
        } 
    }
}
