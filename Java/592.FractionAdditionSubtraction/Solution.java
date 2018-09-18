/* Math: O(n)
 * 1. Add sign to the front of input "expression" to make the pattern general
 * 2. Call "getNext" to get the next fraction, and "add" to update "sum"
 * 3. Use gcd to achieve the irreducible fraction
 */

import java.util.*;

public class Solution{
    private int gcd(int x, int y){
        if(x == 0 || y == 0 || x == 1 || y == 1){
            return 1;
        }
        int small = Math.min(x , y);
        int big = Math.max(x , y);
        return (big % small == 0)? small: gcd(small, big % small);
    }
    
    private int[] add(int[] x, int[] y){
        int[] z = new int[]{x[0] * y[1] + y[0] * x[1], x[1] * y[1]};
        int factor = gcd(Math.abs(z[0]), Math.abs(z[1]));
        z[0] /= factor;
        z[1] /= factor;
        return z;
    }
    
    private int[] getNext(String expression, int[] idx){
        boolean positive = (expression.charAt(idx[0]++) == '+')? true: false;
        int numerator = 0;
        while(expression.charAt(idx[0]) != '/'){
            numerator = numerator * 10 + (expression.charAt(idx[0]++) - '0');
        }
        int denominator = 0;
        idx[0]++;
        while(idx[0] < expression.length() && Character.isDigit(expression.charAt(idx[0]))){
            denominator = denominator * 10 + (expression.charAt(idx[0]++) - '0');
        }
        return positive? new int[]{numerator, denominator}: new int[]{-numerator, denominator};
    }
    
    public String fractionAddition(String expression) {
        if(Character.isDigit(expression.charAt(0))){
            expression = "+" + expression;
        }
        int[] sum = new int[]{0, 1};
        int[] idx = {0};
        while(idx[0] < expression.length()){
            int[] fraction = getNext(expression, idx);
            sum = add(sum, fraction);
        }
        
        sum[1] = (sum[0] == 0)? 1: sum[1] ;
        return Integer.toString(sum[0]) + "/" + Integer.toString(sum[1]);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String expression = "-1/2+1/2+1/3";
        System.out.println("expression: " + expression);
        System.out.println("result: " + sol.fractionAddition(expression));
    }
}
