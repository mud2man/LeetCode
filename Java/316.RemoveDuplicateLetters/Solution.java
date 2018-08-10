/* Greedy: Time:O(n), Space:O(n). LeetCode has a shorter answer
 * 1. Every time, chooce the smallest char which conatins all other chars
 * 2. Recover the map "char2Count" from nextStart and end. Then call dfs given nextStart
 * 3. Since evert time, the size of char2Count will reduce 1. The time complexity is 26n
 */

import java.util.*;

public class Solution{
    private void dfs(StringBuilder letters, String s, int start, Map<Character, Integer> char2Count){
        if(char2Count.isEmpty()){
            return;
        }
        
        int end;
        int nextStart = 0;
        char minC = 'z' + 1;
        for(end = start; end < s.length(); ++end){
            char c = s.charAt(end);
            if(!char2Count.containsKey(c)){
                continue;
            }
            
            char2Count.put(c, char2Count.get(c) - 1);
            int count = char2Count.get(c);
            if(count == 0){
                if(c < minC){
                    nextStart = end + 1;
                    minC = c;
                }
                else{
                    for(int i = end; i >= nextStart; --i){
                        c = s.charAt(i);
                        if(minC != c && char2Count.containsKey(c)){
                              char2Count.put(c, char2Count.get(c) + 1); 
                        }
                    } 
                }
                break;
            }
            else{
                if(c < minC){
                    nextStart = end + 1;
                    minC = c;
                }
            }
        }
        
        letters.append(minC);
        char2Count.remove(minC);
        dfs(letters, s, nextStart, char2Count);
    }
    
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> char2Count = new HashMap<>();
        
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            char2Count.putIfAbsent(c, 0);
            char2Count.put(c, char2Count.get(c) + 1);
        }
        
        StringBuilder letters = new StringBuilder("");
        dfs(letters, s, 0, char2Count);
        return letters.toString();
    }
 
    public static void main(String[] args){
        String s = "cbacdcbc";
        Solution sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("s after reduce: " + sol.removeDuplicateLetters(s));
    }
}
