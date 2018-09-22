/* Math: Time:O(n), Space:O(1)
 * 1. Split equation to "left" and "right", and append sign to the front
 * 2. Parse the "left" and "right" use "merge", and it will return array a, where a[0] = the number of x, a[1] is the constant
 * 3. Caculate x with right[1] - left[1] / left[0] - right[0]
 */

import java.util.*;

public class Solution{
    private int[] merge(String e){
        int[] a = new int[2];
        int idx = 0;
        while(idx < e.length()){
            boolean postive = (e.charAt(idx++) == '+')? true: false;
            int num = 0;
            while(idx < e.length() && Character.isDigit(e.charAt(idx))){
                num = num * 10 + (e.charAt(idx++) - '0');
            }
            
            if(idx < e.length() && e.charAt(idx) == 'x'){
                num = Character.isDigit(e.charAt(idx - 1))? num: 1;
                a[0] += (postive)? num: -num;
                idx++;
            }
            else{
                a[1] += (postive)? num: -num;
            }
        }
        return a;
    }
    
    public String solveEquation(String equation) {
        String[] expressions = equation.split("=");
        expressions[0] = (expressions[0].charAt(0) != '-')? "+" + expressions[0]: expressions[0];
        int[] left = merge(expressions[0]);
        expressions[1] = (expressions[1].charAt(0) != '-')? "+" + expressions[1]: expressions[1];
        int[] right = merge(expressions[1]);
        int x = left[0] - right[0];
        int val = right[1] - left[1];
        
        if(x == 0 && val == 0){
            return "Infinite solutions";
        }
        else if(x == 0 && val != 0){
            return "No solution";
        }
        else{
            val = val / x;
            return "x=" + Integer.toString(val);
        }
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String equation = "x+5-3+x=6+x-2";
        System.out.println("equation: " + equation);
        System.out.println("answer: " + sol.solveEquation(equation));
    }
}
