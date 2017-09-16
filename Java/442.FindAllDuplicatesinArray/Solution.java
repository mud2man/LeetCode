/* O(n)
 * 1. Travserse nums from left to right, and call helper 
 * 2. If nums[index] == 0, means index + 1 never meet before, set nums[index] = -1
 * 3. If nums[index] == -1, means index + 1 already meet before, add index + 1 into dulpicates
 * 4. If nums[index] > 0, means index + 1 never meet before, set nums[index] = -1, and call helper with index = nums[index] - 1

 *
 * ex: nums= {2, 3, 1, 3}
 * time = 0; nums= {0, 3, 1, 3}, num = 2
 * time = 1; nums= {0, -1, 1, 3}, num = 3
 * time = 2; nums= {0, -1, -1, 3}, num = 1
 * time = 3; nums= {-1, -1, -1, 3}
 * time = 4; nums= {-1, -1, -1, 0}, num = 3 
 * time = 5; nums= {-1, -1, -1, 0}, duplicates = [3] 
 */

import java.util.*; // Stack

public class Solution {
    private void helper(List<Integer> dulpicates, int[] nums, int index){
        switch (nums[index]){
            case -1:
                dulpicates.add(index + 1);
                break;
            case 0:
                nums[index] = -1;
                break;
            default:
                int nextIndex = nums[index] - 1;
                nums[index] = -1;
                helper(dulpicates, nums, nextIndex);
        }
    }
    
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> dulpicates = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] != -1 && nums[i] != 0){
                int startIndex = nums[i] - 1;
                nums[i] = 0;
                helper(dulpicates, nums, startIndex);
            }   
        }
        return dulpicates;
    }

    public static void main(String[] args){
        int removeCount;
        Solution sol;
        int[] nums = {4, 3 ,2, 7, 8, 2, 3, 1};
        List<Integer> duplicates;

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        duplicates = sol.findDuplicates(nums) ;
        System.out.println("duplicates: " + duplicates);
    }
}
