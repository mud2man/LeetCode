/* Dynamic Programming: Time:O(n^2), Space: O(n)
 * 1. num2LengthAndCount[i][0] = the max length of subsequence ending with nums[i], the count of the longest subsequence ending with nums[i]
 * 2. In each loop, traverse from 0 to (i - 1), and update maxLength when nums[tail] > nums[i]
 * 3. Traverse again to accumulate num2LengthAndCount[tail][1], when nums[i] < nums[tail] and num2LengthAndCount[i][0] == maxLength
 * 4. Finally, accumulate the count of all node in num2LengthAndCount, where num2LengthAndCount[i][0] == maxLength
 * 
 * ex: nums = {1, 3, 5, 4, 7}
 *     num2LengthAndCount[] = {{1, 1}, {2, 1}, {3, 1}, {3, 1}, {4, 2}}
 */         

import java.util.*;

public class Solution {
    public int findNumberOfLIS(int[] nums) {
        int[][] num2LengthAndCount = new int[nums.length][2]; //{length, count}
        int golbalMaxLength = 0;
        for(int tail = 0; tail < nums.length; ++tail){
            int maxLength = 0;
            for(int i = tail - 1; i >= 0; --i){
                if(nums[i] < nums[tail]){
                    maxLength = Math.max(maxLength, num2LengthAndCount[i][0]);
                }
            }
            num2LengthAndCount[tail][0] = maxLength + 1;
            if(maxLength > 0){
                for(int i = tail - 1; i >= 0; --i){
                    if(nums[i] < nums[tail]) {
                        num2LengthAndCount[tail][1] += (num2LengthAndCount[i][0] == maxLength)? num2LengthAndCount[i][1]: 0;
                    }
                }
            }else{
                num2LengthAndCount[tail][1] = 1;
            }
            golbalMaxLength = Math.max(golbalMaxLength, num2LengthAndCount[tail][0]);
        }
        
        int count = 0;
        for(int i = 0; i < nums.length; ++i){
            count += (num2LengthAndCount[i][0] == golbalMaxLength)? num2LengthAndCount[i][1]: 0;
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 3, 5, 4, 7};
        
        sol = new Solution();
        System.out.println("number of longest increasing subsequence: " + sol.findNumberOfLIS(nums));
    }
}
