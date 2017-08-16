/* : Two pointers: O(n)
 * 1. Use two pointers start and end, and traverse from left to right
 * 2. Add the range "start->end" into ranges if encounter a discontinuous gap
 */         

import java.util.*;

public class Solution {
    private void addRange(List<String> ranges, int start, int end){
        if(start == end){
            ranges.add(Integer.toString(start));
        }
        else{
            String range = Integer.toString(start) + "->" + Integer.toString(end);
            ranges.add(range);
        }
    }
    
    public List<String> summaryRanges(int[] nums) {
        int start;
        int end;
        List<String> ranges = new ArrayList<String>();
        
        if(nums.length == 0){
            return ranges;
        }
        
        start = nums[0];
        end = nums[0];
        for(int i = 1; i < nums.length; ++i){
            if(nums[i] == (end + 1)){
                end++;
            }
            else{
                addRange(ranges, start, end);
                start = nums[i];
                end = nums[i];
            }
        }
        addRange(ranges, start, end);
        return ranges;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {0, 1, 2, 4, 5, 7};
        List<String> ranges;
        
        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        ranges = sol.summaryRanges(nums);
        System.out.println("ranges: " + ranges);
    }
}
