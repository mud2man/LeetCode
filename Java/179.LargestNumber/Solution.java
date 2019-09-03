/* Sort: Time:O(nlog(n)), SpaceO(1). Can use string compareTo
 * 1. Compare if a.b is larger than b.a
 */          

import java.util.*; // Stack

public class Solution {
    public String largestNumber(int[] nums) {
        Integer[] numObjects = new Integer[nums.length];
        for(int i = 0; i < nums.length; ++i){
            numObjects[i] = new Integer(nums[i]);
        }
        Arrays.sort(numObjects, (x, y) -> {
            String xy = Integer.toString(x) + Integer.toString(y);
            String yx = Integer.toString(y) + Integer.toString(x);
            return yx.compareTo(xy);
        });
        
        String ret = "";
        for(Integer numObject: numObjects){
           ret = ret + Integer.toString(numObject);
        }
        return (ret.charAt(0) == '0')? "0": ret;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("largestNumber: " + sol.largestNumber(nums));
    }
}
