/* Sliding window: Time:O(n), Space:O(1)
 * 1. Slide window, and update sum. Accumulate count if sum / k >= threshold
 */

import java.util.*;

public class Solution{
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int count = 0;
        int sum = 0;
        for(int i = 0; i < k - 1; ++i){
            sum += arr[i];
        }
        for(int i = k - 1; i < arr.length; ++i){
            sum += arr[i];
            sum -=(i - k >= 0)? arr[i - k]: 0;
            count += (sum / k >= threshold)? 1: 0;
        }
        return count;
    }
  
    public static void main(String[] args){
        int[] arr = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;
        Solution sol = new Solution();
        System.out.println("arr:" + Arrays.toString(arr) + ", k:" + k + ", threshold:" + threshold);
        System.out.println("count:" + sol.numOfSubarrays(arr, k, threshold));
    }
}
