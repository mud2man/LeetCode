/* Math: Time:O(1), Space:O(1). LeetCode has a shorter and moe elegant solution
 * 1. Get the zero counts "zeroBits" for m and (n + 1) 
 * 2. If the zero count of m and n on position i are equal, then we or (1 << i)
 * 3. The zero count on each position of number n has a pattern. We can chek the following example
 *
 * ex: m = 5, n = 7
 * 0, 0, 0 \  \
 * 0, 0, 1  |  |
 * 0, 1, 0  6  |
 * 0, 1, 1  |  |
 * 1, 0, 0  |  8
 * 1, 0, 1 /   |
 * 1, 1, 0     |
 * 1, 1, 1    / 
 */

import java.util.*;

public class Solution{
    private int[] getZeroBits(long n){
        int[] zeroBits = new int[32];
        long base = 2;
        for(int i = 0; i < 32; ++i){
            long count = (n / base) * (base / 2);
            count += ((n % base) <= (base / 2))? n % base: base / 2;
            zeroBits[i] = (int)count;
            base = base * 2;
        }
        return zeroBits;
    }
    
    public int rangeBitwiseAnd(int m, int n) {
        int[] mZeroBits = getZeroBits((long)m);
        int[] nZeroBits = getZeroBits((long)n + 1);
        
        int bitwiseAnd = 0;
        for(int i = 0, base = 1; i < 32; ++i, base = base * 2){
            if(nZeroBits[i] - mZeroBits[i] == 0){
                bitwiseAnd |= base;
            }
        }
        return bitwiseAnd;
    }
 
    public static void main(String[] args){
        int n = 7;
        int m = 5;
        Solution sol = new Solution();
        System.out.println("m: " + m + ", n:" + n);
        System.out.println("rangeBitwiseAnd: " + sol.rangeBitwiseAnd(m, n));
    }
}
