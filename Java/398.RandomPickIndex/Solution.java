/* Reservoir Sampling: O(n)
 * 1. Have a hash map to record the starting index for every target
 * 2. When pick, replace the next index with the probability of (1 / count), where count is the number of appearing time
 */

import java.util.*; // Stack

public class Solution {
    HashMap<Integer, Integer> indexMap;
    Random random;
    int[] nums;
    
    public Solution(int[] nums) {
        indexMap = new HashMap<Integer, Integer>();
        random = new Random();
        this.nums = nums;
        for(int index = 0; index < nums.length; index++){
            int num = nums[index];
            if(!indexMap.containsKey(num)){
                indexMap.put(num, index);
            }
        }
    }
    
    public int pick(int target) {
        int targetIndex = indexMap.get(target);
        int count = 1;
        
        for(int i = targetIndex + 1; i < nums.length; ++i){
            if(nums[i] == target){
                ++count;
                int randomNum = random.nextInt(count);
                if(randomNum == 0){
                    targetIndex = i;
                }
            }
        }

        return targetIndex;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 2, 3, 3, 3};
        int target = 3;

        sol = new Solution(nums);
        for(int i = 0; i < 10; ++i){
            System.out.println("pick(" + target + "): " + sol.pick(target));
        }
    }
}
