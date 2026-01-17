package Practice;

public class HammingWeightBit {

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1); // Clear the lowest set bit
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 0b00000000000000000000000000001011; // 11 in decimal
        System.out.println("Number of 1 bits: " + hammingWeight(n)); // Output: 3
    }
}
