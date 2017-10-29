/* Sort: O(n^3)
 * 1. Sort 
 * 2. Pick up the least two numbers
 * 3. Use two-pointer search to fine the rest two element
 */

import java.util.*;

public class Solution{
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        int length = nums.length;
        
        Arrays.sort(nums);
        for(int i = 0; i < (length - 3); ++i){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            
            for(int j = i + 1; j < (length - 2); ++j){
                if(j > (i + 1) && nums[j] == nums[j - 1]){
                    continue;
                }
                
                int lb = j + 1;
                int hb = length - 1;
                while(lb < hb){
                    int sum = nums[i] + nums[j] + nums[lb] + nums[hb];
                    if(sum == target){
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[lb], nums[hb]));
                        int lbVal = nums[lb];
                        int hbVal = nums[hb];
                        while(lb < length && nums[lb] == lbVal){lb++;}
                        while(hb >= 0 && nums[hb] == hbVal){hb--;}
                    }
                    else if(sum > target){
                        hb--;
                    }
                    else{
                        lb++;
                    }
                }
            }
        }
        return quadruplets;
    }
 
    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> quadruplets;
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        sol = new Solution();
        
        System.out.println("target: " + target);
        System.out.println("nums[]: " + Arrays.toString(nums));
        System.out.println("quadruplets: " + sol.fourSum(nums, target));
    }
}
