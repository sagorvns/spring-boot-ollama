package Basic;

public class Queue {
    private int[] arr;
    private int front, rear, size, capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;   // Front points to the first element
        rear = -1;   // Rear points to the last inserted element
        size = 0;    // Number of elements in the queue
    }

    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.print();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        q.print();
        q.enqueue(60); // Should print "Queue Overflow"
        q.dequeue();
        q.dequeue();
        q.print();
        q.enqueue(60);
        q.print();
        q.front();
        q.rear();
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue Overflow! Cannot enqueue " + value);
            return;
        }
        rear = (rear + 1) % capacity; // Circular increment for liner rear++
        arr[rear] = value;
        size++;
        System.out.println(value + " enqueued.");
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! No elements to dequeue.");
            return;
        }
        System.out.println(arr[front] + " dequeued.");
        front = (front + 1) % capacity; // Circular increment
        size--;
    }

    public void linearDequeue() {
        if (front > rear) {
            System.out.println("Queue Underflow!");
            return;
        }
        System.out.println(arr[front] + " dequeued.");
        front++;
    }

    public void front() {
        if (isEmpty()) {
            System.out.println("Queue is empty! No front element.");
            return;
        }
        System.out.println("Front element: " + arr[front]);
    }

    public void rear() {
        if (isEmpty()) {
            System.out.println("Queue is empty! No rear element.");
            return;
        }
        System.out.println("Rear element: " + arr[rear]);
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        int index = front;
        for (int i = 0; i < size; i++) {
            System.out.print(arr[index] + " ");
            index = (index + 1) % capacity; // ✅ Fix: Circular increment
        }
        System.out.println();
    }

    // ✅ Display
    public void linearDisplay() {
        if (front > rear) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue: ");
        for (int i = front; i <= rear; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private boolean isFull() {
        return size == capacity;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}