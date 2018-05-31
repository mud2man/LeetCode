/* Time:O(n^2), Space:O(n), However, LeetCode has O(n) 2-stack approach
 * 1. Use stack to store the numbers with operator '+' and '-', and use variable 'optr' to store the previous operator
 * 2. When hitting '(', call getNextExpression to get the expresstion blocked by '(' and ')', and call 'eval' to get the number
 * 3. When hiiting operators, call 'push' to do operation and push the number into stack
 * 4. When hitting digit, update num
 */

import java.util.*;

public class Solution{
    private void push(Stack<Integer> stack, char optr, int num){
        switch (optr){
            case '+':
                stack.push(num);
                break;
            case '-':
                stack.push(-num);
                break;
            case '*':
                stack.push(stack.pop() * num);
                break;
            case '/':
                stack.push(stack.pop() / num);
                break;
        }
    }
    
    private String getNextExpression(String s, int start){
        int count = 0;
        int end = start;
        while(end < s.length()){
            char c = s.charAt(end++);
            if(c == '('){
                count++;
            }
            else if(c == ')'){
                count--;
                if(count == 0){
                    return s.substring(start, end);
                }
            }
        }
        return s;
    }
    
    private int eval(String s){
        Stack<Integer> stack = new Stack<Integer>();
        char optr = '+';
        int idx = 0;
        int num = 0;
        while(idx < s.length()){
            char c = s.charAt(idx++);
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }
            else if(c == '+' || c == '-' || c == '*' || c == '/'){
                push(stack, optr, num);
                optr = c;
                num = 0;
            }
            else{
                String expr = getNextExpression(s, --idx);
                idx += expr.length();
                num = eval(expr.substring(1, expr.length() - 1));
            }
            
            if(idx == s.length()){
                push(stack, optr, num);
            }
        }
        
        int ret = 0;
        while(!stack.isEmpty()){
            ret += stack.pop();
        }
        return ret;
    }
    
    public int calculate(String s) {
        s = s.replaceAll("\\s+","");
        return eval(s);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "2*(5+5*2)/3+(6/2+8)";
        System.out.println("s:" + s);
        System.out.println("result:" + sol.calculate(s));
    }
}
