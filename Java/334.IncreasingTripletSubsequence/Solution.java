/* DynamicO programming: (n)
 * 1. increasingList[0] = the smallest last element with length = 1
 * 2. increasingList[1] = the smallest last element with length = 2
 * ex: nums[] = [4, 2, 1, 5, 3, 6, 7]
 * 
 * time[0]: list = [4, max]
 * time[1]: list = [2, max]
 * time[2]: list = [1, max]
 * time[3]: list = [1, 5]
 * time[4]: list = [1, 3]
 * time[5]: list = [1, 3] => return true, since 6 > list[1] = 3
 */

import java.util.*; // Stack

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        // increasingList[0] = the smallest last element with length = 1
        // increasingList[1] = the smallest last element with length = 2
        int[] increasingList = new int[2];
        
        increasingList[0] = Integer.MAX_VALUE;
        increasingList[1] = Integer.MAX_VALUE;
        for(int num: nums){
            if(num > increasingList[1]){
                return true;
            }
            else if(num > increasingList[0] && num < increasingList[1]){
                increasingList[1] = num;
            }
            else if(num <  increasingList[0]){
                increasingList[0] = num;
            }
            else{
                continue;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution sol;
        boolean isExist;
        int[] nums = {4, 2, 1, 5, 3, 6, 7};

        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        isExist = sol.increasingTriplet(nums);
        System.out.println("isExist: " + isExist);
    }
}
