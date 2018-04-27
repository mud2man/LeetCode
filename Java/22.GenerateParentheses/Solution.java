/* Backtrack: Time:O(n) Space:O(k*n), where k is the number of valid parentheses
 * 1. In backtrack, invoke backtrack with parenthesis + ")" only when score > 0
 */

import java.util.*;

public class Solution{
    void backtrack(List<String> parenthesises, String parenthesis, int lefRemain, int rightRemain, int score){
        if(lefRemain < 0 || rightRemain < 0){
            return;
        }
        
        if(lefRemain == 0 && rightRemain == 0 && parenthesis.length() > 0){
            parenthesises.add(parenthesis);
            return;
        }
        
        backtrack(parenthesises, parenthesis + "(", lefRemain - 1, rightRemain, score + 1);
        if(score > 0){
            backtrack(parenthesises, parenthesis + ")", lefRemain, rightRemain - 1, score - 1);
        }        
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> parenthesises = new ArrayList<String>();
        backtrack(parenthesises, "", n, n, 0);
        return parenthesises;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 3;
        System.out.println("n: " + n);
        System.out.println("parentheses: " + sol.generateParenthesis(n));
    }
}
