package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductOfArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(productWithoutSelf(nums));
    }

    public static List productWithoutSelf(int[] input) {
        int n = input.length;
        List<Integer> result = new ArrayList<>(n);
        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];
        leftProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * input[i - 1];
        }
        rightProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * input[i + 1];
        }
        for (int i = 0; i < n; i++) {
            result.add(leftProducts[i] * rightProducts[i]);
        }
        return result;
    }
}
