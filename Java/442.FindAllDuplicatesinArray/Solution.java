/* O(n)
 * 1. If nums[idx] != idx + 1, put nums[idx] to nums[nums[idx] - 1], nums[idx] = -1,  and call recursiveSwap
 *
 * ex: nums= {2, 3, 1, 3}
 * time = 0; nums= {-1, 3, 1, 3}, num = 2
 * time = 1; nums= {-1, 2, 1, 3}, num = 3
 * time = 2; nums= {-1, 2, 3, 3}, num = 1
 * time = 3; nums= {1, 2, 3, 3}
 * time = 4; nums= {1, 2, 3, -1}, num = 3 
 * time = 5; nums= {1, 2, 3, -1}, duplicates = [3] 
 */

import java.util.*; // Stack

public class Solution {
    public void recursiveSwap(int[] nums, List<Integer> duplicates, int num){
        int tmp;
        
        if(num == -1){
            return;
        }
        else if(nums[num - 1] == num){
            duplicates.add(num);
        }
        else{
            if(nums[num - 1] == -1){
                nums[num - 1] = num;
            }
            else{
                tmp = nums[num - 1];
                nums[num - 1] = num;
                recursiveSwap(nums, duplicates, tmp);
            }
        }
    }
    
    public List<Integer> findDuplicates(int[] nums) {
        int idx, tmp;
        List<Integer> duplicates;
        
        duplicates = new ArrayList<Integer>();
        
        for(idx = 0; idx < nums.length; idx++){
            if(nums[idx] != idx + 1){
                tmp = nums[idx];
                nums[idx] = -1;
                recursiveSwap(nums, duplicates, tmp);
            }
        }
        return duplicates;
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
