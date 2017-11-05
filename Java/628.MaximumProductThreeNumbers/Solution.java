/* Math: Time:O(n), Space:O(1)
 * 1. There is only to cases for the max product
 * 2. case1(positive): max[0] * max[1] * max[2] 
 * 3. case2(positive): max[2] * min[0] * min[1] 
 * 4. case3(negative): max[0] * min[1] * min[2]
 * 5. So the answer is max(max[0] * max[1] * max[2], max[0] * min[1] * min[2]);
 */

import java.util.*;

public class Solution{
    public int maximumProduct(int[] nums) {
        int[] max = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        int[] min = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        for(int num: nums){
            if(num >= max[2]){
                max[0] = max[1];
                max[1] = max[2];
                max[2] = num;
            }
            else if(num >= max[1] && num < max[2]){
                max[0] = max[1];
                max[1] = num;
            }
            else if(num >= max[0] && num < max[1]){
                max[0] = num;
            }
            
            if(num <= min[1]){
                min[0] = min[1];
                min[1] = num;
            }
            else if(num > min[1] && num <= min[0]){
                min[0] = num;
            }
        }
        
        if(min[0] <= 0){
            return Math.max(max[0] * max[1] * max[2], max[2] * min[0] * min[1]);
        }
        else{
            return max[0] * max[1] * max[2];
        }
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums =  {-1, -2, -3, 0, 4, 5, 6};
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("maxProduct: " + sol.maximumProduct(nums));
    }
}
