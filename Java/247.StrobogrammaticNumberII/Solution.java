/* Backtrace: O(n), n = number of strobogrammatic number
 * 1. Create hashmap ends, middles, medians
 * 2. Call helper recursively
 * 3. If currIdx >= n/2, reach the terminated condition. Generate the strobogrammatic number, and put it in the list
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    public void helper(HashMap<Character, Character> ends, HashMap<Character, Character> middles, 
                       HashMap<Character, Character> medians, int currIdx, int n, StringBuilder strobogrammaticWord, 
                       List<String> strobogrammaticWords){
      
        if (currIdx == 0){
            for(Map.Entry<Character, Character> entry : ends.entrySet()){
                StringBuilder word = new StringBuilder("");
                word.append(entry.getKey());
                helper(ends, middles, medians, currIdx + 1, n, word, strobogrammaticWords);
            }
        }
        else if(currIdx >= n/2){
            if(n % 2 == 1){
                List<StringBuilder> words = new ArrayList<StringBuilder>();
                for(Map.Entry<Character, Character> entry : medians.entrySet()){
                    StringBuilder word = new StringBuilder(strobogrammaticWord.toString());
                    word.append(entry.getKey());
                    words.add(word);
                }
                for(StringBuilder word: words){
                    for(int i = word.length() - 2; i >= 0; --i){
                        char c = word.charAt(i);
                        word.append(middles.get(c));
                    }
                    strobogrammaticWords.add(word.toString());
                }
            }
            else{
               for(int i = strobogrammaticWord.length() - 1; i >= 0; --i){
                    char c = strobogrammaticWord.charAt(i);
                    strobogrammaticWord.append(middles.get(c));
                }
                strobogrammaticWords.add(strobogrammaticWord.toString()); 
            }
        }         
        else{
            for(Map.Entry<Character, Character> entry : middles.entrySet()){
                StringBuilder word = new StringBuilder(strobogrammaticWord.toString());
                word.append(entry.getKey());
                helper(ends, middles, medians, currIdx + 1, n, word, strobogrammaticWords);
            }
        }        
    } 
    
    public List<String> findStrobogrammatic(int n) {
        List<String> strobogrammaticWords = new ArrayList<String>();
        HashMap<Character, Character> ends = new HashMap<Character, Character>();
        HashMap<Character, Character> middles = new HashMap<Character, Character>();
        HashMap<Character, Character> medians = new HashMap<Character, Character>();
        
        ends.put('1', '1');
        ends.put('8', '8');
        ends.put('6', '9');
        ends.put('9', '6');
        
        middles.put('0', '0');
        middles.put('1', '1');
        middles.put('8', '8');
        middles.put('6', '9');
        middles.put('9', '6');
        
        medians.put('0', '0');
        medians.put('1', '1');
        medians.put('8', '8');
        
        if(n > 1){
            helper(ends, middles, medians, 0, n, null, strobogrammaticWords);
        }
        else if (n == 1){
            strobogrammaticWords.add("0");
            strobogrammaticWords.add("1");
            strobogrammaticWords.add("8");
        }
        else{
            strobogrammaticWords.add("");
        }
        return strobogrammaticWords;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        List<String> strobogrammaticWords;
        int n = 3;

        strobogrammaticWords = sol.findStrobogrammatic(n);

        System.out.println("n: " + n);
        System.out.println("strobogrammaticWords: " + strobogrammaticWords);
    }
}
