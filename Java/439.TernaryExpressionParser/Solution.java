/* Stack: O(n)
 * 1. Scan from right end
 * 2. If c == '?', pop the top 2 elements in stack, and push the result into the stack
 * 3. If c == ':', continue
 * 4. If c == number, push this number into stack
 *
 * ex: str = "T?T?F:5:3"
 * time[0], stack = [3]
 * time[2], stack = [3, 5]
 * time[4], stack = [3, 5, F], cond = 'T'
 * time[6], stack = [3, F], cond = 'T'
 * time[8], stack = [F]
 */

import java.util.*; // Stack

public class Solution {
    public void caculate(char cond, Stack<Character> stack){
        char right, left, ans;
        
       left = stack.pop();
       right = stack.pop();
        
        if(cond == 'T'){
            ans = left;
        }
        else{
            ans = right;
        }
        stack.push(ans);
    }
    
    public String parseTernary(String expression) {
        int idx;
        char c, cond;
        Stack<Character> stack;
        
        stack = new Stack<Character>();
        
        for(idx = expression.length() - 1; idx >= 0; --idx){
            c = expression.charAt(idx);
            switch (c) {
                case ':':
                    break;
                case '?':
                    cond = expression.charAt(--idx);
                    caculate(cond, stack);
                    break;
                default:
                    stack.push(c);
            }
        } 
        return Character.toString(stack.pop());
    }

    public static void main(String[] args){
        int removeCount;
        Solution sol;
        String expression = "T?T?F:5:3";

        sol = new Solution();

        System.out.println("Expression: " + expression);
        System.out.println("Answer: " + sol.parseTernary(expression));
    }
}
