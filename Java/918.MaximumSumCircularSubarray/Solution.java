/* Dynamic programming: Time:O(n), Space:O(n). LeetCode has solution only consums O(1) space
 * 1. The solution has 2 cases, which are case1: no rollover, case2: rollover
 * 2. For case1, we can record the maximum value "curr" containing A[i] and update it with "max"
 * 3. For case2, we get the array "fromLeft", which fromLeft[i] = the max sum containing A[0] considering A[0, 1, ... i]
 * 4. Plus, we get the array "fromRight", which fromRight[j] = the max sum containing A[A.length - 1] considering A[j, j + 1, ..., A.length - 1]
 * 5. In the end, we get the max from case1 and case2, where case2 is from (fromLeft[i] + fromRight[i + 1]), where 0 <= i < A.length - 1
 */

import java.util.*; // Stack

public class Solution {
    public int maxSubarraySumCircular(int[] A) {
        // the max sum start from and contain A[0]
        int[] fromLeft = new int[A.length];
        // the max sum start from and contain A[A.length - 1]
        int[] fromRight = new int[A.length];
        int leftSum = A[0];
        int rightSum = A[A.length - 1];
        int max = A[0];
        int prev = A[0];
        fromLeft[0] = A[0];
        fromRight[A.length - 1] = A[A.length - 1];
        for(int i = 1; i < A.length; ++i){
            leftSum += A[i];
            rightSum += A[A.length - 1 - i];
            fromLeft[i] = Math.max(fromLeft[i - 1], leftSum);
            fromRight[A.length - 1 - i] = Math.max(fromRight[A.length - i], rightSum);
            
            //update max considering A[0], A[1], ... A[i];
            int curr = (prev > 0)? prev + A[i]: A[i];
            max = Math.max(max, curr);
            prev = curr;
        }
        
        for(int i = 0; i < A.length - 1; ++i){
            max = Math.max(max, fromLeft[i] + fromRight[i + 1]);
        }
        return max;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {3, -1, 2, -1};
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("maximum circular sum: " + sol.maxSubarraySumCircular(A));
    }
}
