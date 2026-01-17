package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.
 */
public class RainWater {

    public static void main(String[] args) {
        System.out.println(trapWater(Arrays.asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)));
    }

    static int trapWater(List<Integer> height) {
        int n = height.size();
        if (n == 0) return 0;
        List<Integer> leftMax = new ArrayList<>(n);
        List<Integer> rightMax = new ArrayList<>(n);
        // Initialize leftMax and rightMax lists
        for (int i = 0; i < n; i++) {
            leftMax.add(0);
            rightMax.add(0);
        }

        // Fill left max
        leftMax.set(0, height.get(0));
        for (int i = 1; i < n; i++) {
            leftMax.set(i, Math.max(height.get(i), leftMax.get(i - 1)));
        }

        // Fill right max
        rightMax.set(n - 1, height.get(n - 1));
        for (int i = n - 2; i >= 0; i--) {
            rightMax.set(i, Math.max(height.get(i), rightMax.get(i + 1)));
        }

        // Calculate trapped water
        int trappedWater = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax.get(i), rightMax.get(i));
            trappedWater += waterLevel - height.get(i);
        }

        return trappedWater;
    }

}
