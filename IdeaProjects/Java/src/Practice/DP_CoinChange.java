package Practice;

import java.util.Arrays;

public class CoinChange {
//https://www.youtube.com/watch?v=A3FHNCAkhxE
   /* public int coinChange(int[] coins, int amount) {
        int max = amount + 1;  // A value larger than any possible answer(or we can take Integer.MAX_VALUE)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);//bottom to top, tabulation
        dp[0] = 0; // Base case

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);//d[i] is min no. of cin to make target i
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }*/

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Base case

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) { // avoid overflow
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum coins: " + cc.coinChange(coins, amount));  // Output: 3
    }
}
/*
    private int helper(int coins[], int sum) {
        if (sum == 0) return 0;
        if (sum < 0) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int ans = helper(coins, sum - coin);
            if (ans != Integer.MAX_VALUE) {
                min = Math.min(min, ans + 1);
            }
        }
        return min;
    }

    //memorization top-down
     public int minCoins(int coins[], int sum) {
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, -1);
        int ans = helper(coins, sum, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int helper(int coins[], int sum, int[] dp) {
        if (sum == 0) return 0;
        if (sum < 0) return Integer.MAX_VALUE;

        // If already computed, return cached result
        if (dp[sum] != -1) return dp[sum];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int ans = helper(coins, sum - coin, dp);
            if (ans != Integer.MAX_VALUE) {
                min = Math.min(min, ans + 1);
            }
        }
        // Store result in dp and return
        dp[sum] = min;
        return min;
    }
 */