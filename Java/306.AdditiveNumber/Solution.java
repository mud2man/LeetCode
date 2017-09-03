/* Use BigInteger: O(n^2)
 * 1. Decide the first two numbers
 * 2. Check if the remaining is additive
 */

import java.util.*;
import java.math.*;

public class Solution{
    private boolean isValid(String operand0, String operand1, String num, int idx){
        if((idx + operand0.length() + operand1.length()) == num.length()){
            return true;
        }
        
        if((operand0.startsWith("0") && operand0.length() > 1) || (operand1.startsWith("0")  && operand1.length() > 1)){
            return false;
        }
        
        BigInteger operand0Big = new BigInteger(operand0);
        BigInteger operand1Big = new BigInteger(operand1);
        BigInteger sumBig = operand0Big.add(operand1Big);
        String sum = sumBig.toString();
        if(num.startsWith(sum, idx + operand0.length() + operand1.length())){
            return isValid(operand1, sum, num, idx + operand0.length());
        }
        else{
            return false;
        }
    }
           
    public boolean isAdditiveNumber(String num) {
        for(int i = 1; i < num.length() - 1; ++i){
            for(int j = i + 1; j < num.length(); ++j){
                String operand0 = num.substring(0, i);
                String operand1 = num.substring(i, j);
                if(isValid(operand0, operand1, num, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution sol;
        String num  = "199100199";

        sol = new Solution();
        
        System.out.println("num: " + num);
        System.out.println("is additive ?: " + sol.isAdditiveNumber(num));
    }
}
