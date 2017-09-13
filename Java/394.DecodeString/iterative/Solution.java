/* Stack: O(n)
 * 1. Traverse the string from left to right
 * 2. If c is a digit, count.append(c)
 * 3. If c is '[', push the count into stack and reset count
 * 4. If c is ']', replicate top.sb with top.count time
 * 5. Otherwise, append the top of stack with c
 */

import java.util.*;

public class Solution{
    private class StackNode{
        StringBuilder sb;
        int count;
        StackNode(int c){sb = new StringBuilder(""); count = c;}
    }
    
    public String decodeString(String s) {
        StringBuilder count = new StringBuilder("");
        Stack<StackNode> stack = new Stack<StackNode>();
        stack.push(new StackNode(1));
        
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                count.append(c);
            }
            else if(c == '['){
                stack.push(new StackNode(Integer.parseInt(count.toString())));
                count = new StringBuilder("");
            }
            else if(c == ']'){
                StackNode top = stack.pop();
                StringBuilder decodeSb = new StringBuilder("");
                for(int j = 0; j < top.count; ++j){
                    decodeSb.append(top.sb);
                }
                stack.peek().sb.append(decodeSb);
            }
            else{
                stack.peek().sb.append(c);
            }
        }
        
        return stack.peek().sb.toString();
    }

    public static void main(String[] args){
        Solution sol;
        String s = "3[a]2[bc]";
        String ans;

        sol = new Solution();

        System.out.println("s: " + s);
        ans = sol.decodeString(s);
        System.out.println("ans: " + ans);
    }
}
