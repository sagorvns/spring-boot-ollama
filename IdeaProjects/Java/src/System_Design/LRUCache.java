package System_Design;

import java.util.*;

class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;
    private final LinkedHashMap<K, V> order;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.order = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public V get(K key) {
        if (!cache.containsKey(key)) return null;
        moveToRecent(key);
        return cache.get(key);
    }

    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            removeOldest();
        }
        cache.put(key, value);
        moveToRecent(key);
    }

    private void moveToRecent(K key) {
        order.remove(key);
        order.put(key, cache.get(key));
    }

    private void removeOldest() {
        K oldestKey = order.keySet().iterator().next();
        order.remove(oldestKey);
        cache.remove(oldestKey);
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache.get(1)); // A
        cache.put(4, "D"); // Removes least recently used (2)
        System.out.println(cache.get(2)); // null
    }
}
