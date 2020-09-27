/* Dynamic programming: Time:O(n), Space:O(1)
 * 1. Update remain2MaxSums[i] when iterating nums
 * 2. nextRemain2MaxSums[nextRemain] = max(nextRemain2MaxSums[nextRemain], remain2MaxSums[remain] + num), 
 *    where nextRemain = (remain2MaxSums[remain] + num) % 3 and 0 <= remain <= 2 
 */     

import java.util.*; // Stack

public class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] remain2MaxSums = new int[3];
        for(int num: nums){
            int[] nextRemain2MaxSums = new int[3];
            for(int remain = 0; remain < 3; ++remain){
                int nextRemain = (remain2MaxSums[remain] + num) % 3;
                nextRemain2MaxSums[nextRemain] = Math.max(nextRemain2MaxSums[nextRemain], remain2MaxSums[remain] + num);
            }
            for(int remain = 0; remain < 3; ++remain){
                remain2MaxSums[remain] = Math.max(remain2MaxSums[remain], nextRemain2MaxSums[remain]);
            }
        }
        return remain2MaxSums[0];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {3, 6, 5, 1, 8};
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("maximum sum divisible by 3:" + sol.maxSumDivThree(nums));
    }
}
