/* Math: O(n)
 * 1. Use two majority-counter pairs majoritiesAndCount
 * 2. If majoritiesAndCount[0][1] == 0, majoritiesAndCount[0][0] = nums[i]
 * 3. If majoritiesAndCount[1][1] == 0, majoritiesAndCount[1][0] = nums[i]
 * 4. Otherwise, descrease majoritiesAndCount[0][1] and  majoritiesAndCount[1][1]
 * 5. Check if the count of the majority is bigger n/3. If so. put it in majorities
 */         

import java.util.*;

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> majorities = new ArrayList<Integer>();
        if(nums.length == 0){
            return majorities;
        }
        
        int[][] majoritiesAndCount = new int[2][2];
        
        for(int num: nums){
            if(majoritiesAndCount[0][0] == num){
                majoritiesAndCount[0][1]++;
            }
            else if(majoritiesAndCount[1][0] == num){
                majoritiesAndCount[1][1]++;
            }
            else{
                if(majoritiesAndCount[0][1] == 0){
                    majoritiesAndCount[0][1]++;
                    majoritiesAndCount[0][0] = num;
                }
                else if(majoritiesAndCount[1][1] == 0){
                    majoritiesAndCount[1][1]++;
                    majoritiesAndCount[1][0] = num;
                }
                else{
                    majoritiesAndCount[1][1] = (majoritiesAndCount[1][1] > 0)? (majoritiesAndCount[1][1] - 1): 0; 
                    majoritiesAndCount[0][1] = (majoritiesAndCount[0][1] > 0)? (majoritiesAndCount[0][1] - 1): 0;
                }
            }
        }
        
        majoritiesAndCount[0][1] = 0;
        majoritiesAndCount[1][1] = 0;
        for(int num: nums){
            if(majoritiesAndCount[0][0] == num){
                majoritiesAndCount[0][1]++;
            }
            
            if(majoritiesAndCount[1][0] == num){
                majoritiesAndCount[1][1]++;
            }
        }
        
        if(majoritiesAndCount[0][1] > nums.length / 3){
            majorities.add(majoritiesAndCount[0][0]);
        }
        
        if(majoritiesAndCount[1][1] > nums.length / 3 && majoritiesAndCount[0][0] != majoritiesAndCount[1][0]){
            majorities.add(majoritiesAndCount[1][0]);
        }
    
        return majorities;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {0, 1, 2, 2, 2, 4};
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        List<Integer> majorities = sol.majorityElement(nums);
        System.out.println("majorities: " + majorities);
    }
}
