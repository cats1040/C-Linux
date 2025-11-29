import java.util.Arrays;
import java.util.LinkedList;

public class MyHashSet<K> {
    private int size = 0;
    private final float loadFactor;
    private LinkedList<K>[] bucket;

    public MyHashSet(int capacity, float loadFactor) {
        this.loadFactor = loadFactor<=0?0.25f:loadFactor;
        bucket = new LinkedList[capacity<=0?8:capacity];
        Arrays.fill(bucket, new LinkedList<>()); // (1)
    }

    public void add(K k) {
        int bucketId = k.hashCode()%size;

        /**
         * do not have to check for null, because
         * of (1)
         * if (bucket[bucketId] == null) { 
         * bucket[bycketId] = new LinkedList<>(); 
         * }
         */

        bucket[bucketId].add(k);
        
        if (bucket[bucketId].size() > size*loadFactor) {
            // re shuffle
            size *= 2;
            LinkedList<K>[] tempBucket = new LinkedList[size];
            Arrays.fill(tempBucket, new LinkedList<>());

            for (int i = 0; i < bucket.length; i++) {
                for (K element: bucket[i]) {
                    int newBucketId = element.hashCode()%size;
                    tempBucket[newBucketId].add(element);
                }
            }

            bucket = tempBucket;
        }
    }

    public boolean hasKey(K k) {
        int bucketId = k.hashCode()%size;
        return bucket[bucketId].contains(k);
    }
}
