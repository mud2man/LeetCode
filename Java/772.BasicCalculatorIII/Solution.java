/* Time:O(n), Space:O(n),
 * 1. Use the similar idea of LeetCode#282 to express the expression by {sum, multiply}
 * 2. Remove spaces and wrap s with '(' and ')', then call eval
 * 3. In eval, update nums given the 5 cases of operator +,-,*,/,)
 * 4. +, - are separator, we update sumAndMultiplys[0], and reset sumAndMultiply[1] to 1 or -1
 * 5. *, / are not separator, we only update sumAndMultiplys[1]
 * 6. When operator is ')', we return the result
 */

import java.util.*;

public class Solution{
    private int getNextNum(String s, int[] idx){
        int nextNum = 0;
        while(idx[0] < s.length() && Character.isDigit(s.charAt(idx[0]))){
            nextNum = nextNum * 10 + (s.charAt(idx[0]++) - '0');
        }
        return nextNum;
    }
    
    private int eval(String s, int[] idx){
        idx[0] += (s.charAt(idx[0]) == '(')? 1: 0;
        int[] sumAndMultiply = {0, 1}; //{sum, multiply}
        boolean isDivide = false;
        while(idx[0] < s.length()){
            int nextNum = (s.charAt(idx[0]) == '(')? eval(s, idx): getNextNum(s, idx);
            char operator = s.charAt(idx[0]++);
            switch (operator){
                case '+':
                case '-':
                    sumAndMultiply[1] = (isDivide)? sumAndMultiply[1] / nextNum: sumAndMultiply[1] * nextNum;
                    sumAndMultiply[0] = sumAndMultiply[0] + sumAndMultiply[1];
                    sumAndMultiply[1] = (operator == '-')? -1: 1;
                    isDivide = false;
                    break;
                case '*':
                case '/':
                    sumAndMultiply[1] = (isDivide)? sumAndMultiply[1] / nextNum: sumAndMultiply[1] * nextNum;
                    isDivide = (operator == '*')? false: true;
                    break;
                case ')':
                    sumAndMultiply[1] = (isDivide)? sumAndMultiply[1] / nextNum: sumAndMultiply[1] * nextNum;
                    return sumAndMultiply[0] + sumAndMultiply[1];
            }
        }
        return 0;
    }
    
    public int calculate(String s) {
        s = s.replaceAll("\\s","");
        s = "(" + s + ")";
        int[] idx = new int[1];
        return eval(s, idx);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "2*(5+5*2)/3+(6/2+8)";
        System.out.println("s:" + s);
        System.out.println("result:" + sol.calculate(s));
    }
}
