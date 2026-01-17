package Practice;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public boolean isHappy(int n) {
        // We use a set to track already visited numbers to detect cycles
        Set<Integer> seen = new HashSet<>();

        while (n != 1) {
            if (seen.contains(n)) {
                return false;  // Cycle detected
            }
            seen.add(n);
            n = sumOfSquaresOfDigits(n);  // Replace n with the sum of the squares of its digits
        }

        return true;  // Happy number found
    }

    // Helper method to calculate sum of squares of digits
    private int sumOfSquaresOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        HappyNumber solution = new HappyNumber();

        System.out.println("19 is happy: " + solution.isHappy(19)); // true
        System.out.println("2 is happy: " + solution.isHappy(2));   // false
    }
}
