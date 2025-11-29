import java.util.Arrays;

public class OpenAddressing {
    private static final Integer PRIME = 7;
    private final int size;
    private Node[] data;
    private final AddressingType addressingType;

    private enum AddressingType {
        LinearProbing, QuadraticProbing, DoubleAddressing;
    }
    
    private static class Node {
        private Integer key;
        private State state;

        public Node(Integer key, State state) {
            this.key = key;
            this.state = state;
        }

        @Override
        public String toString() {
            return key + "(" + state + ")";
        }
    }
    
    private enum State {
        Occupied, Free, Deleted;
    }
    
    public OpenAddressing(int size, String addressingType) {
        this.size = size;
        this.addressingType = AddressingType.valueOf(addressingType);
        data = new Node[size];
        Arrays.fill(data, new Node(null, State.Free));
    }

    public int generateAddress(Integer key, int h) {
        // if (h == -1) return key.hashCode()%size;
        // return (1 + h) % size; // linear addressing
        /**
         * for quadratic addressing
         * return (qudratic expression)%size;
         */

        if (h == -1) return key % size;
        if(this.addressingType == AddressingType.LinearProbing){
            return (1 + h) % size;
        }
        return ((1 + key) % PRIME + h) % size;
    }

    public void insert(Integer key) {
        int h = generateAddress(key, -1);
        int originalH = h;

        while (true) {
            if (data[h].state == State.Free) {
                data[h] = new Node(key, State.Occupied);
                return;
            }

            h = generateAddress(key, h);

            if (h == originalH) {
                throw new RuntimeException("Overflow");
            }
        }
    }

    public boolean hasKey(int key) {
        int h = generateAddress(key, -1);
        int originalH = h;

        while (true) {
            if (data[h].state == State.Free) {
                return false;
            }

            if (data[h].state == State.Occupied && data[h].key == key) {
                return true; 
            }

            h = generateAddress(key, h);

            if (h == originalH) {
                throw new RuntimeException("Overflow");
            }
        }
    }

    public void delete(int key) {
        int h = generateAddress(key, -1);
        int originalH = h;

        while (true) {
            if (data[h].state == State.Free) {
                return;
            }

            if (data[h].state == State.Occupied && data[h].key == key) {
                data[h].state = State.Deleted;
                return;
            }

            h = generateAddress(key, h);

            if (h == originalH) {
                throw new RuntimeException("Overflow");
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public static void main(String[] args) {
        OpenAddressing oa = new OpenAddressing(10, "DoubleAddressing");
        System.out.println(oa);

        oa.insert(15);
        oa.insert(25);
        oa.insert(35);
        oa.insert(8);
        oa.insert(45);
        oa.insert(65);

        System.out.println(oa);
        System.out.println(oa.hasKey(15));
    }
}
