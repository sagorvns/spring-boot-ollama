package Practice;

import java.util.*;

public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);  // Integer division truncates towards zero in Java
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        ReversePolishNotation rpn = new ReversePolishNotation();

        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println("Output: " + rpn.evalRPN(tokens1)); // 9

        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println("Output: " + rpn.evalRPN(tokens2)); // 6

        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("Output: " + rpn.evalRPN(tokens3)); // 22
    }
}

