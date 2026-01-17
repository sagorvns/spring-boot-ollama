package Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/*Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.*/
public class TwoSum {

    public static void main(String[] args) {
        int [] nums = {4,6,8,12,67};
        System.out.println(twoSum(nums,18));
    }

    public static List twoSum(int[] nums, int target){
        int n=nums.length;
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(nums[i]+nums[j]==target){
                    result.add(i);result.add(j);
                }
            }
        }
        return result;
    }

    public static int[] twoSumMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
