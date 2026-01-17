package Practice;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch); // Push opening brackets
            } else {
                if (stack.isEmpty()) return false; // No matching open
                char top = stack.pop(); // Pop the last open
                if (!isMatching(top, ch)) return false; // Check if it matches
            }
        }

        return stack.isEmpty(); // If empty, all brackets matched correctly
    }

    private boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();

        String input1 = "()[]{}";
        String input2 = "(]";
        String input3 = "([{}])";

        System.out.println(input1 + " → " + vp.isValid(input1)); // true
        System.out.println(input2 + " → " + vp.isValid(input2)); // false
        System.out.println(input3 + " → " + vp.isValid(input3)); // true
    }
}
