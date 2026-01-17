package Practice;

public class SumOfTwoIntegersBit {

    public static int getSum(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;           // Add a and b without carry
            int carry = (a & b) << 1;  // Calculate the carry
            a = sum;                   // Move to the next step with the sum
            b = carry;                 // Carry becomes the new b
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println("Sum of " + a + " and " + b + " is: " + getSum(a, b)); // Output: 3
    }
}

