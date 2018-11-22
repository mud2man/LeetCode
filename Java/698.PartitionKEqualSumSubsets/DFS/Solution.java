/* DFS: Time:O(k^n) Space:O(n)
 * 1. Sort the input array, and create a array buckets[] with initial value (sum / k)
 * 2. Call partition and put the nums[index] into different buckets[] every time 
 * 3. If index == -1 retunr true
 */

import java.util.*;

public class Solution{
    private boolean partition(int[] nums, int idx, int[] buckets){
        if(idx == -1){
            return true;
        }
        
        int num = nums[idx];
        for(int i = 0; i < buckets.length; ++i){
            if(buckets[i] >= num){
                buckets[i] -= num;
                if(partition(nums, idx - 1, buckets)){
                    return true;
                }
                buckets[i] += num;
            }
        }
        return false;
    }
    
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        if(sum % k != 0){
            return false;
        }
        
        int target = sum / k;
        Arrays.sort(nums);
        int[] buckets = new int[k];
        Arrays.fill(buckets, target);
        return partition(nums, nums.length - 1, buckets);
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
