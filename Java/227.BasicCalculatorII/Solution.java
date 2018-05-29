/* Time:O(n), Space:O(n), where n is the length of string. However, LeetCode has a one-shot stack approach
 * 1. Parse string and have 2 lists "operators" and "operands"
 * 2. Caculate '*' and '/' first, then '+' and '-' later
 * 3. When caculating '*' and '/', update the operand of index "lastIndex"
 * 4. When caculating '+' and '-', update leftOperand
 */

import java.util.*;

public class Solution{
        public int calculate(String s) {
        List<Character> operators = new ArrayList<Character>();
        List<Integer> operands = new ArrayList<Integer>();
        int num = 0;
        boolean hasNum = false;
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(c == ' '){
                if(hasNum){
                    operands.add(num);
                    num = 0;
                }
                hasNum = false;
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/'){
                operators.add(c);
                if(hasNum){
                    operands.add(num);
                    num = 0;
                }
                hasNum = false;
            }
            else{
                hasNum = true;
                num = num * 10 + (c - '0');
            }
        }
        
        if(hasNum){
            operands.add(num);
        }
        
        int lastIndex = 0;
        for(int i = 0; i < operators.size(); ++i){
            char operator = operators.get(i);
            if(operator == '*' || operator == '/'){
                int result = (operator == '*')? (operands.get(i) * operands.get(i + 1)): (operands.get(i) / operands.get(i + 1));
                operands.set(lastIndex, result);
                operands.set(i + 1, result);
                operators.set(i, '.');
            }
            else{
                lastIndex = i + 1;
            }
        }
        
        boolean noPlusSubtract = true;
        int leftOperand = 0;
        for(int i = 0; i < operators.size(); ++i){
            char operator = operators.get(i);
            if(operator == '+' || operator == '-'){
                if(noPlusSubtract){
                    leftOperand = operands.get(i);
                    noPlusSubtract = false;
                }
                leftOperand = (operator == '+')? (leftOperand + operands.get(i + 1)): (leftOperand - operands.get(i + 1));
            }
        }
        
        return (noPlusSubtract)? operands.get(0): leftOperand;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "3 +2*2 ";
        System.out.println("s:" + s);
        System.out.println("result:" + sol.calculate(s));
    }
}
