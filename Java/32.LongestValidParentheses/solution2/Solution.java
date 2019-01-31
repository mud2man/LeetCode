/* Stack: Time:O(n), 
 * 1. Have a stack contains node, where if node.parentheses == '*', means there are paired parentheses. Otherwise, it means a '('
 * 2. If c == '(', push Node(0, '(')
 * 3. Otherwise, update stack in the case of top == null, top.parentheses == '*', top.parentheses == '('. And merge the top 2, if they are '*'
 * 4. Update max
 *
 * ex: s = "()(()())" 
 * time[0]: stack = {('(', 0)}, max = 0
 * time[1]: stack = {('*', 1)}, max = 1
 * time[2]: stack = {('*', 1), ('(', 0)}, max = 1
 * time[3]: stack = {('*', 1), ('(', 0), ('(', 0)}, max = 1
 * time[4]: stack = {('*', 1), ('(', 0), ('*', 1)}, max = 1
 * time[5]: stack = {('*', 1), ('(', 0), ('*', 1), ('(', 0)}, max = 1
 * time[6]: stack = {('*', 1), ('(', 0), ('*', 2)}, max = 2
 * time[7]: stack = {('*', 4)}, max = 4
 */

import java.util.*;

public class Solution{
    private class Node{
        int count;
        Node(){count = 0;}
    }
    
    public int longestValidParentheses(String s) {
        int max = 0;
        int queued = 0;
        Stack<Node> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(new Node());
            }
            else{
                if(stack.isEmpty()){
                    queued = 0;
                }
                else{
                    int count = stack.pop().count + 1;
                    if(stack.isEmpty()){
                        queued += count;
                        max = Math.max(max, queued);
                    }
                    else{
                        stack.peek().count += count;
                        max = Math.max(max, stack.peek().count);
                    }
                }
            }
        }
        
        return max * 2;
    }
 
    public static void main(String[] args){
        String s = "()(()())";
        Solution sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("longest valid parentheses: " + sol.longestValidParentheses(s));
    }
}
