package Practice;

import java.util.Arrays;

public class HouseRobber {

    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return robRec(nums, n - 1, dp);
    }

    // Recursive with memoization
    public int robRec(int[] nums, int n, int[] dp) {
        if (n == 0) return nums[0];
        if (n < 0) return 0;

        if (dp[n] != -1) return dp[n]; // reuse if already computed

        int rob = nums[n] + robRec(nums, n - 2, dp);
        int skip = robRec(nums, n - 1, dp);

        return dp[n] = Math.max(rob, skip);
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println("Max loot: " + hr.rob(nums)); // ✅ Output: 12
    }
}
