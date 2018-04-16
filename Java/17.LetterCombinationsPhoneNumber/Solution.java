/* Backtrack: Time:O(3^n), Space:O(n)
 */         

import java.util.*;

public class Solution {
    void helper(int idx, String digits, String subString, String[] bottoms, List<String> combinations){
        char bottom;
        
        if(subString.length() == digits.length()){
            combinations.add(subString);
            return;
        }
        
        bottom = digits.charAt(idx);
        for(char c:  bottoms[bottom - '2'].toCharArray()){
            helper(idx + 1, digits, subString + Character.toString(c), bottoms, combinations);
        }
    }
    
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        String[] bottoms = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        if(digits.length() > 0)
            helper(0, digits, "", bottoms, combinations);
            
        return combinations;
    }
 
    public static void main(String[] args){
        Solution sol;
        String digits = "23";
        sol = new Solution();

        System.out.println("digits: " + digits);
        System.out.println("combinations: " + sol.letterCombinations(digits));
    }
}
