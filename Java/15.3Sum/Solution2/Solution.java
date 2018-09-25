/* Sort + HashMap: O(n^2)
 * 1. Sort, and use map to store the used first 2 numbers
 */

import java.util.*;

public class Solution{
    public List<List<Integer>> threeSum(int[] nums) {
        public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> tuples = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < (nums.length - 2); ++i){
            int lb = i + 1;
            int hb = nums.length - 1;
            while(lb < hb){
                int sum = nums[i] + nums[lb] + nums[hb];
                if(sum == 0){
                    if(!map.containsKey(nums[i]) || !map.get(nums[i]).contains(nums[lb])){
                        tuples.add(Arrays.asList(nums[i], nums[lb], nums[hb]));
                        map.putIfAbsent(nums[i], new HashSet<>());
                        map.get(nums[i]).add(nums[lb]);
                    }
                    lb++;
                    hb--;
                }
                else if(sum < 0){
                    lb++;
                }
                else{
                    hb--;
                }
            }
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
