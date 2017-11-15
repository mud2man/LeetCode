/* Stack: Time:O(n), Space:O(n), LeetCode has a shorter answer
 * 1. Have currInteger to track the current result
 * 2. Have operator to track the current opeartor
 * 3. Have nextInteger to track the next integer
 * 4. Have a stack to store the previous result in the outer parathesis
 * 5. Have a helper method operation to do caculation
 */

import java.util.*;

public class Solution{
    //after operation, reset operator and nextInteger
    private StringBuilder operation(StringBuilder currInteger, StringBuilder operator, StringBuilder nextInteger){
        StringBuilder answer;

        if((operator.length() == 0 || nextInteger.length() == 0) && currInteger.length() > 0){
            answer = currInteger;
        }
        else if(nextInteger.length() > 0 && currInteger.length() == 0){
            answer = new StringBuilder(nextInteger);
            nextInteger.delete(0, nextInteger.length());
        }
        else if(operator.length() == 0 && nextInteger.length() == 0 && currInteger.length() == 0){
            answer = currInteger;
        }
        else{
            int leftOperand = Integer.parseInt(currInteger.toString());
            int rightOperand = Integer.parseInt(nextInteger.toString());
            if(operator.toString().equals("+")){
                int result = leftOperand + rightOperand;
                answer = new StringBuilder(Integer.toString(result));
            }
            else{
                int result = leftOperand - rightOperand;
                answer = new StringBuilder(Integer.toString(result));
            }
            operator.delete(0, operator.length());
            nextInteger.delete(0, nextInteger.length());
        }
        return answer;
    }
    
    public int calculate(String s) {
        Stack<String> stack = new Stack<String>();
        StringBuilder nextInteger = new StringBuilder("");
        StringBuilder currInteger = new StringBuilder("");
        StringBuilder operator = new StringBuilder("");
        
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                nextInteger.append(c);
            }
            else if(c == '+' || c == '-'){
                if(currInteger.length() == 0){
                    operator = new StringBuilder("");
                    operator.append(c);
                    currInteger = nextInteger;
                    nextInteger = new StringBuilder("");
                }
                else{
                    currInteger = operation(currInteger, operator, nextInteger);
                    operator = new StringBuilder("");
                    operator.append(c);
                }
            }
            else if(c == ' '){
                currInteger = operation(currInteger, operator, nextInteger);
            }
            else if(c == '('){
                if(currInteger.length() != 0){
                    stack.push(currInteger.toString());
                    currInteger = new StringBuilder("");
                }
                
                if(operator.length() != 0){
                    stack.push(operator.toString());
                    operator = new StringBuilder("");
                } 
            }
            else{
                currInteger = operation(currInteger, operator, nextInteger);
                if(!stack.isEmpty()){
                    nextInteger = currInteger;
                    String top = stack.pop().toString();
                    if(top.equals("+") || top.equals("-")){
                        operator = new StringBuilder(top);
                        currInteger = operation(new StringBuilder(stack.pop()), operator, nextInteger);
                    }
                    else{
                        currInteger = new StringBuilder(top);
                    } 
                }
            }
        }

        currInteger = operation(currInteger, operator, nextInteger);
        return Integer.parseInt(currInteger.toString());
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "(1+(4+5+2)-3)+(6+8)";
        
        sol = new Solution();
        
        System.out.println("s: " + s);
        System.out.println("answer: " + sol.calculate(s));
    }
}
