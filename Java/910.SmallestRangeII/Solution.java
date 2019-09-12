/* Greedy: Time:O(nlogn), Space:O(1). We need to prove the theory
 * 1. Decrease A by K, and sort
 * 2. Separete A to 2 parts "left" and "right" with adding 2 * K to all value in "left", while keeping "right" unchanged
 * 3. Get the range of "left" and "right", and update the global range
 *
 * Proof: After sorting, A[0] <= A[1] <= ... <= A[A.length - 1] 
 *        If A[i] is the peak with the minimum range, then all the left neighbors of A[i] should be added with K since it can make them closer to the peak.
 *        And it will only get the better answer. We can claim A[i + 1] must be decreased by K, since A[i] is the peak. So as all number on the right side. 
 *        So we can traverse from left of A, and increase A[i] with 2 * K
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int smallestRangeII(int[] A, int K) {
        for(int i = 0; i < A.length; ++i){
            A[i] -= K;
        }
        
        Arrays.sort(A);
        int range = A[A.length - 1] - A[0];
        int[] left = new int[]{A[0] + (2 * K), A[0] + (2 * K)};
        int[] right = new int[]{A[A.length - 1], A[A.length - 1]};
        for(int i = 0; i < A.length - 1; ++i){
            left[1] = A[i] + (2 * K);
            right[0] = A[i + 1];
            int max = Math.max(left[1], right[1]);
            int min = Math.min(left[0], right[0]);
            int diff = max - min;
            range = Math.min(range, diff);
        }
        return range;
    }
 
    public static void main(String[] args){
        int[] A = {1, 3, 6};
        int K = 3;
        Solution sol = new Solution();
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("K: " + K);
        System.out.println("smallest range: " + sol.smallestRangeII(A, K));
    }
}
