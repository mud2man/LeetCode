/* Sliding window:Time:O(n), Space:O(n)
 * 1. Keep the longest window with K different numbers ending at i, and its num2Count "kDiffMap"
 * 2. Keep the longest window with K - 1 different numbers ending at i, and its num2Count "kMinusOneDiffMap"
 * 3. Iterate the last number, and update "kMinusOneDiffStart" and "kDiffStart" while keeping K - 1/K different number in kMinusOneDiffMap/kDiffMap
 * 4. Given kMinusOneDiffStart and kDiffStart, we can get the number of subarray with K different number ending at nums[i] is kMinusOneDiffStart - kDiffStart
 */

import java.util.*;

public class Solution {
    private int shiftStartIndex(Map<Integer, Integer> num2Count, int threshold, int[] A, int start){
        while(num2Count.size() > threshold){
            int removeNum = A[start++];
            num2Count.put(removeNum, num2Count.get(removeNum) - 1);
            if(num2Count.get(removeNum) == 0){
                num2Count.remove(removeNum);
            }
        }
        return start;
    }
    
    public int subarraysWithKDistinct(int[] A, int K) {
        int kMinusOneDiffStart = 0;
        int kDiffStart = 0;
        Map<Integer, Integer> kMinusOneDiffMap = new HashMap<>();
        Map<Integer, Integer> kDiffMap = new HashMap<>();
        int count = 0;
        for(int a: A){
            kMinusOneDiffMap.put(a, kMinusOneDiffMap.getOrDefault(a, 0) + 1);
            kMinusOneDiffStart = shiftStartIndex(kMinusOneDiffMap, K - 1, A, kMinusOneDiffStart);
            kDiffMap.put(a, kDiffMap.getOrDefault(a, 0) + 1);
            kDiffStart = shiftStartIndex(kDiffMap, K, A, kDiffStart);
            count +=(kDiffMap.size() == K)? (kMinusOneDiffStart - kDiffStart): 0;
        }
        return count;
    }
 
    public static void main(String[] args){
        int[] A = {1, 2, 1, 2, 3};
        int K = 2;
        Solution sol = new Solution();
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("K:" + K);
        System.out.println("subarray#:" + sol.subarraysWithKDistinct(A, K));
    }
}
