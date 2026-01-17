package Practice;

import java.util.*;

public class SingleNumberBit {

    public static int singleNumber(int[] nums) {
        var result = 0;
        for (var num : nums) {
            result ^= num;
        }
        return result;
    }
/*    result = 0           // Start
    result ^= 4   → 0 ^ 4 = 4
    result ^= 1   → 4 ^ 1 = 5
    result ^= 2   → 5 ^ 2 = 7
    result ^= 1   → 7 ^ 1 = 6
    result ^= 2   → 6 ^ 2 = 4*/
    public static void main(String[] args) {
        var nums = new int[]{4, 1, 2, 1, 2};
        System.out.println("Input Array: " + Arrays.toString(nums));
        var single = singleNumber(nums);
        System.out.println("Single Number is: " + single); // Output: 4
    }
}

