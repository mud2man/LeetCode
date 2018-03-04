/* Recursive with memorization: Time:O(logn), Space:O(logn). However, leetcode has a bit manupulation version
 * 1. Use hashmap and recursive procedure
 */

import java.util.*;

public class Solution{
    private int helper(long number, HashMap<Long, Integer> numberToCount){
        if(numberToCount.containsKey(number)){
            return numberToCount.get(number);
        }
        
        int count = 0;
        if(number % 2 == 0){
            count = helper(number / 2, numberToCount) + 1;
        }
        else{
            count = Math.min(helper((number - 1) / 2, numberToCount) + 2, helper((number + 1) / 2, numberToCount) + 2);
        }
        
        numberToCount.put(number, count);
        return count;
    }
    
    public int integerReplacement(int n) {
        HashMap<Long, Integer> numberToCount = new HashMap<Long, Integer>();
        numberToCount.put(0L, 0);
        numberToCount.put(1L, 0);
        return helper((long)n, numberToCount);
    }

    public static void main(String[] args){
        Solution sol;
        int n = 7;
        
        sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("replacement time: " + sol.integerReplacement(n));
    }
}
