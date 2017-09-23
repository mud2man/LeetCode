/* Dynamic programming: O(n^2)
 * 1. Have a list prevLens to store all the lengths of palindromic substring ending with index currIdx - 1
 * 2. In every loop, traverse all len in prevLens and check if s.charAt(currIdx - len - 1) == s.charAt(currIdx)
 * 3. If yes, newPrevLens.add(currIdx - startIdx + 1) and accumulate totalCount
 * 4. In the end of a single loop, prevLens = newPrevLens
 */          

import java.util.*; // Stack

public class Solution {
    public int countSubstrings(String s) {
        List<Integer> prevLens = new ArrayList<Integer>();
        int totalCount = s.length();
        
        prevLens.add(0);
        prevLens.add(1);
        for(int currIdx = 1; currIdx < s.length(); ++currIdx){
            char currChar = s.charAt(currIdx);
            List<Integer> newPrevLens = new ArrayList<Integer>();
            newPrevLens.add(0);
            newPrevLens.add(1);
            for(int len: prevLens){
                int startIdx = currIdx - len - 1;
                if(startIdx >= 0 && s.charAt(startIdx) == currChar){
                    totalCount++;
                    newPrevLens.add(currIdx - startIdx + 1);
                }
            }
            prevLens = newPrevLens;
        }
        
        return totalCount;
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "aaa";

        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("count: " + sol.countSubstrings(s));
    }
}
