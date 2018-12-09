/* Math: Time:O(10^2n), Space;O(1)
 * 1. Get the high bound "hb" and low bound "lb" of factors
 * 2. Get the left half of hb * hb, then decrease leftHalf and check if its associated palidrome can be devided by the factors between hb and sqrt(hb)
 * 3. Consider the palidrome with odd digits in the second while loop
 */

import java.util.*;

public class Solution {
    private long getPalidrome(long leftHalf, boolean even){
        StringBuilder left = new StringBuilder(Long.toString(leftHalf));
        StringBuilder palidrome = new StringBuilder(Long.toString(leftHalf));
        for(int i = (even)? left.length() - 1: left.length() - 2; i >= 0; --i){
            palidrome.append(left.charAt(i));
        }
        return Long.valueOf(palidrome.toString());
    }
    
    public int largestPalindrome(int n) {
        if(n == 1){
            return 9;
        }
        
        long hb = 9;
        long lb = 1;
        for(int i = 0; i < (n - 1); ++i){
            hb = hb * 10 + 9;
            lb = lb * 10;
        }

        long palidrome = hb * hb;
        int exp = (int)Math.log10(palidrome) + 1;
        long leftHalf = palidrome / (long)Math.pow(10, exp / 2);
        while(leftHalf >= 0){
            palidrome = getPalidrome(leftHalf, true);
            long limit = (long)Math.sqrt(palidrome);
            for(long i = hb; i >= limit; --i){
                if(palidrome % i == 0 && palidrome / i <= hb && palidrome / i >= lb){
                    return (int)(palidrome % 1337);
                }
            }
            leftHalf--;
        }
        
        leftHalf = palidrome / (long)Math.pow(10, exp / 2);
        while(leftHalf >= 0){
            palidrome = getPalidrome(leftHalf, false);
            long limit = (long)Math.sqrt(palidrome);
            for(long i = hb; i >= limit; --i){
                if(palidrome % i == 0 && palidrome / i <= hb && palidrome / i >= lb){
                    return (int)(palidrome % 1337);
                }
            }
            leftHalf--;
        }
        return 0;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int n  = 2;
        System.out.println("n:" + n);
        System.out.println("largest palindrome:" + sol.largestPalindrome(n));
    }
}
