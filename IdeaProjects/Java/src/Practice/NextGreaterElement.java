package Practice;

import java.util.Stack;

public class NextGreaterElement {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>(); // store indexes

        for (int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;

            while (!stack.isEmpty() && nums[stack.peek()] <= nums[idx]) {
                stack.pop(); // pop smaller or equal elements
            }

            res[idx] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(idx); // push current index
        }

        return res;
    }

    public static void main(String[] args) {
        NextGreaterElement solver = new NextGreaterElement();

        int[] input = {1, 2, 1};
        int[] output = solver.nextGreaterElements(input);

        System.out.print("Next greater elements: ");
        for (int val : output) {
            System.out.print(val + " ");
        }
    }
}

