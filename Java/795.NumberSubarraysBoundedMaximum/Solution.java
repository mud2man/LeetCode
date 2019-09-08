/* Sliding Window: Time = O(n), Space = O(1)
 * 1. Have "start" to record the starting point, s.t. {A[start], A[start + 1], ... A[i]} contains no element > R
 * 2. Have "lastHitIdx" to record the last index in range {L, R}
 * 3. Accumulate count with (i - start + 1) when (A[i] >= L && A[i] <= R)
 * 4. Accumulate count with ((i - start + 1) - (i - lastHitIdx)) when A[i] < L, but hit == true
 */

import java.util.*;

public class Solution{
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        boolean hit = false;
        int start = -1;
        int count = 0;
        int lastHitIdx = -1;
        for(int i = 0; i < A.length; ++i){
            if(A[i] > R){
                start = -1;
                hit = false;
            }else if(A[i] >= L && A[i] <= R){
                start = (start == -1)? i: start;
                hit = true;
                count += (i - start + 1);
                lastHitIdx = i;
            }else{
                if(hit){
                    count += ((i - start + 1) - (i - lastHitIdx)); 
                }else{
                    start = (start == -1)? i: start;
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] A = {2, 1, 4, 3};
        int L = 2;
        int R = 3;
        Solution sol = new Solution();
        System.out.println(Arrays.toString(A));
        System.out.println("count with bounded maximum: " + sol.numSubarrayBoundedMax(A, L, R));
	}
}
