/* Bit manupulation: O(1)
 * 1. Check if num <= 0
 * 2. Check if num is power of 2
 * 3. Check if any 1 in odd position
 */

import java.util.*;

public class Solution{
    public boolean isPowerOfFour(int num) {
        if(num <= 0){
            return false;
        }
        
        //check if power of 2
        if((num & (num - 1)) != 0){
            return false;
        }
        
        //chechk if any 1 in odd position
        if((num & 0xAAAAAAAA) != 0){
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
