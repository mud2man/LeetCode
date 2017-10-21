/* Bit manupulation: O(1)
 * 1. Check if log4(n) is an integer or not
 */

import java.util.*;

public class Solution{
    public boolean isPowerOfFour(int num) {
        int mask = 0xAAAAAAAA;
        if(num <= 0 || (num & mask) != 0 || (num & (num - 1)) != 0){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        int[] nums = {0, 1, 5, 16, 48, 64, 196};
        Solution sol = new Solution();
        
        for(int num: nums){
            System.out.println(num + " is Power4? " + sol.isPowerOfFour(num));
        }
    }
}
