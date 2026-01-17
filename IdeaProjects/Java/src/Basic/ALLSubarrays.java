package Basic;

import java.util.ArrayList;
import java.util.List;

public class ALLSubarrays {
    public static int subArrays(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int totalSum = 0, ans=0,  maxSum=0;
        int n = arr.length;
        // Generate all subarrays using two loops
        for (int start = 0; start < n; start++) {
            List<Integer> tempList = new ArrayList<>();
            int sum = 0; // Stores sum of current subarray
            for (int end = start; end < n; end++) {
                sum += arr[end]; // Add current element
               // System.out.println("Sum of subarray [" + start + "..." + end + "] = " + sum);
                totalSum += sum; // Add subarray sum to total
                //all sub array print
                tempList.add(arr[end]); // Add current element to the subarray
                result.add(new ArrayList<>(tempList)); // Store a copy of the subarray

                maxSum = Math.max(maxSum, sum);
                if(sum==0){
                    ans = Math.max(ans, end - start + 1); //maxi sub array with sum zero
                }
            }
        }
        System.out.println("mazimum for zero sum " + ans);
        System.out.println("mazimum sum " + maxSum);
        for (List<Integer> subarray : result) {
            //System.out.println(subarray);
        }
        return totalSum;
    }

    public static void main(String[] args) {
        int[] arr = {15, -2, 2, -8, 1, 7, 10, 23};
        int totalSum = subArrays(arr);
        //System.out.println("Sum of all subarrays: " + totalSum);
    }
    public int findSubarray(int[] arr) {
        int count = 0;
        int prefixSum = 0;
        HashMap<Integer, Integer> sumFrequency = new HashMap<>();

        // Initialize with prefix sum 0 appearing once (to count subarrays starting from index 0)
        sumFrequency.put(0, 1);

        for (int num : arr) {
            prefixSum += num;

            // If this prefix sum has been seen before, it means subarrays with sum 0 exist
            count += sumFrequency.getOrDefault(prefixSum, 0);

            // Store the prefix sum in the hashmap
            sumFrequency.put(prefixSum, sumFrequency.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
}

