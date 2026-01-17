package Basic;

import java.util.*;
public class HashMapClass {

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
        Collections.sort(list, (i1,i2) -> i1.getValue().compareTo(i2.getValue()));
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();

        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void main(String args[]) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("ishal", 10);
        map.put("sachin", 30);
        map.put("aibhav", 20);

        Map<String, Integer> hm1 = sortByValue(map);
        for (Map.Entry<String, Integer> entry : hm1.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
//https://www.geeksforgeeks.org/comparator-interface-java/
//https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
//https://www.geeksforgeeks.org/comparable-interface-in-java-with-examples/