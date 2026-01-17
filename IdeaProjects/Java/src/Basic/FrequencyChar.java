package Basic;

import java.util.*;
public class FrequencyChar {
    public static void main(String args[]){

       String str="Lofi Version Mangal Bhawan Amangal Hari One hour straight";
        boolean[] visted=new boolean[str.length()];
        str=str.toLowerCase();
        str=str.replaceAll(" ","");
        Arrays.fill(visted,false);

        for(int i=0;i<str.length();i++){
            int count=1;
            if(visted[i]==true)
                continue;

            for(int j=i+1;j<str.length();j++){

                if(str.charAt(i)==str.charAt(j)){
                    count++;
                    visted[j]=true;
                }
            }
            System.out.println(str.charAt(i)+"="+count);
        }
    }
}
