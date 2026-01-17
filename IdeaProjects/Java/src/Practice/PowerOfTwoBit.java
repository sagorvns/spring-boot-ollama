package Practice;

public class PowerOfTwoBit {

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        int n = 16;
        System.out.println(n + " is a power of two: " + isPowerOfTwo(n)); // Output: true

        n = 18;
        System.out.println(n + " is a power of two: " + isPowerOfTwo(n)); // Output: false
    }
}

