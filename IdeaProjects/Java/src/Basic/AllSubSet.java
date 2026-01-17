package Basic;
import java.util.*;

public class AllSubSet {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with an empty subset

        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println("All subsets: " + subsets);
    }




    public static List<List<Integer>> Rsubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // Add the current subset
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);                // Include nums[i]
            backtrack(nums, i + 1, current, result); // Recurse
            current.remove(current.size() - 1);  // Exclude nums[i] (backtrack)
        }
    }

}
