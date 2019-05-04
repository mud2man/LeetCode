/* Hash Table: O(n)
 * 1. Use hash table sumMap to store all subSum and their smallest last index
 * 2. Get the difference "diff" between subSum and k
 * 3. If there exists subSum = diff in sumMap, update maxLen =  max(maxLen, i - sumMap.get(diff))
 *
 * ex: nums = [1, -1, 5, -2, 3], k = 3, hash table = [0:-1, 1:0, 5:2, 3:3, 6:4]
 * i = 0: subSum = 1, diff = 2, maxLen = 0
 * i = 1: subSum = 0, diff = 3, maxLen = max(0, 1 - 3) = 0
 * i = 2: subSum = 5, diff = -2, maxLen = 0
 * i = 3: subSum = 3, diff = 0, maxLen = max(0, 3 - (-1)) = 4
 * i = 4: subSum = 6, diff = -3, maxLen = 4
 */

import java.util.*;
public class Solution{
    public int maxSubArrayLen(int[] nums, int k) {
        int subSum = 0;
        HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        sumMap.put(0, -1);
        int maxLen = 0;
        for(int i = 0; i < nums.length; ++i){
            subSum = subSum + nums[i];
            sumMap.putIfAbsent(subSum, i);
            int diff = subSum - k;
            if(sumMap.containsKey(diff)){
                maxLen = Math.max(maxLen, i - sumMap.get(diff));
            }
        }
        return maxLen;
    }
 
    public static void main(String[] args){
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        Solution sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums) + ", k:" + k);
        System.out.println("maxLen: " + sol.maxSubArrayLen(nums, k));
    }
}
