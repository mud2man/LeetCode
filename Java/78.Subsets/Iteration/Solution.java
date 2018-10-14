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
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        answer.add(new ArrayList<Integer>());
        
        for(int num: nums){
            int size = answer.size();
            for(int i = 0; i < size; ++i){
                List<Integer> newSet = new ArrayList<Integer>(answer.get(i));
                newSet.add(num);
                answer.add(newSet);
            }
        }
        return answer;
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
