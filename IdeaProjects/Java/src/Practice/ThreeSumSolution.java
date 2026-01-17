package Practice;

import java.util.*;

public class ThreeSumSolution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Traverse the array
        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];

                if (total == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;

                } else if (total < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        ThreeSumSolution solution = new ThreeSumSolution();
        int[] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> result = solution.threeSum(nums);
        System.out.println("Triplets that sum to 0: " + result);
    }
}

