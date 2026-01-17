package Basic;// Java program to count frequencies of array items
import java.util.*;
class FrequencyHashMap
{
    static void countFreq(int arr[], int n)
    {
        Map<Integer,Integer> mp = new HashMap<>();
       for (int i = 0; i < n; i++){
            if (mp.containsKey(arr[i]))
                mp.put(arr[i], mp.get(arr[i]) + 1);
            else
                mp.put(arr[i], 1);
        }
        for (Map.Entry<Integer,Integer> entry : mp.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
   public static void main(String args[])
    {
        int arr[] = {10, 20, 20, 10, 10, 20, 5, 20};
        int n = arr.length;
        countFreq(arr, n);
    }

    public static void frStringType(String str,int n){

        HashMap<Character,Integer> map=new HashMap<Character,Integer>();
        for(int i=0;i<n;i++){

            if(map.containsKey(str.charAt(i)))
                map.put(str.charAt(i),map.get(str.charAt(i))+1);
            else
                map.put(str.charAt(i),1);
        }
        for(Map.Entry<Character,Integer> hm: map.entrySet())
            System.out.println(hm.getKey()+" "+hm.getValue());
    }
}
