/* Math: Time:O(n), Space:O(n). Leetcode has stack-version solution
 * 1. Scan from left and append ')' only the score >= 0
 * 2. Use the same logic, scan again from right, and append '(' only score >= 0
 * 3. Reverse "ret"
 */

import java.util.*; // Stack

public class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder("");
        int score = 0;
        for(char c: s.toCharArray()){
            if(c == '('){
                score++;
                sb.append(c);
            }else if(c == ')'){
                score--;
                if(score >= 0){
                    sb.append(c);
                }else{
                    score++;
                }
            }else{
                sb.append(c);
            }
        }
        
        StringBuilder ret = new StringBuilder("");
        score = 0;
        for(int i = sb.length() - 1; i >= 0; i--){
            char c = sb.charAt(i);
            if(c == ')'){
                score++;
                ret.append(c);
            }else if(c == '('){
                score--;
                if(score >= 0){
                    ret.append(c);
                }else{
                    score++;
                }
            }else{
                ret.append(c);
            }
        }
        return ret.reverse().toString();
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "lee(t(c)o)de)";
        System.out.println("S: " + S);
        System.out.println("After remove: " + sol.minRemoveToMakeValid(S));
    }
}
