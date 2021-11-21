/* Hash: Time:O(n), Space:O(n)
 * 1. Have lastNum2Len to remember the longest length with the key of last number
 * 2. Update lastNum2Len with considering if num - difference exist in lastNum2Len
 * 3. If yes, lastNum2Len.put(num, max(lastNum2Len.get(prevNum) + 1, lastNum2Len.getOrDefault(num, 0))
 * 4. Otherwise, lastNum2Len.put(num, Math.max(1, lastNum2Len.getOrDefault(num, 0)));
 */

import java.util.*; // Stack


public class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> lastNum2Len = new HashMap<>();
        int longestLen = 0;
        for(int num: arr){
            int prevNum = num - difference;
            if(lastNum2Len.containsKey(prevNum)){
                lastNum2Len.put(num, Math.max(lastNum2Len.get(prevNum) + 1, lastNum2Len.getOrDefault(num, 0)));
            }else{
                lastNum2Len.put(num, Math.max(1, lastNum2Len.getOrDefault(num, 0)));
            }
            longestLen = Math.max(longestLen, lastNum2Len.get(num));
        }
        return longestLen;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int difference = 1;
        int[] arr = {1, 2, 3, 4};
        System.out.println("difference:" + difference);
        System.out.println("arr:" + Arrays.toString(arr));
        System.out.println("longest length:" + sol.longestSubsequence(arr, difference));
    }
}
