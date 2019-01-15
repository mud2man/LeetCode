/* Math: Time:O(logn), Space:O(logn)
 * 1. We can caculate the number of digit one of XX... by formula
 *    X = 1
 *    XX = X*8 + 10 + X = 9*X + 10
 *    XXX = (X+XX)*8+100 + (X+XX) = 9*(X+XX) + 100
 *    XXXX = 9*(X + XX + XXX) + 1000
 * 2. Then, we get the count from MSB, assume we use f(n) to get the count
 *    f(330) = (X+XX){0} + (100 + (X+XX)){1} + (X + XX){2} + f(30)
 *    f(30) = X{0} + (10+X){1} + X{2} + f(0)
 *    f(0) = 0
 */

import java.util.*;

public class Solution {
    private int helper(int n, List<Integer> dp, int base){
        if(n == 0){
            return 0;
        }
        
        int msb = n / base;
        if(msb == 0){
            return helper(n % base, dp, base / 10);
        }
        else if(msb == 1){
            int idx = (int)Math.log10(base) - 1;
            int count = (idx >= 0)? dp.get(idx): 0;
            count += (n % base + 1);
            return (count + helper(n % base, dp, base / 10));
        }
        else{
            int idx = (int)Math.log10(base) - 1;
            int count = (idx >= 0)? dp.get(idx) * msb + base: base;
            return (count + helper(n % base, dp, base / 10));
        }
    }
    
    public int countDigitOne(int n) {
        if(n < 0){
            return 0;
        }
        List<Integer> dp = new ArrayList<>();
        int sum = 0;
        long base = 1;
        for(int m = n; m > 0; m = m / 10, base = base * 10){
            sum = sum * 10 + (int)base;
            dp.add(sum);
        }
        return helper(n, dp, (int)(base / 10));
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 13;
        System.out.println("n: " + n);
        System.out.println("count of digit one: " + sol.countDigitOne(n));
    }
}
