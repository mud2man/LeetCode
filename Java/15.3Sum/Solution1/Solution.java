/* Sort + Two Pointers: Time: O(n^2), Space:O(1)
 * 1. Sort nums
 * 2. Fix first element nums[i], set lb = i + 1, hb = length - 1, to find the tuples
 * 3. Move i, lb, hb until no duplicates
 */

import java.util.*;

public class Solution{
        public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> tuples = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length - 2){
            int lb = i + 1;
            int hb = nums.length - 1;
            while(lb < hb){
                if(nums[i] + nums[lb] + nums[hb] == 0){
                    tuples.add(Arrays.asList(nums[i], nums[lb], nums[hb]));
                    while(lb + 1 < nums.length && nums[lb] == nums[lb + 1]){
                        lb++;
                    }
                    lb++;
                    while(hb - 1 >= 0 && nums[hb] == nums[hb - 1]){
                        hb--;
                    }
                    hb--;
                }
                else if(nums[i] + nums[lb] + nums[hb] < 0){
                    lb++;
                }
                else{
                    hb--;
                }
            }
            while(i + 1 < nums.length && nums[i] == nums[i + 1]){
                i++;
            }
            i++;
        }
        return tuples;
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
