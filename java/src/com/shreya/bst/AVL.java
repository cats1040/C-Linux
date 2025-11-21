/**
 * Adelson-Velsky and Landis’s Tree - AVL Tree
 * 
 * Balanced tree - When any 2 sibling subtrees
 * do not differ in height by more than one level.
 * 
 */
public class AVL<Key extends Comparable<Key>, Value> {
    private class Node {
        int size;
        int height;
        Key key;
        Value value;
        Node left, right;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = right = null;
            size = 1;
            height = 1;
        }
    }

    private Node root;

    public int rotationCount;

    public AVL() {
        root = null;
        rotationCount = 0;
    }

    //------------------------------------
    // ------------- PUBLIC -------------- 
    //------------------------------------

    public void put(Key key, Value value) {
        root = put(root, key, value);
        return;
    }

    // get the value corresponding to the key
    public Value get(Key key) {
        Node node = root;
        
        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp == 0) {
                // bingo !
                return node.value;
            }

            if (cmp < 0) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }

        return null;
    }

    // get the minimum key
    public Key min() {
        Node node = root;

        if (node == null) return null;

        while (node.left != null) {
            node = node.left;
        }

        return node.key;
    }

    // get the maximum key
    public Key max() {
        Node node = root;

        if (node == null) return null;

        while (node.right != null) {
            node = node.right;
        }

        return node.key;   
    }

    // floor - largest key smaller than the given key
    public Key floor(Key key) {
        return floor(root, key);
    }

    public int sizeof(Key key) {
        return sizeof(root, key);
    }

    // ceil - smallest key larger than the given key
    public Key ceil(Key key) {
        return ceil(root, key);
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    public void delete(Key key) {
        root = delete(root, key);
        return;
    }

    public int getRotations() {
        return rotationCount;
    }

    //------------------------------------
    // ------------ PRIVATE -------------- 
    //------------------------------------
       
    // helper function to put node in a LLRBT
    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            Node newNode = new Node(key, value);
            return newNode;
        }

        // compare
        int cmp = key.compareTo(node.key);
        
        if (cmp == 0) {
            // update value
            node.value = value;
        }
        else if (cmp < 0) {
            // left
            node.left = put(node.left, key, value);
        }
        else {
            // right
            node.right = put(node.right, key, value);
        }

        // SELF BALANCING BST - AVL
        
        int diff = diff(node);

        // BALANCE
        if (diff > 1) {
            if (diff(node.left) == -1) {
                // case 3: +2 and -1
                node.left = rotateLeft(node.left);
            }
            
            // case 1: +2 and +1
            node = rotateRight(node);
        }
        else if (diff < -1) {
            if (diff(node.right) == 1) {
                // case 4: -2 and +1
                node.right = rotateRight(node.right);
            }
            
            // case 2: -2 and -1
            node = rotateLeft(node);
        }
        
        node.size = 1 + getSize(node.left) + getSize(node.right);
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        
        return node;
    }
    
    private int diff(Node node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(Node node) {
        if (node == null) return 0;
        
        int l = getHeight(node.left);
        int r = getHeight(node.right);

        return 1 + Math.max(l, r);
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.size = node.size;
        node.size = 1 + getSize(node.left) + getSize(node.right);

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
      
        this.rotationCount++;

        return x;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.size = node.size;
        node.size = 1 + getSize(node.left) + getSize(node.right);

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    
        this.rotationCount++;

        return x;
    }

    private Key floor(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node.key;
        }

        if (cmp < 0) {
            return floor(node.left, key);
        }

        Key right = floor(node.right, key);
        if (right == null) right = node.key;
        
        return right;
    }

    private Key ceil(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node.key;
        }

        if (cmp > 0) {
            return ceil(node.right, key);
        }

        Key left = ceil(node.left, key);
        if (left == null) left = node.key;

        return left;
    }

    private int rank(Node node, Key key) {
        if (node == null) {
            return 0;
        }

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return getSize(node.left) + 1;
        }
        
        if (cmp < 0) {
            return rank(node.left, key);    
        }
        
        return 1 + getSize(node.left) + rank(node.right, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            // YAYAY!!

            // delete
            // find the maximum from node.left
            // or the minimum from node.right
            // your choice :)

            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            // Imma go with the second one
            Node mini = node.right;
            while (mini != null && mini.left != null) {
                mini = mini.left;
            }

            node.key = mini.key;
            node.value = mini.value;
            
            // now recursively dlete the mini.key from node.right
            node.right = delete(node.right, mini.key);
        }
        else if (cmp < 0) {
            node.left = delete(node.left, key);
        }
        else {
            node.right = delete(node.right, key);
        }
        
        // SELF BALANCING BST - AVL

        int diff = diff(node);

        // BALANCE
        if (diff > 1) {
            if (diff(node.left) == -1) {
                // case 3: +2 and -1
                node.left = rotateLeft(node.left);
            }
            
            // case 1: +2 and +1
            node = rotateRight(node);
        }
        else if (diff < -1) {
            if (diff(node.left) == 1) {
                // case 4: -2 and +1
                node.right = rotateRight(node.right);
            }
            
            // case 2: -2 and -1
            node = rotateLeft(node);
        }
        
        node.size = 1 + getSize(node.left) + getSize(node.right);
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        
        return node;
    }

    private int sizeof(Node node, Key key) {
        if (key == null) return 0;

        int cmp = key.compareTo(node.key);

        if (cmp == 0) return node.size;
        if (cmp < 0) {
            return sizeof(node.left, key);
        }
        return sizeof(node.right, key);
    }

    private int getSize(Node node) {
        if (node == null) return 0;
        return node.size;
    }
}