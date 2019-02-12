/* Recursive: Time:O(n), Space:O(n)
 * 1. Have a utility method calculate to caculate the current expression
 * 2. Start with left = 0, sign = '-'
 * 3. When c == '(', call calculate recursively, and update left then reset right
 * 4. When c == ')', update left and return left
 * 5. When c == '+' | '-' | ' ', update left and sign
 * 6. When c is digit, update right 
 */

import java.util.*;

public class Solution{
    private int calculate(StringBuilder expression, int[] idx){
        Boolean sign = true;
        int left = 0;
        int right = 0; 
        while(idx[0] < expression.length()){
            char c = expression.charAt(idx[0]++);
            switch(c){
                case '(':
                    right = calculate(expression, idx);
                    left += (sign)? right: -right;
                    right = 0;
                    break;
                case ')':
                    left += (sign)? right: -right;
                    return left;
                case '+':
                case '-':
                case ' ':
                    left += (sign)? right: -right;
                    right = 0;
                    sign = (c == '-')? false: (c == '+')? true: sign;
                    break;
                default:
                    right = right * 10 + (c - '0');
            }
        }
        left += (sign)? right: -right;
        return left;
    }
    
    public int calculate(String s) {
        StringBuilder expression = new StringBuilder(s.trim());
        return calculate(expression, new int[1]);
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "(1+(4+5+2)-3)+(6+8)";
        
        sol = new Solution();
        
        System.out.println("s: " + s);
        System.out.println("answer: " + sol.calculate(s));
    }
}
