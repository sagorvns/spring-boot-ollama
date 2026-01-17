package Practice;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEquals {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int target = 2;
        System.out.println(subarraySum(nums, target));
    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1);
        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            if (prefixMap.containsKey(sum - k)) {
                count += prefixMap.get(sum - k);
            }
            prefixMap.put(sum, prefixMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
