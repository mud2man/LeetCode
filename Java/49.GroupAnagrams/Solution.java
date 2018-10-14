/* HashMap: Time:O(n*klogk), Space:O(n), where k = longest length of string. We can translate char count to string as key to reduce time complexity to O(n*k)
 * 1. Put the string with the same key into hashMap
 * 2. Convert hashmap to list
 */          

import java.util.*;
import java.math.*;

public class Solution {
    public String getKey(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> anagramsHashMap = new HashMap<String, List<String>>();;
        List<List<String>> anagramsList = new ArrayList<List<String>>();
        
        for(String str: strs){
            String key = getKey(str);
            if(anagramsHashMap.containsKey(key)){
                anagramsHashMap.get(key).add(str);
            }
            else{
                List<String> anagrams = new ArrayList<String>();
                anagrams.add(str);
                anagramsHashMap.put(key, anagrams);
            }
        }
        
        for (Map.Entry<String, List<String>> entry : anagramsHashMap.entrySet()) {
            anagramsList.add(entry.getValue());
        }
        
        return anagramsList;
    }
  
    public static void main(String[] args){
        Solution sol;
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> anagrams;

        sol = new Solution();

        System.out.println("strs: " + Arrays.toString(strs));

        anagrams = sol.groupAnagrams(strs);

        System.out.println("anagrams: ");
        for(List<String> anagram: anagrams){
            System.out.println(anagram);
        }
    }
}
