/* Sort: O(nlog(n))
 * 1. Compare if a.b is larger than b.a
 */          

import java.util.*; // Stack

public class Solution {
    private class SeqComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y ){
            if(x == 0 && y == 0){
                return 0;
            }
            else if(x == 0){
                return 1;
            }
            else if(y == 0){
                return -1;
            }
            else{
                int xy = Integer.valueOf(x) * (int)Math.pow(10, (int)Math.log10(Integer.valueOf(y)) + 1) + y;
                int yx = Integer.valueOf(y) * (int)Math.pow(10, (int)Math.log10(Integer.valueOf(x)) + 1) + x;
                return yx - xy;
            }
        }
    }
    
    public String largestNumber(int[] nums) {
        Integer[] sortedNums = new Integer[nums.length];
        for(int i = 0; i < nums.length; ++i){
            sortedNums[i] = Integer.valueOf(nums[i]);
        }
        Arrays.sort(sortedNums, new SeqComparator());
        
        String maxSeq = "";
        for(Integer num: sortedNums){
            System.out.println(Integer.valueOf(num));
            maxSeq = maxSeq + Integer.toString(Integer.valueOf(num));
        }
        
        return (maxSeq.charAt(0) == '0')? "0": maxSeq;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {3, 30, 34, 5, 9};

        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("largestNumber: " + sol.largestNumber(nums));
    }
}
