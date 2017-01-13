/* O(n)
 * 1. list[0] = the first element of list with length 2
 * 2. list[1] = the second element of list with length 2
 * 3. min = the minimum value as long as we traverse
 * 4. Maintain the relationship among list[0], list[1], min
 * 5. If num > list[1], return true;
 * ex: preorder = [4, 2, 1, 5, 3, 6]
 * 
 * time[0]: list = [max, max], min = max
 * time[1]: list = [max, max], min = 2
 * time[1]: list = [max, max], min = 1
 * time[1]: list = [1, 5], min = 1
 * time[1]: list = [1, 3], min = 1
 * time[1]: list = [1, 3], min = 1 => return true, since 6 > list[1] = 3
 */

import java.util.*; // Stack

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        //list[0] = the minimum last element of list with length 1;
        //list[1] = the minimum last element of list with length 2;
        int[] list;
        int min;
        
        list = new int[3];
        list[0] = Integer.MAX_VALUE;
        list[1] = Integer.MAX_VALUE;
        min = Integer.MAX_VALUE;
        
        for(int num: nums){
            if(num > list[1]){
                return true;
            }
            else if((num > list[0]) && (num < list[1])){
                list[1] = num;   
            }
            else if((num > min) &&(num < list[1])){
                list[1] = num;
                list[0] = min;
            }
            else if(num < list[0]){
                min = Math.min(num, min);
            }
            else{
                continue;
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        Solution sol;
        boolean isExist;
        int[] nums = {4, 2, 1, 5, 3, 6};

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));

        isExist = sol.increasingTriplet(nums);
        
        System.out.println("isExist: " + isExist);
	}
}
