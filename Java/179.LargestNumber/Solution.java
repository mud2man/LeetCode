/* Sort: Time:O(nlog(n)), SpaceO(1). Can use string compareTo
 * 1. Compare if a.b is larger than b.a
 */          

import java.util.*; // Stack

public class Solution {
    private class OrderComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            String xy = Integer.toString(x) + Integer.toString(y);
            String yx = Integer.toString(y) + Integer.toString(x);
            return yx.compareTo(xy);
        }
    }
    
    public String largestNumber(int[] nums) {
        Integer[] numObjects = new Integer[nums.length];
        for(int i = 0; i < nums.length; ++i){
            numObjects[i] = new Integer(nums[i]);
        }
        Arrays.sort(numObjects, new OrderComparator());
        
        StringBuilder sb = new StringBuilder("");
        for(int numObject: numObjects){
            sb.append(numObject);
        }
        return (sb.charAt(0) == '0')? "0": sb.toString();
    }
  
    public static void main(String[] args){
        Solution sol;
        int[] nums = {3, 30, 34, 5, 9};

        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("largestNumber: " + sol.largestNumber(nums));
    }
}
