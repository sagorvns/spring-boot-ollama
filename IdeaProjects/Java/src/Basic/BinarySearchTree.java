package Basic;

public class BinarySearchTree {
    static class Node {
        int data;
        Node left,right;
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    public static void main(String[] args) {
        int nodes[] = {8,3,1,4,6,10,11,14};
        Node root = null;
        for(int value : nodes){
            root = insert(root, value);
        }
        inOrder(root);
        System.out.println();
        System.out.println(search(root,6));
        System.out.println("Min Record "+findMinRec(root));
        System.out.println("Max Record "+findMaxRec(root));
    }
    public static void inOrder(Node root) {
        if(root == null) {return;}
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    public static Node insert(Node root,int value){
        if(root==null){
            root=new Node((value));
            return root;
        }
        if(root.data>value){
            root.left=insert(root.left,value);
        }else{
            root.right=insert(root.right,value);
        }
            return root;
    }
    public static boolean search(Node root,int value){
        if(root==null){
          return false;
        }
        if(root.data>value){
            return search(root.left,value);
        }else if(root.data==value){
            return true;
        }else{
            return search(root.right,value);
        }
    }
  private static int findMinRec(Node root) {
        return root.left == null ? root.data : findMinRec(root.left);
    }
    private static int findMaxRec(Node root) {
        return root.right == null ? root.data : findMaxRec(root.right);
    }
}
