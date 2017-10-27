/* Stack + DFS: Time: O(Valid Parentheses#), Space: O(Valid Parentheses#)
 * 1. Traverse the string with stack to record if the current substring is valid
 * 2. If not, delete the first pair[0] among the consecutive pair[0]s from the lastDeleteIndex to i
 *  2.1, It guarantees that the string have different lastDeleteIndex are not overlap, and they can contain all combinations
 * 3. If yes, reverse the string and call helper again
 */

import java.util.*;

public class Solution{
    private char[] pair = {')', '('};
    private char[] reversePair = {'(', ')'};
    
    private void helper(String s, int lastDeleteIndex, int startIndex, char[] pair, List<String> validParentheses){
        int stack = 0;
        for(int i = startIndex; i < s.length(); ++i){
            char c = s.charAt(i);
            if(c == pair[0]) {stack--;}
            else if(c == pair[1]) {stack++;}
            else{continue;}
            
            if(stack < 0){
                for(int j = lastDeleteIndex; j <= i; ++j){
                    if(s.charAt(j) == pair[0]){
                        if(j == 0 || s.charAt(j - 1) != pair[0]){
                            String deleteS = s.substring(0, j) + s.substring(j + 1);
                            helper(deleteS, j, i, pair, validParentheses);
                        }
                    }
                }
                return;
            }
        }
        StringBuilder sb = new StringBuilder(s);
        String reverS = sb.reverse().toString();
        if(pair[0] == ')'){
            helper(reverS, 0, 0, reversePair, validParentheses);
        }
        else{
            validParentheses.add(reverS);
        }
    }
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> validParentheses = new ArrayList<String>();
        helper(s, 0, 0, pair, validParentheses);
        return validParentheses;
    }

    public static void main(String[] args){
        String s = "(a)())()";
        Solution sol = new Solution();
        
        System.out.println("s:" + s);
        System.out.println("answer:" + sol.removeInvalidParentheses(s));
    }
}
