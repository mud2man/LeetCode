/* Backtrack: Time:O(3^n), Space:O(n)
 */         

import java.util.*;

public class Solution {
    private void backtrack(int idx, String digits, String subString, String[] bottoms, List<String> combinations){
        if(subString.length() == digits.length()){
            if(digits.length() > 0){
                combinations.add(subString);
            }
            return;
        }
        char bottom = digits.charAt(idx);
        for(char c:  bottoms[bottom - '2'].toCharArray()){
            backtrack(idx + 1, digits, subString + Character.toString(c), bottoms, combinations);
        }
    }
    
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        String[] bottoms = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtrack(0, digits, "", bottoms, combinations);    
        return combinations;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String digits = "23";
        System.out.println("digits: " + digits);
        System.out.println("combinations: " + sol.letterCombinations(digits));
    }
}
