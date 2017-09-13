/* Sort: O(n^2)
 */

import java.util.*;

public class Solution{
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                if (nums[i] + nums[j] + nums[k] < 0) j++;
                else if (nums[i] + nums[j] + nums[k] > 0) k--;
                else {
                    result.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        return result;
    }
 
    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> triplets;
        int[] nums = {-1,0,1,2,-1,4};
        
        sol = new Solution();
        
        System.out.println("nums[]: " + Arrays.toString(nums));
        triplets = sol.threeSum(nums);
        
        System.out.println("triplets[][]: ");
        for(List<Integer> triplet: triplets){
            System.out.println(triplet);
        }
    }
}
