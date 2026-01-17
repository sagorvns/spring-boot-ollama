package Basic;

/* Online Java Compiler and Editor */
public class ArrayRotate{

    public static void main(String []args){
        System.out.println("Hello, World!");

        int arr[]={1,2,3,4,5};
        // 2 3 4 5 1
        //5 1 2 3 4
        int k=1,n=arr.length;
        System.out.print("Right: ");
        rightRotate(arr,k,n);
        System.out.print("\n");
        System.out.print("Left: ");
        //leftRotate(arr,k,n);
    }
    public static void leftRotate(int arr[],int k,int n){

        for(int i=0;i<k;i++){
            int first=arr[0];
            for(int j=0;j<n-1;j++)
                arr[j]=arr[j+1];

            arr[n-1]=first;
        }
        for(int l:arr)
            System.out.print(l+" ");
    }


    public static void rightRotate(int arr[],int k,int n){

        for(int i=0;i<k;i++){
            int last=arr[n-1];

            for(int j=n-1;j>0;j--)
                arr[j]=arr[j-1];

            arr[0]=last;
        }
        for(int l:arr)
            System.out.print(l+" ");
    }
}