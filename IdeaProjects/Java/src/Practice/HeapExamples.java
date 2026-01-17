package Practice;

import java.util.*;

public class HeapExamples {

    // 1. Min Heap (Default in Java)
    public static void minHeapExample(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) minHeap.offer(num);

        System.out.println("Min Heap (Ascending Order):");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println("\n");
    }

    // 2. Max Heap using Comparator
    public static void maxHeapExample(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) maxHeap.offer(num);

        System.out.println("Max Heap (Descending Order):");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println("\n");
    }

    // 3. Heap Sort using PriorityQueue
    public static int[] heapSort(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) heap.offer(num);

        int i = 0;
        while (!heap.isEmpty()) {
            nums[i++] = heap.poll();
        }
        return nums;
    }

    // 4. Kth Largest Element
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.peek(); // kth largest
    }

    // 5. Top K Frequent Elements
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                Map.Entry.comparingByValue()
        );

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) heap.poll();
        }

        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty()) result.add(heap.poll().getKey());
        Collections.reverse(result); // optional for descending order
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 12, 10, 7};

        // 1. Min Heap
        minHeapExample(nums);

        // 2. Max Heap
        maxHeapExample(nums);

        // 3. Heap Sort
        System.out.println("Heap Sort:");
        int[] sorted = heapSort(nums.clone());
        System.out.println(Arrays.toString(sorted) + "\n");

        // 4. Kth Largest
        int k = 3;
        System.out.println(k + "rd Largest Element: " + findKthLargest(nums, k) + "\n");

        // 5. Top K Frequent Elements
        int[] nums2 = {1,1,1,2,2,3};
        int k2 = 2;
        System.out.println("Top " + k2 + " Frequent Elements: " + topKFrequent(nums2, k2));
    }
}

