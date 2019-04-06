/* Time:O(n), Space:O(1), where n is the length of string
 * 1. Remove all empty spaces, and represent the intermedia caculation result as (sum, multiply) pair. e.g., 2+3*2=(2, 6), 2+1=(2, 1)
 * 2. When operator is '+' or '-', update sum by (sum + multiply), multiply = (+/-)number
 * 3. When operator is '*' or '/', update multiply by (multiply * number) or (multiply / number)
 */

import java.util.*;

public class Solution{
    private int getNextNumber(String s, int[] idx){
        int number = 0;
        while(idx[0] < s.length() && Character.isDigit(s.charAt(idx[0]))){
            number = number * 10 + (s.charAt(idx[0]++) - '0');
        }
        return number;
    }
    
    public int calculate(String s) {
        s = s.replaceAll("\\s","");
        int[] idx = {0};
        int sum = 0;
        int multiply = getNextNumber(s, idx);
        while(idx[0] < s.length()){
            char operator = s.charAt(idx[0]++);
            int number = getNextNumber(s, idx);
            switch(operator){
                case '+':
                    sum = sum + multiply;
                    multiply = number;
                    break;
                case '-':
                    sum = sum + multiply;
                    multiply = -number;
                    break;
                case '*':
                    multiply = multiply * number;
                    break;
                case '/':
                    multiply = multiply / number;
                    break;
            }
        }
        return (sum + multiply);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "3 +2*2 ";
        System.out.println("s:" + s);
        System.out.println("result:" + sol.calculate(s));
    }
}
