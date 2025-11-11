package com.shreya.datastructures;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class MyArrayList<T> implements Iterable<T> {
    private class DLLNode {
        T value;
        DLLNode next;
        DLLNode prev;

        DLLNode(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    };

    private class DLL {
        DLLNode head, tail;

        private void insertAtTail(T t) {
            if (tail == null) {
                head = tail = new DLLNode(t);
                return;
            }

            DLLNode newNode =new DLLNode(t);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;

            return;
        }

        private void remove(DLLNode node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            }

            return;
        }
    };

    private int currSize;
    private DLL dll;
    private Map<T, Integer> isPresent;

    public MyArrayList() {
        this.currSize = 0;
        dll = new DLL();
        isPresent = new HashMap<>();
    }

    public void add(T t) {
        this.currSize++;
        dll.insertAtTail(t);

        if (isPresent.containsKey(t)) {
            isPresent.put(t, isPresent.get(t) + 1);
        }
        else {
            isPresent.put(t, 1);
        }

        return;
    }
    
    public void clear() {
        this.currSize = 0;
        isPresent.clear();
        dll = null;

        return;
    }

    public boolean contains(T target) {
        if (isPresent.containsKey(target)) {
            return true;
        }
        return false;
    }

    public boolean containsAll(T target) {
        if (!isPresent.containsKey(target)) return false;

        if (isPresent.size() == 1) return true;
        return false;
    }

    public boolean isEmpty() {
        return currSize == 0;
    }

    public T get(int index) {
        DLLNode temp = dll.head;
        T t = null;

        while (index > 0) {
            t = temp.value;
            temp = temp.next;
            index--;
        }

        return t;
    }

    public MyArrayList<T> subList(int i, int j) {
        MyArrayList<T> ans = new MyArrayList<>();

        DLLNode temp = dll.head;
        
        while (i > 0) {
            temp = temp.next;
            i--;
        }

        while (j > 0) {
            ans.add(temp.value);
            temp = temp.next;
            j--;
        }

        return ans;
    }

    public int size() {
        return currSize;
    }

    public void remove(T t) {
        if (!isPresent.containsKey(t) || currSize == 0) {
            return;
        }

        DLLNode temp = dll.head;
        while (temp != null) {
            if (temp.value == t) {
                dll.remove(temp);
                
                if (isPresent.get(t) == 1) {
                    isPresent.remove(t);
                }
                else {
                    isPresent.put(t, isPresent.get(t) - 1);
                }
                return;
            }

            temp = temp.next;
        }

        return;
    }

    public void removeAll(T t) {
        if (!isPresent.containsKey(t)) {
            return;
        }
    }

    @Override
    public boolean equals(Object other) {
        // TO DO
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");

        for (T t: this) {
            str.append(t);
            str.append(" ");
        }

        return str.toString();
    }

    private class MyIterator implements Iterator<T> {
        DLLNode temp = dll.head;

        @Override
        public boolean hasNext() {
            return (temp != null);
        }

        @Override
        public T next() {
            T t = temp.value;
            temp = temp.next;
            return t;
        }
    }
}
