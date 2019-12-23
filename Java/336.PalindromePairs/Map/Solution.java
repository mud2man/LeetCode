/* Hash: O(n*k^2), where n is the length of words, k is the longest length or word. Need to check a shorter solution in LeetCode
 * 1. Have leftMap to store the needed left partner and its associate right partner's index
 * 2. Have rightMap to store the needed right partner and its associate left partner's index
 * 3. In every iteration, check if the current word can match other's left or right partner
 * 4. In updateMap, check if other can match its own left or right partner
 * 5. The adding part will not repeat, because we are always looking for a shorter partner in updateMap, and shorter-or-equal-length partner in palindromePairs
 */

import java.util.*;
import java.math.*;

public class Solution{
    private boolean isPalindrome(String word){
        for(int i = 0; i < (word.length() / 2); ++i){
            if(word.charAt(i) != word.charAt(word.length() - i - 1)){
                return false;
            }
        }
        return true;
    }
    
    private void updateMap(HashMap<String, Integer> indexMap, HashMap<String, List<Integer>> map, String word, int index, boolean findLeft, List<List<Integer>> pairs){
        if(findLeft){
            StringBuilder right = new StringBuilder("");
            for(int i = 0; i <= word.length(); ++i){
                if(isPalindrome(word.substring(i, word.length()))){
                    String key = right.toString();
                    map.putIfAbsent(key, new ArrayList<Integer>());
                    map.get(key).add(index);
                    if(right.length() < word.length() && indexMap.containsKey(right.toString())){
                        pairs.add(Arrays.asList(index, indexMap.get(right.toString())));
                    }
                }
                if(i < word.length()){
                    right.insert(0, word.charAt(i));
                }
            }
        }
        else{
            StringBuilder left = new StringBuilder("");
            for(int i = word.length(); i >= 0; --i){
                if(isPalindrome(word.substring(0, i))){
                    String key = left.toString();
                    map.putIfAbsent(key, new ArrayList<Integer>());
                    map.get(key).add(index);
                    if(left.length() < word.length() && indexMap.containsKey(left.toString())){
                        pairs.add(Arrays.asList(indexMap.get(left.toString()), index));
                    }
                }
                if(i > 0){
                    left.append(word.charAt(i - 1));
                }
            }
        }
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new ArrayList<List<Integer>>();
        HashMap<String, List<Integer>> leftMap = new HashMap<String, List<Integer>>();
        HashMap<String, List<Integer>> rightMap = new HashMap<String, List<Integer>>();
        HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
        
        for(int i = 0; i < words.length; ++i){
            indexMap.put(words[i], i);
            if(leftMap.containsKey(words[i])){
                List<Integer> lefts = leftMap.get(words[i]);
                for(Integer left: lefts){
                    pairs.add(Arrays.asList(left, i));
                }
            }
            if(rightMap.containsKey(words[i])){
                List<Integer> rights = rightMap.get(words[i]);
                for(Integer right: rights){
                    pairs.add(Arrays.asList(i, right));
                }
            }
            updateMap(indexMap, leftMap, words[i], i, true, pairs);
            updateMap(indexMap, rightMap, words[i], i, false, pairs);
        }
        return pairs;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("palindrome pairs: " + sol.palindromePairs(words));
	}
}
