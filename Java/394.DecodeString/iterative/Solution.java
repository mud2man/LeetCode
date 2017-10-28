/* Stack: O(n)
 * 1. Traverse the string from left to right
 * 2. If c is a digit, get the whole muliplication times, and update index with the index next to '['
 * 3. If c is a letter. append it to the sb of top
 * 4. If c is ']', append top.sb to the next top's sb with to.ptimes time
 */

import java.util.*;

public class Solution{
    private class StackNode{
        int times;
        StringBuilder sb;
        StackNode(int t, StringBuilder s){times = t; sb = s;}
    }
    
    public String decodeString(String s) {
        Stack<StackNode> stack = new Stack<StackNode>();
        StackNode top = new StackNode(1, new StringBuilder(""));
        stack.push(top);
        
        int index = 0;
        while(index < s.length()){
            char c = s.charAt(index);
            if(Character.isDigit(c)){
                int nextIndex = s.indexOf('[', index);
                int times = Integer.parseInt(s.substring(index, nextIndex));
                stack.push(new StackNode(times, new StringBuilder("")));
                index = nextIndex + 1;
            }
            else if(Character.isLetter(c)){
                stack.peek().sb.append(c);
                index++;
            }
            else{
                top = stack.pop();
                String pattern = top.sb.toString();
                for(int i = 0; i < top.times; ++i){
                    stack.peek().sb.append(pattern);
                }
                index++;
            }
        }
        return stack.pop().sb.toString();
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
