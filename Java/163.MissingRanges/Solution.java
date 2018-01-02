/* Two pointer: Time:O(n), Space:O(1)
 * 1. Traverse imput array nums with two pointer marked the lower bound and upper boundi, and invoke getRange
 * 2. If range existed, getRange will return a non-null string
 */

import java.util.*;

public class Solution{
        private String getRange(long lower, long upper){
        if(lower > upper){
            return null;
        }
        
        if(lower == upper){
            return Long.toString(lower);
        }
        else{
            return Long.toString(lower) + "->" + Long.toString(upper);
        }
    }
    
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges  = new ArrayList<String>();
        
        long lb = (long)lower;
        long ub = (long)upper;
        for(int num: nums){
            String range = getRange(lb, (long)num - 1);
            if(range != null){
                ranges.add(range);
            }
            lb = (long)num + 1;
        }
        
        
        String range = getRange(lb, ub);
        if(range != null){
            ranges.add(range);
        }
        
        return ranges;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;

        sol = new Solution();    
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("lower: " + lower);
        System.out.println("upper: " + upper);
        System.out.print("ranges: ");
        System.out.println(sol.findMissingRanges(nums, lower, upper));
    }
}
