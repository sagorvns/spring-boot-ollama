package Practice;

public class EditDistance {

    // ----------------- 1. Pure Recursion (Brute Force) -----------------
    // Time: Exponential (very slow for large strings)
    public int minDistanceRecursive(String word1, String word2) {
        return helperRecursive(word1, word2, word1.length(), word2.length());
    }

    private int helperRecursive(String word1, String word2, int m, int n) {
        // Base cases: one of the strings is empty
        if (m == 0) return n; // need to insert all characters of word2
        if (n == 0) return m; // need to delete all characters of word1

        // If last chars match → no operation needed, move both pointers
        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            return helperRecursive(word1, word2, m - 1, n - 1);
        }

        // Otherwise, try:
        // 1. Replace last char (m-1, n-1)
        // 2. Insert into word1 (m, n-1)
        // 3. Delete from word1 (m-1, n)
        return 1 + Math.min(
                helperRecursive(word1, word2, m - 1, n - 1), // replace
                Math.min(helperRecursive(word1, word2, m, n - 1), // insert
                        helperRecursive(word1, word2, m - 1, n)) // delete
        );
    }

    // ----------------- 2. Top-Down DP (Memoization) -----------------
    // Time: O(m * n), Space: O(m * n) for memo
    public int minDistanceTopDown(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        Integer[][] memo = new Integer[m + 1][n + 1];
        return helperMemo(word1, word2, m, n, memo);
    }

    private int helperMemo(String word1, String word2, int m, int n, Integer[][] memo) {
        // Base cases
        if (m == 0) return n;
        if (n == 0) return m;

        if (memo[m][n] != null) return memo[m][n]; // already computed

        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            memo[m][n] = helperMemo(word1, word2, m - 1, n - 1, memo);
        } else {
            memo[m][n] = 1 + Math.min(
                    helperMemo(word1, word2, m - 1, n - 1, memo), // replace
                    Math.min(helperMemo(word1, word2, m, n - 1, memo), // insert
                            helperMemo(word1, word2, m - 1, n, memo)) // delete
            );
        }
        return memo[m][n];
    }

    // ----------------- 3. Bottom-Up DP (Tabulation) -----------------
    // Time: O(m * n), Space: O(m * n)
    public int minDistanceBottomUp(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base cases: converting empty string
        for (int i = 0; i <= m; i++) dp[i][0] = i; // delete all
        for (int j = 0; j <= n; j++) dp[0][j] = j; // insert all

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no change
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1], // replace
                            Math.min(dp[i][j - 1], // insert
                                    dp[i - 1][j]) // delete
                    );
                }
            }
        }

        return dp[m][n];
    }

    // ----------------- Main Method for Testing -----------------
    public static void main(String[] args) {
        EditDistance ed = new EditDistance();

        String word1 = "horse", word2 = "ros";

        System.out.println("Recursive (Brute Force): " +
                ed.minDistanceRecursive(word1, word2)); // Output: 3

        System.out.println("Top-Down DP (Memoization): " +
                ed.minDistanceTopDown(word1, word2)); // Output: 3

        System.out.println("Bottom-Up DP (Tabulation): " +
                ed.minDistanceBottomUp(word1, word2)); // Output: 3
    }
}
