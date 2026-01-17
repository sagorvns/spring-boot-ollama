package Practice;

public class MergeTwoSortedLists {

    // Definition for singly-linked list
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // Method to merge two sorted linked lists
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        var dummy = new ListNode(-1); // Temporary head
        var current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach the remaining nodes
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    // Utility method to print list
    public static void printList(ListNode head) {
        var curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        // First sorted list: 1 -> 2 -> 4
        var l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        // Second sorted list: 1 -> 3 -> 5
        var l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(5);

        System.out.println("List 1:");
        printList(l1);

        System.out.println("List 2:");
        printList(l2);

        var merged = mergeTwoLists(l1, l2);

        System.out.println("Merged List:");
        printList(merged);
    }
}

