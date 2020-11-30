/* Binary Search: Time:O(nlogn), Space:O(n). Similar with LC#673: Number of Longest Increasing Subsequence
 * 1. Have "index2LeftCount" to record the length of longest increasing sequence ending with nums[i] from left end
 * 2. Use the same method "searchAndReplace+ to get the length of longest increasing sequence ending with nums[i] from rigth end
 * 3. Update "min" with min(min,  nums.length - (leftCount + rightCount + 1)) while on step 2
 */

import java.util.*; // Stack


public class Solution {
     private int searchAndReplace(List<Integer> monotonousStack, int num){
        int left = 0;
        int right = monotonousStack.size() - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(monotonousStack.get(mid) >= num){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if(left == monotonousStack.size()){
            monotonousStack.add(num);
        }
        monotonousStack.set(left, num);
        return left;
    }
    
    public int minimumMountainRemovals(int[] nums) {
        List<Integer> monotonousStack = new ArrayList<>();
        Map<Integer, Integer> index2LeftCount = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int replaceIdx = searchAndReplace(monotonousStack, nums[i]);
            index2LeftCount.put(i, replaceIdx);
        }
        int min = nums.length;
        monotonousStack = new ArrayList<>();
        for(int i = nums.length - 1; i >= 0; i--){
            int leftCount = index2LeftCount.get(i);
            int rightCount = searchAndReplace(monotonousStack, nums[i]);
            if(leftCount > 0 && rightCount > 0){
                min = Math.min(min, nums.length - (leftCount + rightCount + 1));
            }   
        }
        return min;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {2, 1, 1, 5, 6, 2, 3, 1}; 
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("minimum number of removals to make mountain array:" + sol.minimumMountainRemovals(nums));
    }
}
