/* Math: O(1), but leetcode has bit-manipulation solution
 * 1. Check if log4(n) is an integer or not
 */

import java.util.*;

public class Solution{
    public boolean isPowerOfFour(int num) {
        if(num < 0){
            return false;
        }
        
        double dk = Math.log(num) / Math.log(4);
        int ik = (int)dk;
        return (dk == (double)ik);
    }

    public static void main(String[] args){
        Solution sol;
        int n = 32;
        sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("isPowerOfFour: " + sol.isPowerOfFour(n));
    }
}
