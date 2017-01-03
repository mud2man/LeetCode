/* Use BigInteger: O(n^2)
 * 1. Decide the first two numbers
 * 2. Check if the remaining is additive
 */

import java.util.*;
import java.math.*;

public class Solution{
    public boolean fibCheck(BigInteger first, BigInteger second, String remain){
        BigInteger sum;
        BigInteger tail;
        String s;
        
        if(remain.length() == 0){
            return true;
        }
        
        sum = first.add(second);
        
        if(remain.length() >= sum.toString().length()){
            tail = new BigInteger(remain.substring(0, sum.toString().length()));
        }
        else{
            tail = null;
        }
        
        if((tail != null ) && (sum.equals(tail))){
            return fibCheck(second, sum, remain.substring(sum.toString().length()));
        }
        else{
            return false;
        }
    }
    
    public boolean isAdditiveNumber(String num) {
        int len;
        String first;
        String second;
        int i, j;
        
        len = num.length();
        
        for(i = 1; i <= (len / 2); ++i){
            first = num.substring(0, i);
            if((first.charAt(0) == '0') && (first.length() > 1)){
                    continue;
            }
            for(j = i + 1; j <= (len - 1); ++j){
                second = num.substring(i, j);
                if((second.charAt(0) == '0') && (second.length() > 1)){
                    continue;
                }
                if(fibCheck(new BigInteger(first), new BigInteger(second), num.substring(j)) == true){
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
