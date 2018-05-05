/* Stack: Time:O(n), Space:O(n)
 * 1. Have a stack to store the result for each level, which has a initial value {0, +}
 * 2. Delete all space from s, and have a new string "expression"
 * 3. Traverse expression, and update stack according the current char
 *
 * ex: input: '(1+(4+5+2)-3)+(6+8)'
 * time[0]: stack:{{0, +}}
 * time[1]: stack:{{0, +}, {0, +}}, c = '('
 * time[2]: stack:{{0, +}, {1, +}}, c = '+'
 * time[3]: stack:{{0, +}, {1, +}, {0, +}}, c = '('
 * time[4]: stack:{{0, +}, {1, +}, {4, +}}, c = '+'
 * time[5]: stack:{{0, +}, {1, +}, {9, +}}, c = '+'
 * time[6]: stack:{{0, +}, {12, +}}, c = ')'
 * time[7]: stack:{{0, +}, {12, -}}, c = '-'
 * time[8]: stack:{{9, +}}, c = ')'
 * time[9]: stack:{{9, +}, {0, +}}, c = '('
 * time[10]: stack:{{9, +}, {6, +}}, c = '+'
 * time[11]: stack:{{23, +}}, c = ')'
 */

import java.util.*;

public class Solution{
    private class Node{
        int left;
        char operator;
        Node(int l, char o){left = l; operator = o;}
    }
    
    private void operate(Stack<Node> stack, int right){
        Node top = stack.peek();
        top.left = (top.operator == '+')? top.left + right: top.left - right;
    }
    
    public int calculate(String s) {
        StringBuilder expression = new StringBuilder("");
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i); 
            if(c != ' '){
                expression.append(c);
            }
        }
        
        Stack<Node> stack = new Stack<Node>();
        stack.push(new Node(0, '+'));
        int right = 0;
        for(int i = 0; i < expression.length(); ++i){
            char c = expression.charAt(i); 
            switch (c){
                case '(':
                    stack.push(new Node(0, '+'));
                    break;
                case ')':
                    operate(stack, right);
                    right = stack.pop().left;
                    break;
                case '+':
                    operate(stack, right);
                    right = 0;
                    stack.peek().operator = '+';
                    break;
                case '-':
                    operate(stack, right);
                    right = 0;
                    stack.peek().operator = '-';
                    break;
                default:
                    right = right * 10 + (c - '0');
                    
            }
        }
        
        if(right != 0){
            operate(stack, right);
        }
        return stack.pop().left;
    }
  
    public static void main(String[] args){
        Solution sol;
        String s = "(1+(4+5+2)-3)+(6+8)";
        
        sol = new Solution();
        
        System.out.println("s: " + s);
        System.out.println("answer: " + sol.calculate(s));
    }
}
