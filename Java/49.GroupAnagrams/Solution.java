/* HashMap + BigInteger: O(n*k), where k = longest length of string 
 * 1. Put the string with the same key into hashMap
 * 2. Convert hashmap to list
 */          

import java.util.*;
import java.math.*;

public class Solution {
    public BigInteger getKey(int[] primes, String s){
        BigInteger key, num;
        
        key = new BigInteger("1");
        for(char c: s.toCharArray()){
            num = new BigInteger(Integer.toString(primes[c - 'a']));
            key = key.multiply(num);
        }
        return key;
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] primes = {2,  3,  5,  7,  11,
                        13, 17, 19 ,23 ,29,
                        31, 37, 41, 43, 47,
                        53, 59, 61, 67, 71, 
                        73, 79, 83, 89, 97, 
                        101}; 
        HashMap<BigInteger, List<String>> anagramsHashMap = new HashMap<BigInteger, List<String>>();;
        List<List<String>> anagramsList = new ArrayList<List<String>>();
        
        for(String str: strs){
            BigInteger key = getKey(primes, str);
            if(anagramsHashMap.containsKey(key)){
                anagramsHashMap.get(key).add(str);
            }
            else{
                List<String> anagrams = new ArrayList<String>();
                anagrams.add(str);
                anagramsHashMap.put(key, anagrams);
            }
        }
        
        for (Map.Entry<BigInteger, List<String>> entry : anagramsHashMap.entrySet()) {
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
