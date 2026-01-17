package Basic;

public class BasicLinkList {
    Node head;
    private int size;

    BasicLinkList() {
        size = 0;
    }

    class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    // Add node at the beginning
    public void addFirst(String data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Add node at the end
    public void addLast(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    // Remove first node
    public void removeFirst() {
        if (head == null) return;
        head = head.next;
        size--;
    }

    // Remove last node
    public void removeLast() {
        if (head == null) return; // List empty
        if (head.next == null) {  // Only one element
            head = null;
            size--;
            return;
        }
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        size--;
    }

    // Search for an element
    public void search(String data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(data)) {
                System.out.println("Found: " + data);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Not Found: " + data);
    }

    // Reverse the linked list (Iterative)
    public void reverse() {
        if (head == null || head.next == null) return;
        Node prev = null, curr = head, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    // Reverse the linked list (Recursive)
    public void reverseRecursive() {
        head = reverseRecursiveHelper(head);
    }

    private Node reverseRecursiveHelper(Node node) {
        if (node == null || node.next == null) return node;
        Node newHead = reverseRecursiveHelper(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    // Get the size of the linked list
    public int getSize() {
        return size;
    }

    // Check if the linked list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Find middle of the linked list (Tortoise & Hare Approach)
    public void getMiddle() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println("Middle element: " + slow.data);
    }

    // Detect loop (Floyd's Cycle Detection Algorithm)
    public boolean detectLoop() {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                System.out.println("Loop detected in the list!");
                return true;
            }
        }
        System.out.println("No loop detected.");
        return false;
    }

    // Print the linked list
    public void print() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Main method for testing
    public static void main(String[] args) {
        BasicLinkList ll = new BasicLinkList();
        ll.addFirst("1");
        ll.addFirst("2");
        ll.addLast("6");
        ll.addLast("3");
        ll.addFirst("4");
        ll.addLast("5");

        System.out.println("Original List:");
        ll.print();

        System.out.println("Reversing List (Iterative):");
        ll.reverse();
        ll.print();

        System.out.println("Reversing List (Recursive):");
        ll.reverseRecursive();
        ll.print();

        System.out.println("Searching for element '6':");
        ll.search("6");

        System.out.println("Searching for element '10':");
        ll.search("10");

        System.out.println("Removing First Element:");
        ll.removeFirst();
        ll.print();

        System.out.println("Removing Last Element:");
        ll.removeLast();
        ll.print();

        System.out.println("Finding Middle Element:");
        ll.getMiddle();

        System.out.println("Checking for loop in the list:");
        ll.detectLoop();

        System.out.println("Size of the list: " + ll.getSize());
        System.out.println("Is List Empty? " + ll.isEmpty());
    }
}