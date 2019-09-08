/* HashMap: Time:O(n*k), Space:O(n), where k = longest length of string.
 * 1. Put the string with the same key into hashMap, where key is character count
 * 2. Convert hashmap to list
 */          

import java.util.*;

public class Solution {
     public String getKey(String s){
        int[] count = new int[26];
        for(char c: s.toCharArray()){
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < 26; ++i){
            sb.append(count[i]);
            sb.append("#");
        }
        return sb.toString();
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> anagramsHashMap = new HashMap<String, List<String>>();;
        List<List<String>> anagramsList = new ArrayList<List<String>>();
        
        for(String str: strs){
            anagramsHashMap.computeIfAbsent(getKey(str), key -> new ArrayList<String>()).add(str);
        }
        for (Map.Entry<String, List<String>> entry : anagramsHashMap.entrySet()) {
            anagramsList.add(entry.getValue());
        }
        return anagramsList;
    }
  
    public static void main(String[] args){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Solution sol = new Solution();
        System.out.println("strs: " + Arrays.toString(strs));
        System.out.println("anagrams: " + sol.groupAnagrams(strs));
    }
}
