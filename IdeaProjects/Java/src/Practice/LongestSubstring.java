package Practice;

import java.util.*;
/*
Longest Substring Without Repeating Characters
 */
public class LongestSubstring {

    public static void main(String[] args) {
        System.out.println(findLength("aabcdefgabcbb"));//abcdefg
    }

    static int findLength(String input) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;   // stores the maximum length found so far
        int start = 0;       // left pointer of sliding window
        for (int end = 0; end < input.length(); end++) {
            char currentChar = input.charAt(end);  // right pointer expands window
            // If character already exists in map → adjust window start
            if (map.containsKey(currentChar)) {
                // Move start just after the last occurrence of currentChar
                start = Math.max(map.get(currentChar) + 1, start);
            }
            // Update character's latest index
            map.put(currentChar, end);
            // Calculate window size and update maxLength
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}
