/* TreeMap: Time:O(nlogk), Space:O(k). However Leetcode has O(n) solution
 * 1. Keep the tree map "windowMap" of the elements {nums[i - k], nums[i - k + 1], ..., nums[i]}
 * 2. In each loop, check if the index of deleted key(nums[i - k - 1]) is equals to (i - k - 1)
 * 3. If so, delete the entry from the "windowMap", because there is no other key-value pair in the tree
 * 4. Otherwise, not delete because there is a closer key-value pair
 * 5. Check if the difference between cail/floor and nums[i] is equal to or smaller than t
 * 6. Update entry of key nums[i] with (nums[i], i)
 */

import java.util.*;

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Integer, Integer> windowMap = new TreeMap<Integer, Integer>();
        for(int i = 0; i < nums.length; ++i){
            if(i > k){
                int deleteKey = nums[i - k - 1];
                if(windowMap.get(deleteKey) == (i - k - 1)){
                    windowMap.remove(deleteKey);
                }   
            }
            
            Integer ceil = windowMap.ceilingKey(nums[i]);
            if(ceil != null){
                if(Math.abs((long)ceil - (long)nums[i]) <= (long)t){
                    return true;
                } 
            }
            
            Integer floor = windowMap.floorKey(nums[i]);
            if(floor != null){
                if(Math.abs((long)floor - (long)nums[i]) <= (long)t){
                    return true;
                } 
            }
            
            windowMap.put(nums[i], i);
        }
        
        return false;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 5, 3, 6, 4};
        int k = 2;
        int t = 1;

        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("t: " + t);
        System.out.println("hitted: " + sol.containsNearbyAlmostDuplicate(nums, k, t));
    }
}
