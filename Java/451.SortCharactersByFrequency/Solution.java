/* Bucket sort and HashMap: O(n)
 * 1. Create a hashMap "freqMap" to record the frequency of every character
 * 2. According the frequency f, and put it into the f-th slot of bucket
 * 3. Iterate bucket from right, and append the character based on the frequency 
 */

import java.util.*; // Stack

public class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> char2Count = new HashMap<>();
        for(char c: s.toCharArray()){
            char2Count.putIfAbsent(c, 0);
            char2Count.put(c, char2Count.get(c) + 1);
        }
        
        Map<Integer, Set<Character>> count2Chars = new HashMap<>();
        for(Map.Entry<Character, Integer> entry: char2Count.entrySet()){
            int count = entry.getValue();
            char c = entry.getKey();
            count2Chars.putIfAbsent(count, new HashSet<>());
            count2Chars.get(count).add(c);
        }
        
        StringBuilder sb = new StringBuilder("");
        for(int i = s.length(); i > 0; --i){
            if(count2Chars.containsKey(i)){
                int count = i;
                Set<Character> chars = count2Chars.get(count);
                for(char c: chars){
                    for(int j = 0; j < count; ++j){
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args){
        Solution sol;
        String s;

        s = "aAbb";
        sol = new Solution();
        
        System.out.println("before sorting: " + s);
        System.out.println("after sorting: " + sol.frequencySort(s));
    }
}
