package Practice;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeMap {
    // key -> (timestamp -> value), timestamps sorted
    private final Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    // O(log n) due to TreeMap insertion (amortized constant time for growing keys)
    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap<>())
                .put(timestamp, value);
    }

    // O(log n) lookup for floorKey
    public String get(String key, int timestamp) {
        TreeMap<Integer, String> tree = map.get(key);
        if (tree == null) return "";
        Integer t = tree.floorKey(timestamp);
        return t == null ? "" : tree.get(t);
    }
}

