/* Bit Manipulation: Time:O(logn), Space:O(1)
 * 1. Get digit by digit from A, B, C, and accumulate the count by getflipCount
 */

import java.util.*;


public class Solution{
    private int getflipCount(int bitA, int bitB, int bitC){
        if(bitA == 0 && bitB == 0){
            return (bitC == 0)? 0: 1;
        }else if(bitA == 1 && bitB == 1){
            return (bitC == 1)? 0: 2;
        }else{
            return (bitC == 1)? 0: 1;
        }
    }
    
    public int minFlips(int a, int b, int c) {
        int count = 0;
        while(a > 0 || b > 0 || c > 0){
            int bitA = a % 2;
            int bitB = b % 2;
            int bitC = c % 2;
            count += getflipCount(bitA, bitB, bitC);
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int a = 2;
        int b = 6;
        int c = 5;
        System.out.println("a:" + ", b:" + b + ", c:" + c);
        System.out.println("minimum flips:" + sol.minFlips(a, b, c));
    }
}
