package Basic;

public class Stack {
    private int[] arr;
    private int capacity;
    private int top;
    public Stack(int size) {
        this.arr = new int[size];
        this.capacity = size;
        this.top = -1;
    }
    public static void main(String[] args) {
        Stack s = new Stack(5);
        s.print();
        s.push(1);s.push(3);s.push(45);s.push(2);s.print();
        s.peek();s.pop();s.pop();s.print();
        s.peek();s.push(30);s.print();s.peek();
    }
    public void push(int value) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow! Cannot push " + value);
            return;
        }
        arr[++top] = value;
        System.out.println(value + " added to stack.");
    }
    public void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow! No elements to pop.");
            return;
        }
        System.out.println(arr[top] + " removed from stack.");
        top--;
    }
    public void peek() {
        if (top == -1) {
            System.out.println("Stack is empty! No top element.");
            return;
        }
        System.out.println("Top element: " + arr[top]);
    }
    public void print() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.print("Stack elements (top to bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}