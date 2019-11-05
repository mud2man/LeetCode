/* Dynamic programming: Time:O(n*2^n) Space:O(2^n)
 * 1. We can say we put the number into bucket in a greedy way, and we iterate all possible "lastSelect". So we search all permutation
 * 2. state = used status of nums, e.g.state of {6(used, 5(used), 5(unused), 4(used)} = 2'b1101
 * 3. dp[state] = if the state of nums can fill the bucket which the last selected num didn't overflow the bucket
 * 4. Since if nums can fill k buckets, we can find the last selected num which not overflow the last bucket, we can deduced theory from the end to start
 * 5. dp[state] = dp[prevState0] | dp[prevState1] | ...., where the the last selected num <= room
 */

import java.util.*;

public class Solution{
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0){
            return false;
        }
        
        int bucketSize = sum / k;
        int len = nums.length;
        boolean[] dp = new boolean[1 << len];
        dp[0] = true;
        for(int state = 1; state < dp.length; ++state){
            int mask = 1;
            int subSum = 0;
            for(int i = 0; i < len; ++i, mask = mask << 1){
                if((mask & state) != 0){
                    subSum += nums[i];
                }
            }
            
            int lastSelect = 1;
            int room = (subSum - 1) % bucketSize + 1; //consider the edge case of subSum = bucketSize * m
            for(int i = 0; i < len; ++i, lastSelect = lastSelect << 1){
                if((lastSelect & state) != 0 && nums[i] <= room){
                    int prevState = state & ~lastSelect;
                    if(dp[prevState] == true){
                        dp[state] = true;
                        break;
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }
  
    public static void main(String[] args){
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        Solution sol = new Solution();
        
        System.out.println("k: " + k);
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can be divide? " + sol.canPartitionKSubsets(nums, k));
    }
}
