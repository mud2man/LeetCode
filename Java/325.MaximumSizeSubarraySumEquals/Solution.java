/* Hash Table: O(n)
 * 1. Use hash table subSums to store all subSums and their largest last index
 * 2. Caculate subSums again, and check if (k + subSum) exist in the hash table
 * 3. Update the max length if the largest last index of subSum "subSums.get(k + subSum)" > i
 * 4. [<-----subSum1---->|<-------k-------->|.............] => k = subSum2 - sunSum1
 *     <-------------subSum1--------------->|
 * ex: nums = [-2, -1, 2, 1], k = 1, hash table = [-2:0, -3:1, -1:2, 0:3]
 * time[0]: subSum = -2, key = -1, maxLen = Math.max(0, 2) = 2
 * time[1]: subSum = -3, key = -2
 * time[2]: subSum = -1, key = 0, maxLen = Math.max(3 - 2, 2) = 2
 */

import java.util.*;
public class Solution{
    public int maxSubArrayLen(int[] nums, int k) {
        int subSum;
        int i;
        int maxLen;
        HashMap<Integer, Integer>subSums;
        
        subSum = 0;
        subSums = new HashMap<Integer, Integer>();
        
        for(i = 0; i < nums.length; ++i){
            subSum = subSum + nums[i];
            subSums.put(subSum, i);
        }
        
        maxLen = (subSums.containsKey(k))? subSums.get(k) + 1: 0;
        subSum = 0;
        for(i = 0; i < (nums.length - 1); ++i){
            subSum = subSum + nums[i];
            if(subSums.containsKey(k + subSum) && (subSums.get(k + subSum) > i)){
                maxLen = Math.max(maxLen, subSums.get(k + subSum) - i);   
            }
        }
        
        return maxLen;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {-2, -1, 2, 1};
        int k;
        
        k = 1;
        sol = new Solution();

        System.out.println("nums:" + Arrays.toString(nums) + ", k:" + k);
        System.out.println("maxLen: " + sol.maxSubArrayLen(nums, k));
    }
}
