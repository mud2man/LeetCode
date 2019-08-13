/* Sliding Window: Time:O(n), Space:O(1)
 * 1. Count the number of 1s to get window length
 * 2. Move the window from left, and get the swap number from count[0], where count[]={0's count, 1's count}
 * 3. Compare with minSwap each time, and update it
 */

import java.util.*; // Stack

public class Solution {
    public int minSwaps(int[] data) {
        int window = Arrays.stream(data).sum();
        int[] count = {0, 0};
        for(int i = 0; i < window; ++i){
            count[0] += (data[i] == 0)? 1: 0;
            count[1] += (data[i] == 1)? 1: 0;
        }
        
        int minSwap = count[0];
        for(int j = window; j < data.length; ++j){
            int addData = data[j];
            int deleteData = data[j - window];
            count[0] += (addData == 0)? 1: 0;
            count[1] += (addData == 1)? 1: 0;
            count[0] -= (deleteData == 0)? 1: 0;
            count[1] -= (deleteData == 1)? 1: 0;
            minSwap = Math.min(minSwap, count[0]);
        }
        return minSwap;
    }
 
    public static void main(String[] args){
        int[] data = {1, 0, 1, 0, 1};
        Solution sol = new Solution();
        System.out.println(String.format("data: %s", Arrays.toString(data)));
        System.out.println(String.format("minimum swap: %d", sol.minSwaps(data)));
    }
}
