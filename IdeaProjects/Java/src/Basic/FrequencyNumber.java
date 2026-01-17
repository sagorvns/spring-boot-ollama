package Basic;

import java.util.*;
public class FrequencyNumber {
    public static void main(String args[]){

            int[] arr={11,22,343,42,11,22,12,12,11,3243,22,22,42};
            boolean[] visted=new boolean[arr.length];
            Arrays.fill(visted,false);

            for(int i=0;i<arr.length;i++){
                int count=1;
                if(visted[i]==true)
                    continue;

                    for(int j=i+1;j<arr.length;j++){

                        if(arr[i]==arr[j]){
                            count++;
                            visted[j]=true;
                        }
                    }
                    System.out.println(arr[i]+"="+count);
            }
     }
}
