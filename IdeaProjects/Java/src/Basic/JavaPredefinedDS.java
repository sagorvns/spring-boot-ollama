package Basic;

import java.util.*;
import java.util.Queue;
import java.util.Stack;

public class JavaPredefinedDS {
    public static void main(String[] args) {
        // STACK (LIFO)
        java.util.Stack<Integer> stack = new Stack<>();
        stack.push(10); stack.push(20); stack.push(30);
        System.out.println("Stack: " + stack);
        System.out.println("Stack Pop: " + stack.pop());
        System.out.println("Stack after Pop: " + stack);
        System.out.println();

        // QUEUE (FIFO using LinkedList)
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(10); queue.offer(20); queue.offer(30);
        System.out.println("Queue: " + queue);
        System.out.println("Queue Poll: " + queue.poll());
        System.out.println("Queue after Poll: " + queue);
        System.out.println();

        // PRIORITY QUEUE (Min-Heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(40); pq.offer(10); pq.offer(30); pq.offer(20);
        System.out.println("PriorityQueue: " + pq);
        System.out.println("PriorityQueue Poll: " + pq.poll());
        System.out.println("PriorityQueue after Poll: " + pq);
        System.out.println();

        // ARRAYLIST (Dynamic Array)
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
        arrayList.add(50);
        System.out.println("ArrayList: " + arrayList);
        arrayList.remove(Integer.valueOf(30));
        System.out.println("ArrayList after removing 30: " + arrayList);
        System.out.println();

        // LINKEDLIST (Doubly Linked List)
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList("A", "B", "C"));
        linkedList.addFirst("X"); linkedList.addLast("Y");
        System.out.println("LinkedList: " + linkedList);
        System.out.println("LinkedList Remove First: " + linkedList.removeFirst());
        System.out.println("LinkedList after Remove First: " + linkedList);
        System.out.println();

        // HASHSET (Unique Unordered Set)
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(10, 20, 30, 40, 10, 20));
        System.out.println("HashSet (removes duplicates): " + hashSet);
        System.out.println("HashSet contains 30? " + hashSet.contains(30));
        System.out.println();

        // TREESET (Sorted Unique Set)
        TreeSet<Integer> treeSet = new TreeSet<>(Arrays.asList(50, 10, 40, 30, 20));
        System.out.println("TreeSet (Sorted Set): " + treeSet);
        System.out.println("TreeSet First: " + treeSet.first());
        System.out.println("TreeSet Last: " + treeSet.last());
        System.out.println();

        // HASHMAP (Key-Value Pair)
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alice", 25);
        hashMap.put("Bob", 30);
        hashMap.put("Charlie", 35);
        System.out.println("HashMap: " + hashMap);
        System.out.println("HashMap Get Bob: " + hashMap.get("Bob"));
        hashMap.remove("Alice");
        System.out.println("HashMap after removing Alice: " + hashMap);
        System.out.println();

        // TREEMAP (Sorted Key-Value Pair)
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "Three"); treeMap.put(1, "One"); treeMap.put(2, "Two");
        System.out.println("TreeMap (Sorted by Key): " + treeMap);
        System.out.println("TreeMap First Entry: " + treeMap.firstEntry());
        System.out.println("TreeMap Last Entry: " + treeMap.lastEntry());
    }
}
