/* Stack: Time:O(n), Space:O(n). LeetCode has dynamic programming solution with the smae complexity
 * 1. Have a variable "queued" to record the paired number
 * 2. Have a stack to record the number of pair with an unpaired '('. ex stack.peek() = 2, if s = (()()
 * 3. Traverse the string and update stack and queued
 * 4. If c = '(', just new a node, and push it into stack
 * 5. Otherwise, if c = ')' and stack is empty, whcih means there is no '(' and be paired with the current ')'. It's will be a break
 * 6. Otherwise, pop the stack and accumulate count with the next top's count
 *
 * ex: s = "()(()())" 
 * time[0]: queued = 0, stack = {0}, max = 0
 * time[1]: queued = 1, stack = {}, max = 1
 * time[2]: queued = 1, stack = {0}, max = 1
 * time[3]: queued = 1, stack = {0, 0}, max = 1
 * time[4]: queued = 1, stack = {1}, max = 1
 * time[5]: queued = 1, stack = {1, 0}, max = 1
 * time[6]: queued = 1, stack = {2}, max = 2
 * time[7]: queued = 4, stack = {}, max = 4
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
            }else{
                if(stack.isEmpty()){
                    queued = 0;
                }else{
                    int count = stack.pop().count + 1;
                    if(stack.isEmpty()){
                        queued += count;
                        max = Math.max(max, queued);
                    }else{
                        stack.peek().count += count;
                        max = Math.max(max, stack.peek().count);
                    }
                }
            }
        }
        return max * 2;
    }
 
    public static void main(String[] args){
        String s = ")()())";
        Solution sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("longest valid parentheses: " + sol.longestValidParentheses(s));
    }
}
