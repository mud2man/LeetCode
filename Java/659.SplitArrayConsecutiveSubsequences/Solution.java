/* Greedy: Time:O(n), Space:O(n)
 * 1. Have a list subsequences to store the length of consecutive sequence and the last element as well
 * 2. subsequences.get(index)[0] = the length, subsequences.get(index)[1] = the last element
 * 3. Traverse nums, and dispatch to 3 cases
 * 4. If currentValue + 1 == nums[i], add a new entry into subsequences
 * 5. If currentValue == nums[i], shift index to smaller if possible, otherwise add new entry into subsequences
 * 6. Otherwise, add a new entry into subsequences
 */         

import java.util.*;

public class Solution {
    public boolean isPossible(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        
        ArrayList<int[]> subsequences = new ArrayList<int[]>();
        int index = 0;
        subsequences.add(new int[]{1, nums[0]});
        for(int i = 1; i < nums.length; ++i){
            int currentValue = subsequences.get(index)[1];
            if(currentValue + 1 == nums[i]){
                index = subsequences.size() - 1;
                subsequences.get(index)[0]++;
                subsequences.get(index)[1]++;
            }
            else if(currentValue == nums[i]){
                if((index > 0) && (subsequences.get(index - 1)[1]  + 1 == nums[i])){
                    index--;
                    subsequences.get(index)[0]++;
                    subsequences.get(index)[1]++;
                }
                else{
                    subsequences.add(new int[]{1, nums[i]});
                    index = subsequences.size() - 1;
                }
            }
            else{
                subsequences.add(new int[]{1, nums[i]});
                index = subsequences.size() - 1;
            }
        }
        
        for(int i = 0; i < subsequences.size(); ++i){
            if(subsequences.get(i)[0] < 3){
                return false;
            }
        }
        
        return true;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 2, 3, 3, 4, 4, 5, 5};
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("is possible to split: " + sol.isPossible(nums));
    }
}
