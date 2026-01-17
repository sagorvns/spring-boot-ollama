package Practice;

public class LowestCommonAncestorBST {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        // If both p and q are smaller than root, LCA lies in the left
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // If both p and q are greater than root, LCA lies in the right
        else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // Else, root is the split point and hence LCA
        else {
            return root;
        }
    }

    // Helper to print the tree (In-order)
    public void printTree(TreeNode root) {
        if (root == null) return;
        printTree(root.left);
        System.out.print(root.val + " ");
        printTree(root.right);
    }

    public static void main(String[] args) {
        LowestCommonAncestorBST treeOps = new LowestCommonAncestorBST();

        /*
            Construct the BST:
                      6
                     / \
                    2   8
                   / \ / \
                  0  4 7  9
                    / \
                   3   5
        */

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = root.left;         // Node with value 2
        TreeNode q = root.left.right;  // Node with value 4

        treeOps.printTree(root);
        System.out.println();

        TreeNode lca = treeOps.lowestCommonAncestor(root, p, q);
        System.out.println("\nLowest Common Ancestor of " + p.val + " and " + q.val + " is: " + lca.val);
    }
}

