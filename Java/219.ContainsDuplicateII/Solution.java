/* HashMap: O(n)
 * 1. Have a hashmap to record the latest index w.r.t. the number
 * 2. Visit the numbers from leftmost and check if the difference between the current index and the previous one  <= k
 * 3. If yes, return true
 */          

import java.util.*; // Stack

public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> positionMap = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; ++i){
            int num = nums[i];
            if(positionMap.containsKey(num)){
                if(i - positionMap.get(num) <= k){
                    return true;
                }
                else{
                    positionMap.put(num, i);
                }
            }
            else{
                positionMap.put(num, i);
            }
        }
        
        return false;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 2, 3, 4, 2, 1};
        int k = 3;
        sol = new Solution();

        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("k:" + k);
        System.out.println("is existed: " + sol.containsNearbyDuplicate(nums, k));
    }
}
