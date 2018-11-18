/* HashMap: Time:O(n), Space: O(k)
 * 1. Have a map, where key is sum % k, value is the smallest index 
 * 2. If exist j, s.t. nums[0] + nums[1] + ...nums[j] = sum % k. Then, nums[j + 1] + nums[j + 2] + ... + nums[i] = k * n
 * 3. Also, we need to check if there is any consecutive 0s, because 0 * k = 0
 */         

import java.util.*;

public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        k = Math.abs(k);
        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(0, -1);
        int sum = 0;
        for(int i = 0; i < nums.length; ++i){
            sum += nums[i];
            if(k > 0){
                int remain = sum % k;
                if(visited.containsKey(remain) && i - visited.get(remain) >= 2){
                    return true; 
                }
                visited.putIfAbsent(remain, i);
            }
            
            // 0 = k * 0
            if(i > 0 && nums[i] == 0 && nums[i - 1] == 0){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        
        sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("k:" + k);
        System.out.println("continuous subarray with sum = n*k exists: " + sol.checkSubarraySum(nums, k));
    }
}
