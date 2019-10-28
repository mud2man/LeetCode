/* Stack: Time:O(n), Space:O(n)
 */         

import java.util.*;

public class Solution {
    private void operation(Deque<Integer> stack, String operator){
        int rightOperand = stack.pollLast();
        int leftOperand = stack.pollLast();
        
        switch(operator){
            case "+":
                stack.add(leftOperand + rightOperand);
                break;
            case "-":
                stack.add(leftOperand - rightOperand);
                break;
            case "*":
                stack.add(leftOperand * rightOperand);
                break;
            case "/":
                stack.add(leftOperand / rightOperand);
                break;
        }
    }
    
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        for(String token: tokens){
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                operation(stack, token);
            }else{
                stack.add(Integer.parseInt(token));
            }
        }
        return stack.peekLast();
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println("tokens: " + Arrays.toString(tokens));
        System.out.println("eval: " + sol.evalRPN(tokens));
    }
}
