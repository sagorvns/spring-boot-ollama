package Basic;

import java.util.*;

public class JavaSacnList {
    public static void main(String args[]){

        Scanner sc=new Scanner(System.in);
    /*
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age= sc.nextInt();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);

        //Array
        int[] array = new int[5];
        int arr[]={1,2,3,4,5};
        System.out.print("Enter array data:");
        for(int i=0; i<5; i++) {
            array[i] = sc.nextInt();
        }
        for (int arrr : array) {
            System.out.print(arr+" ");
        }
        */
        //List
        List<Integer> listof= List.of(11,23,3,4,443);
        List<Integer> list= Arrays.asList(11,23,3,4,443);

        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i)+" ");
        // Using enhanced for loop(for-each) for iteration
        System.out.println("for each");
        for(int l:list)
            System.out.print(l+" ");
        System.out.println("iterator");
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next()+" ");
        }
        ListIterator<Integer> itlist = list.listIterator();
        while (itlist.hasNext()) {
         System.out.println(itlist.next());
        }
        // Lambda expression printing all elements in a List
        list.forEach((temp) -> { System.out.println(temp); });
        //stream api
        list.stream().forEach((temp) -> System.out.println(temp));

    }

}
