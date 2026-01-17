package Practice;

public class ValidateBST {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Core recursive function with min-max constraints
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        // Validate left subtree and right subtree
        return validate(node.left, min, node.val) &&
                validate(node.right, node.val, max);
    }

    // Print in-order to check ordering
    public void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        ValidateBST treeOps = new ValidateBST();

        /*
            Valid BST:
                   5
                  / \
                 3   7
                / \   \
               2   4   8
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(8);

        treeOps.printInOrder(root);
        System.out.println();

        boolean isValid = treeOps.isValidBST(root);
        System.out.println("Is the tree a valid BST? " + isValid);
    }
}

