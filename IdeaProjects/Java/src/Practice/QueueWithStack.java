package Practice;

import java.util.Stack;

public class QueueWithStack {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public QueueWithStack() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        shiftStacks();
        return outStack.pop();
    }

    public int peek() {
        shiftStacks();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    // Transfer only when outStack is empty
    private void shiftStacks() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueWithStack q = new QueueWithStack();
        q.push(1);
        q.push(2);
        System.out.println("Peek: " + q.peek());  // Output: 1
        System.out.println("Pop: " + q.pop());    // Output: 1
        System.out.println("Is empty: " + q.empty()); // false
    }
}
