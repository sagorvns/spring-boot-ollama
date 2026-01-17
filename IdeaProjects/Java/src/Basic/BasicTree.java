package Basic;

public class BasicTree {
    static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    /*static class Binarytree {
        static int index = -1;
        public static Node buildTree(int nodes[]) {
            index++;
            if(nodes[index] == -1) {return null;}
            Node root = new Node(nodes[index]);
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);
            return root;
        }
    }*/
    public static void preOrder(Node root) {
        if(root == null) {return;}
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    public static void inOrder(Node root) {
        if(root == null) {return;}
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    public static void postOrder(Node root) {
        if(root == null) {return;}
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        //Binarytree tree = new Binarytree();
        //Node root = tree.buildTree(nodes);
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        preOrder(root);
        System.out.println();
        System.out.println(countNode(root));
        System.out.println(SumOfNode(root));
        System.out.println(height(root));
    }
    public static int countNode(Node root){
        if(root == null) {return 0;}
        int left = countNode(root.left);
        int right = countNode(root.right);
        return left+right+1;
    }
    public static int SumOfNode(Node root){
        if(root == null) {return 0;}
        int left = SumOfNode(root.left);
        int right = SumOfNode(root.right);
        return left+right+root.data;
    }
    public static int height(Node root){
        if(root == null) {return 0;}
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left,right)+1;
    }
}
