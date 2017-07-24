/* Math: O(logn)
 * 1. Minus dividend with divisor left shift
 * 2. Repeat step1 until dividend is less than divisor
 *
 * ex: dividend = 10, divisor = 2
 * times[0]: dividend = 10, divisor = 2 << 4 = 16 => dividend = 10 - (16 >> 1) = 2, ans = 0 + 4 = 4
 * times[1]: dividend = 2, divisor = 2 << 0 = 16 => dividend = 2 - 2 = 0, ans = 4 + 1 = 5 
 */

import java.util.*; // Stack

public class Solution{
    public int divide(int dividend, int divisor) {
        long ans = 0;
        boolean isNegative = false;
        long longDividend, longDivisor;
        
        if(divisor == 0){
            return Integer.MAX_VALUE;
        }
        
        if((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)){
            isNegative = true;
        }

        longDividend = Math.abs((long)dividend);
        longDivisor = Math.abs((long)divisor);
        while(longDividend >= longDivisor){
            long temp = longDivisor;
            long subAns = 1;
            while(longDividend > temp){
                temp = temp << 1;
                subAns = subAns << 1;
            }
            
            if(longDividend == temp){
                ans += subAns;
                ans = (isNegative == true)? -ans: ans;
                if(ans <= (long)Integer.MAX_VALUE && ans >= (long)Integer.MIN_VALUE){
                    return (int)ans; 
                }
                else{
                    return Integer.MAX_VALUE;
                }       
            }
            
            temp = temp >> 1;
            subAns = subAns >> 1;
            ans += subAns;
            longDividend -= temp;
        }
        
        return (isNegative)? -(int)ans: (int)ans; 
    }
 
    public static void main(String[] args){
        int dividend = 10;
        int divisor = 2;
        Solution sol;

        sol = new Solution();
        System.out.println(dividend + "/" + divisor + ": " + sol.divide(dividend, divisor));
    }
}
