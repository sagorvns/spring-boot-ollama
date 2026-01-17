package Practice;
//Lecture 103: Minimum Cost Climbing Stairs || DP Series
public class FrogJump {

    public int minimumEnergy(int[] heights) {
        int n = heights.length;
        return solve(heights, n - 1); // Start from the last stone
    }
    public int solve(int[] height, int n) {
        if (n == 0) return 0; // Base case: no cost at first stone
        // Jump from previous stone
        int jumpOne = solve(height, n - 1) + Math.abs(height[n] - height[n - 1]);
        // Jump from two steps back
        int jumpTwo = Integer.MAX_VALUE;
        if (n > 1) {
            jumpTwo = solve(height, n - 2) + Math.abs(height[n] - height[n - 2]);
        }
        return Math.min(jumpOne, jumpTwo);
    }

    public static void main(String[] args) {
        FrogJump fj = new FrogJump();
        int[] heights = {10, 20, 30, 10};
        System.out.println("Minimum cost: " + fj.minimumEnergy(heights));  // ✅ Output: 20
    }
}
