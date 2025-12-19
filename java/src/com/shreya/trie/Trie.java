package com.shreya.trie;

public class Trie {
    private final TrieNode root;

    public void insert(String key, String value) {
        TrieNode t = root;

        for (char c: key.toCharArray()) {
            int index = c - 'a';
            if (t.children[index] == null) {
                t.children[index] = new TrieNode();
            }
            t = t.children[index];
        }

        t.value = value;
    }

    private TrieNode find(String key) {
        TrieNode t = root;

        for (char c: key.toCharArray()) {
            int index = c - 'a';
            if (t.children[index] == null) return null;
            t = t.children[index];
        }

        return t;
    }

    public String search(String key) {
        TrieNode t = find(key);
        return t == null ? null : t.value;
    }

    public boolean startsWith(String key) {
        return find(key) != null;
    }

    private boolean delete(int level, String key, TrieNode node) {
        if (node == null) return false;
        if (level == key.length()) {
            node.value = null;
            return !hasChild(node);
        }

        int index = key.charAt(level) - 'a';

        boolean canDeleteChild = delete(level + 1, key, node.children[index]);

        if (canDeleteChild) {
            node.children[index] = null;
            return !hasChild(node);
        }

        return false;
    }

    private boolean deleteable(TrieNode t) {
        return t.value == null && !hasChild(t);
    }

    private boolean hasChild(TrieNode t) {
        for (TrieNode c: t.children) {
            if (c != null) return true;
        }
        return false;
    }

    public void delete(String key) {
        delete(0, key, root);
    }

    public Trie() {
        this.root = new TrieNode();
    }

    private static class TrieNode {
        private String value;
        private TrieNode[] children;
    
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));

        System.out.println("-----------------------------------");

        trie.insert("apple", "a fruit");

        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));

        System.out.println("-----------------------------------");
        
        trie.insert("app", "application");

        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));

        System.out.println("-----------------------------------");

        trie.delete("apple");

        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("appl"));
    }
}
