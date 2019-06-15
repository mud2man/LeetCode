/* Bucket: Time:O(n), Space:O(n).
 * 1. Divide each num by (t + 1) and associate it with bucket ID. ex: nums = {1, 5, 9, 1, 5, 9}, where k = 2 , bucket = {0, 1, 3, 0, 1, 3}
 * 2. We shift a window with length = k, and remove nums[i - k - 1] from bucket, and add nums[i] into bucket
 * 3. During the window, there is only one number in a bucket. Otherwise, we hit the answer
 * 4. Then we check the neighbor with (bucketIdx - 1) and (bucketIdx + 1), it the asolute difference meet, we retrun true
 */

import java.util.*;

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(k < 0 || t < 0){
            return false;
        }
        
        Map<Integer, Integer> bucketIdx2Val = new HashMap<>();
        for(int i = 0; i < nums.length; ++i){
            // delete nums[i - k - 1]
            if(i - k - 1 >= 0){
                int deleteNum = nums[i - k - 1];
                int bucketIdx = (deleteNum >= 0)? deleteNum / (t + 1): (deleteNum + 1 / (t + 1)) - 1;
                bucketIdx2Val.remove(bucketIdx);
            }
            // add nums[i]
            int addNum = nums[i];
            int bucketIdx = (addNum >= 0)? addNum / (t + 1): (addNum + 1 / (t + 1)) - 1;
            if(bucketIdx2Val.containsKey(bucketIdx)){
                return true;
            }else if(bucketIdx2Val.containsKey(bucketIdx + 1)){
                int right = bucketIdx2Val.get(bucketIdx + 1);
                if(Math.abs(right - addNum) <= t){
                    return true;
                }
            }else if(bucketIdx2Val.containsKey(bucketIdx - 1)){
                int left = bucketIdx2Val.get(bucketIdx - 1);
                if(Math.abs(left - addNum) <= t){
                    return true;
                }
            }
            bucketIdx2Val.put(bucketIdx, addNum);
        }
        return false;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 5, 3, 6, 4};
        int k = 2;
        int t = 1;
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("t: " + t);
        System.out.println("hitted: " + sol.containsNearbyAlmostDuplicate(nums, k, t));
    }
}
