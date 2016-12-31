/* Dynamic programming: O(2^n)
 * 1. Assume nums = [1, 2, 3]
 * 2. 1st: []
 * 3. 2nd: [], [1] => nums[0]
 * 4. 3rd: [], [1], [2], [1, 2] => nums[1]
 * 5. 3rd: [], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3] => nums[2]
 */

import java.util.*;

public class Solution{
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> dp;
        List<Integer> subset;
        int size;
        int i;
        
        dp = new ArrayList<List<Integer>>();
        dp.add(new ArrayList<Integer>());
        
        for(int num : nums){
            size = dp.size();
            for(i = 0; i < size; ++i){
                subset = new ArrayList<Integer>();
                subset.addAll(dp.get(i));
                subset.add(num);
                dp.add(subset);
            }
        }
        return dp;
    }
 
    public static void main(String[] args){
		Solution sol;
		List<List<Integer>> subsets;
		int[] nums = {1,2,3};
		
		sol = new Solution();
		
        System.out.println("nums[]: " + Arrays.toString(nums));
		subsets = sol.subsets(nums);
		
		System.out.println("subsets[][]: ");
		for(List<Integer> subset: subsets){
			System.out.println(subset);
		}
	}
}
