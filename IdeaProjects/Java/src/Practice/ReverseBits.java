package Practice;

public class ReverseBits {

    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;          // Shift result left by 1 to make room for the next bit
            result |= (n & 1);     // Add the least significant bit of n to result
            n >>= 1;               // Shift n right by 1 to get the next bit
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 43261596;  // Example input
        System.out.println("Original number: " + n);
        System.out.println("Reversed bits: " + reverseBits(n)); // Output: 964176192
    }
}

