/* Two pointer: Time:O(n), Space:O(1)
 * 1. Traverse input array nums with two pointer "prev" and "num" 
 * 2. If prev != (long)num - 1 && prev < num, getRange will return a range
 */

import java.util.*;

public class Solution{
    private String getRange(long lower, long upper){
        return (lower == upper)? Long.toString(lower): Long.toString(lower) + "->" + Long.toString(upper);
    }
    
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges  = new ArrayList<String>();
        long prev = (long)lower - 1;
        for(int num: nums){
            if(prev != (long)num - 1 && prev < num){
                ranges.add(getRange(prev + 1, (long)num - 1));
            }
            prev = (long)num;
        }
        
        if(prev != (long)upper){
            ranges.add(getRange(prev + 1, (long)upper));
        }
        return ranges;
    }
 
    public static void main(String[] args){
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;

        Solution sol = new Solution();    
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("lower: " + lower);
        System.out.println("upper: " + upper);
        System.out.print("ranges: ");
        System.out.println(sol.findMissingRanges(nums, lower, upper));
    }
}
