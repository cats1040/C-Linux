public class Test {
    public static void main(String[] args) {
        LLRBT<Integer, String> llrbt = new LLRBT<>();
        llrbt.put(1, "one");
        llrbt.put(2, "two");
        llrbt.put(3, "three");
        llrbt.put(4, "four");
        llrbt.put(5, "five");
        llrbt.put(6, "five");
        llrbt.put(7, "five");
        llrbt.put(8, "five");

        System.out.println("Search for key 10: " + llrbt.get(10)); // Output: Ten
        System.out.println("Search for key 15: " + llrbt.get(15)); // Output: null
        
        System.out.println("Rotations: " + llrbt.getRotations());

        // System.out.println("Height of BST: " + llrbt.height()); // Output: 2

        BST<Integer, Integer> bst = new BST<>();
        // bst.put(10, 10);
        // bst.put(20, 20);
        // bst.put(15, 15);

        // bst.delete(10);

        // System.out.println(bst.get(20));

        int[] keys = {5, 1, 9, 3, 7, 12, 2, 4, 10, 8, 6};
        for (int k : keys) {
            bst.put(k, k);
        }

        for (int k: keys) {
            System.out.println("Size of " + k + " " + bst.sizeof(k));
        }

        System.out.println();

        // Print ranks for all existing keys
        System.out.println("Ranks of existing keys:");
        System.out.println("rank(1)  = " + bst.rank(1));   // 0
        System.out.println("rank(2)  = " + bst.rank(2));   // 1
        System.out.println("rank(3)  = " + bst.rank(3));   // 2
        System.out.println("rank(4)  = " + bst.rank(4));   // 3
        System.out.println("rank(5)  = " + bst.rank(5));   // 4
        System.out.println("rank(6)  = " + bst.rank(6));   // 5
        System.out.println("rank(7)  = " + bst.rank(7));   // 6
        System.out.println("rank(8)  = " + bst.rank(8));   // 7
        System.out.println("rank(9)  = " + bst.rank(9));   // 8
        System.out.println("rank(10) = " + bst.rank(10));  // 9
        System.out.println("rank(12) = " + bst.rank(12));  // 10

        // Keys NOT in the BST
        System.out.println("\nRanks of missing keys:");
        System.out.println("rank(0)  = " + bst.rank(0));   // 0
        System.out.println("rank(11) = " + bst.rank(11));  // 10
        System.out.println("rank(13) = " + bst.rank(13));  // 11
        System.out.println("rank(15) = " + bst.rank(15));  // 11 (max rank)

        // ------------- AVL ------------

    }
}
