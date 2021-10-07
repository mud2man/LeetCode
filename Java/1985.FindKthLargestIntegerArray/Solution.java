/* Selection algorithm: Time:O(n), Space:O(n)
 * 1. Swap the integer randomlt at first
 * 2. Return the first num if all numbers in nums[lb ~ hb] are the same 
 */

import java.util.*; // Stack


public class Solution {
    private int compareTo(String num1, String num2){
        if(num1.length() == num2.length()){
            return num1.compareTo(num2);
        }else{
            return (num1.length() > num2.length())? 1: -1;
        }
    }
    
    private void random(String[] nums){
        Random rand = new Random();
        for(int i = 0; i < nums.length; i++){
            int randIdx = rand.nextInt(nums.length);
            String tmp = nums[randIdx];
            nums[randIdx] = nums[i];
            nums[i] = tmp;
        }
    }
    
    private boolean areAllSame(String[] nums, int lb, int hb){
        String num = nums[lb];
        for(int i = lb + 1; i <= hb; i++){
            if(num.compareTo(nums[i]) != 0){
                return false;
            }
        }
        return true;
    }
    
    public String kthLargestNumber(String[] nums, int k) {
        random(nums);
        int lb = 0;
        int hb = nums.length - 1;
        while(lb < hb){
            if(areAllSame(nums, lb, hb)){
                return nums[lb];
            }
            String lastNum = nums[hb];
            int ptr = lb - 1; //the last index of numbers >= lastNum
            for(int i = lb; i < hb; i++){
                String num = nums[i];
                if(compareTo(num, lastNum) >= 0){ // num >= lastNum
                    nums[i] = nums[ptr + 1];
                    nums[++ptr] = num;
                }
            }
            nums[hb] = nums[ptr + 1];
            nums[++ptr] = lastNum;
            if(ptr == k - 1){
                return  lastNum;  
            }else if(ptr > k - 1){
                hb = ptr - 1;
            }else{
                lb = ptr + 1;
            }
        }
        return nums[lb];
    }
  
    public static void main(String[] args){
        int k = 4;
        String nums[] = {"3", "6", "7", "10"};
        Solution sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums) + ", k:" + k);
        System.out.println("4-th largest num:" + sol.kthLargestNumber(nums, k));
    }
}
