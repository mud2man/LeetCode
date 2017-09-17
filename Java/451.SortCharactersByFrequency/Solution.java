/* Bucket sort and HashMap: O(n)
 * 1. Create a hashMap "freqMap" to record the frequency of every character
 * 2. According the frequency f, and put it into the f-th slot of bucket
 * 3. Iterate bucket from right, and append the character based on the frequency 
 */

import java.util.*; // Stack

public class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
        
        for(char c: s.toCharArray()){
            if(!frequencyMap.containsKey(c)){
                frequencyMap.put(c, 0);
            }
            frequencyMap.put(c, frequencyMap.get(c) + 1);
        }
        
        Set<Character>[] bucket = new HashSet[s.length() + 1];
        for(Map.Entry<Character, Integer> entry: frequencyMap.entrySet()){
            int frequency = entry.getValue();
            char c = entry.getKey();
            if(bucket[frequency] == null){
                bucket[frequency] = new HashSet<Character>();
            }
            bucket[frequency].add(c);
        }
        
        StringBuilder answer = new StringBuilder("");
        for(int i = s.length(); i >= 0; --i){
            int frequency = i;
            if(bucket[i] != null){
                for(char c: bucket[i]){
                    for(int j = 0; j < frequency; ++j){
                        answer.append(c);
                    }
                }
            }
        }
        
        return answer.toString();
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
